package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.dto.PositionAlarmDto;
import br.com.eneeyes.main.model.CompanyDetector;
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
import br.com.eneeyes.main.repository.singleton.CompanyDetectorAlarmSingletonRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class PositionAlarmService implements IService<PositionAlarmDto> {
	
	@Autowired
	private PositionAlarmRepository repository;
	
	@Autowired
	CompanyDetectorAlarmService companyDetectorAlarmAlarmService;
	
	@Autowired
	HistoricAlarmService historicAlarmService;
	
	public AlarmType checkAndUpdateAlarmsAndActions(Position position) {
		
		return checkAndUpdateAlarmsAndActions(position, false);
	}
	
	public AlarmType checkAndUpdateAlarmsAndActions(Position position, Boolean offLine) {		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
		
		if(CompanyDetectorAlarmSingletonRepository.init()) {
			CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
		}
		
		CompanyDetectorAlarmDto alarm = CompanyDetectorAlarmSingletonRepository.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
		
		AlarmDto alarmDto = alarm.getAlarmDto();
		
		AlarmType alarmType = AlarmType.NORMAL;
		if(offLine)
			alarmType = AlarmType.OFFLINE;
		else
			alarmType = getExistsAlarm(alarmDto, position.getLastValue());
		
		if (alarmType != AlarmType.NORMAL) {
					
			SigmaStatus sigmaStatus = null;
			if(alarmDto.getAlarmSigma() != null && alarmDto.getAlarmSigma())
				sigmaStatus = SigmaStatus.ON;
			else
				sigmaStatus = SigmaStatus.OFF;
			
			EmailStatus emailStatus = null;
			if(alarm.getAlarmDto().getAlarmEmail() != null && alarm.getAlarmDto().getAlarmEmail())
				emailStatus = EmailStatus.PENDENT;
			else
				emailStatus = EmailStatus.OFF;
			
			SmsStatus smsStatus = null;
			if(alarm.getAlarmDto().getAlarmSms() != null && alarm.getAlarmDto().getAlarmSms())
				smsStatus = SmsStatus.PENDENT;
			else
				smsStatus = SmsStatus.OFF;
			
			String action = null;
			if(alarmType == AlarmType.DETECCAO)
				action = alarm.getAlarmDto().getAction1();
			else if(alarmType == AlarmType.ALERTA)
				action = alarm.getAlarmDto().getAction2();
			else if(alarmType == AlarmType.EVACUACAO)
				action = alarm.getAlarmDto().getAction3();
			
			SoundStatus soundStatus = null;
			if(alarm.getAlarmDto().getAlarmSound())
				soundStatus = SoundStatus.ON;
			else
				soundStatus = SoundStatus.OFF;
			
			historicAlarmService.save(position.getLastValue(), companyDetector.getUid(), sensor.getUid(), position.getHistoric().getUid(), alarm.getAlarmDto().getAlarmOn(), alarmType, 
					emailStatus, smsStatus, action, soundStatus, sigmaStatus);
			
			if(alarm.getAlarmDto().getAlarmOn())			
				updatePositionAlarm(position, companyDetector, sensor, alarmType, emailStatus, smsStatus, action, soundStatus, sigmaStatus);
		
		}	
				
		return !alarm.getAlarmDto().getAlarmOn() ? AlarmType.OFF : alarmType; 
	}

	
	AlarmType getExistsAlarm(AlarmDto alarm, BigDecimal lastValue ) {
			
		AlarmType alarmType = AlarmType.NORMAL;
				
		if(alarm != null) {

			if( lastValue.compareTo( new BigDecimal(alarm.getAlarm3())) > 0 ) {				
				alarmType = AlarmType.EVACUACAO;							
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm2())) > 0 ) {				
				alarmType = AlarmType.ALERTA;
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm1())) > 0 ) {				
				alarmType = AlarmType.DETECCAO;
			}		
		}	
		
		return alarmType;		
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

		PositionAlarm positionAlarm = repository.findByCompanyDetectorAndSensorAndAlarmTypeAndAlarmStatusNotIn(companyDetector, sensor, alarmType, solvedOrCancelesAlarms);
		
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
		
		repository.save(positionAlarm);			
	}
	
	public int updateEmailStatus(Long uid, EmailStatus emailStatus) {
		
		return repository.updateEmailStatus(emailStatus, uid);		
	}
	
	public int updateSmsStatus(Long uid, SmsStatus smstatus) {
		
		return repository.updateSmsStatus(smstatus, uid);		
	}
	
	public BasicResult<?> updateAlarmStatus(Long uid, AlarmStatus alarmtatus) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		try {
			repository.updateAlarmStatus(alarmtatus, uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public BasicResult<?> updateAlarmStatusAndSilent(Long uid, AlarmStatus alarmStatus) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		try {
			repository.updateAlarmStatusAndSoundStatus(alarmStatus, SoundStatus.SILENT, uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public BasicResult<?> updateSoundStatus(Long uid, SoundStatus soundStatus) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		try {
			repository.updateSoundStatus(soundStatus, uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public int updateSigmaStatus(Long uid, SigmaStatus sigmatatus) {
		
		return repository.updateSigmaStatus(sigmatatus, uid);		
	}	

	@Override
	public BasicResult<?> findOne(Long uid) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		try {
			PositionAlarm item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new PositionAlarmDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	public BasicResult<?> findByCompanyDetector(Long uid) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		try {
			List<PositionAlarm> lista = repository.findByCompanyDetector(companyDetector);
			
			if (lista != null) {
				
				List<PositionAlarmDto> dto = new ArrayList<PositionAlarmDto>();
				
				for (PositionAlarm positionAlarm   : lista) {					
					dto.add(new PositionAlarmDto(positionAlarm) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	public Result<?> listAll() {
		
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>(); 	
		
		try {
			List<PositionAlarm> lista = repository.findAll();

			if (lista != null) {
				
				List<PositionAlarmDto> dto = new ArrayList<PositionAlarmDto>();
				
				for (PositionAlarm positionAlarm   : lista) {					
					dto.add(new PositionAlarmDto(positionAlarm) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BasicResult<?> save(PositionAlarmDto dto) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
