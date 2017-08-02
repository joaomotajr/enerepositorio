package br.com.eneeyes.main.service.buss;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Historic;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.PositionAlarmRepository;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.repository.singleton.CompanyDetectorAlarmSingletonRepository;
import br.com.eneeyes.main.service.CompanyDetectorAlarmService;
import br.com.eneeyes.main.service.HistoricAlarmService;

@Service
public class ProcessAlarmService {
	
	@Autowired
	private PositionRepository positionrepository;
	
	@Autowired
	CompanyDetectorAlarmService companyDetectorAlarmAlarmService;
	
	@Autowired
	HistoricAlarmService historicAlarmService;
	
	@Autowired
	private PositionAlarmRepository positionAlarmRepository;
	
	public Position updatePositionByHistoric(Historic historic) {
		
		Position position = new Position();
		
		position = positionrepository.findByCompanyDetectorAndSensor(historic.getCompanyDetector(), historic.getSensor());
		
		if (position != null) {		
			
			position.setCompanyDetector(historic.getCompanyDetector());
			position.setLastUpdate(historic.getLastUpdate());
			position.setLastValue(historic.getValue());
			position.setHistoric(historic);
						
			positionrepository.save(position);
		}
		
		return position;		
	}
	
	
	public CompanyDetectorAlarmDto checkExistAlarms(Position position) {
		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
		
		if(CompanyDetectorAlarmSingletonRepository.init()) {
			CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
		}
		
		return CompanyDetectorAlarmSingletonRepository.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());		
		
	}
	
	public AlarmType getExistsAlarm(CompanyDetectorAlarmDto companyDetectorAlarmDto, BigDecimal lastValue ) {						
		
		AlarmType alarmType = AlarmType.NORMAL;
				
		if(companyDetectorAlarmDto != null) {
			
			AlarmDto alarm = companyDetectorAlarmDto.getAlarmDto();
						
			if( !alarm.getAlarmOn()) {
				alarmType = AlarmType.OFF;
			}				
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm3())) > 0 ) {				
				alarmType = AlarmType.EVACUACAO;							
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm2())) > 0 ) {				
				alarmType = AlarmType.ALERTA;
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm1())) > 0 ) {				
				alarmType = AlarmType.DETECCAO;
			}		
		}
		else {
			alarmType = AlarmType.OFF;
		}
		
		return alarmType;		
	}
	
	public void updateAlarmsAndActions(AlarmDto alarmDto, AlarmType alarmType, Position position) {		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
	
		
		if (alarmType != AlarmType.NORMAL && alarmType != AlarmType.OFF) {
					
			SigmaStatus sigmaStatus = null;
			if(alarmDto.getAlarmSigma() != null && alarmDto.getAlarmSigma())
				sigmaStatus = SigmaStatus.ON;
			else
				sigmaStatus = SigmaStatus.OFF;
			
			EmailStatus emailStatus = null;
			if(alarmDto.getAlarmEmail() != null && alarmDto.getAlarmEmail())
				emailStatus = EmailStatus.PENDENT;
			else
				emailStatus = EmailStatus.OFF;
			
			SmsStatus smsStatus = null;
			if(alarmDto.getAlarmSms() != null && alarmDto.getAlarmSms())
				smsStatus = SmsStatus.PENDENT;
			else
				smsStatus = SmsStatus.OFF;
			
			String action = null;
			if(alarmType == AlarmType.DETECCAO)
				action = alarmDto.getAction1();
			else if(alarmType == AlarmType.ALERTA)
				action = alarmDto.getAction2();
			else if(alarmType == AlarmType.EVACUACAO)
				action = alarmDto.getAction3();
			
			SoundStatus soundStatus = null;
			if(alarmDto.getAlarmSound())
				soundStatus = SoundStatus.ON;
			else
				soundStatus = SoundStatus.OFF;
			
			historicAlarmService.save(position.getLastValue(), companyDetector.getUid(), sensor.getUid(), position.getHistoric().getUid(), alarmDto.getAlarmOn(), alarmType, 
					emailStatus, smsStatus, action, soundStatus, sigmaStatus);
			
			if(alarmDto.getAlarmOn())			
				updatePositionAlarm(position, companyDetector, sensor, alarmType, emailStatus, smsStatus, action, soundStatus, sigmaStatus);
		
		}				
		
	}
	
	/**
	 * @param position
	 * @param companyDetector
	 * @param sensor
	 * @param alarmType
	 */
	public void updatePositionAlarm(Position position, CompanyDetector companyDetector, Sensor sensor, 
			AlarmType alarmType, EmailStatus emailStatus, SmsStatus smsStatus, String action, SoundStatus soundStatus, SigmaStatus sigmaStatus) {
				
		List<AlarmStatus> solvedOrCancelesAlarms = new ArrayList<AlarmStatus>();
		
		solvedOrCancelesAlarms.add(AlarmStatus.SOLVED);
		solvedOrCancelesAlarms.add(AlarmStatus.CANCELED);

		PositionAlarm positionAlarm = positionAlarmRepository.findByCompanyDetectorAndSensorAndAlarmTypeAndAlarmStatusNotIn(companyDetector, sensor, alarmType, solvedOrCancelesAlarms);
		
		if(positionAlarm == null) {
			
			positionAlarm =  new PositionAlarm();
			
			positionAlarm.setCompanyDetector(position.getCompanyDetector());
			positionAlarm.setSensor(position.getSensor());
			positionAlarm.setFirstUpdate(new Date());
			positionAlarm.setLastValue(position.getLastValue());
			positionAlarm.setAlarmStatus(AlarmStatus.CREATED);
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setAlarmType(alarmType);
			positionAlarm.setEmailStatus(emailStatus);
			positionAlarm.setSmsStatus(smsStatus);
			positionAlarm.setAction(action);
			positionAlarm.setSoundStatus(soundStatus);
			positionAlarm.setSigmaStatus(sigmaStatus);
		}
		else {
			// TODO Verificar se haverá reenvio de Actions em casos de Reinscidência do mesmo alerta				
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setLastValue(position.getLastValue());				
		}
		
		positionAlarmRepository.save(positionAlarm);			
	}
	
	
}
