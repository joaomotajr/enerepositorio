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
import br.com.eneeyes.main.model.enums.SmsStatus;
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
		
    /** Método checar se houve violação de Alarme
     *   @return sem retorno  						*/
	public AlarmType checkAlarmLimits(Position position) {		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
		
		if(CompanyDetectorAlarmSingletonRepository.init()) {
			CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
		}
		
		CompanyDetectorAlarmDto alarm = CompanyDetectorAlarmSingletonRepository.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
			
		AlarmType alarmType = AlarmType.NORMAL ;
		
		if(alarm != null) {
			
			if( position.getLastValue().compareTo( new BigDecimal(alarm.getAlarmDto().getAlarm3())) > 0 ) {				
				alarmType = AlarmType.EVACUACAO;
			}
			else if( position.getLastValue().compareTo( new BigDecimal(alarm.getAlarmDto().getAlarm2())) > 0 ) {				
				alarmType = AlarmType.ALERTA;
			}
			else if( position.getLastValue().compareTo( new BigDecimal(alarm.getAlarmDto().getAlarm1())) > 0 ) {				
				alarmType = AlarmType.DETECCAO;				
			}		
		}	
		
		return alarmType;		 
	}
	
	/** Método checar se houve violação de Alarme
	 * Criar Alarme correspondentes
	 * Carregar Filas de eventos 
     *   @return Tipo do Alarme */
	public AlarmType checkAndUpdateAlarmsAndActions(Position position) {		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
		
		if(CompanyDetectorAlarmSingletonRepository.init()) {
			CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
		}
		
		CompanyDetectorAlarmDto alarm = CompanyDetectorAlarmSingletonRepository.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
		
		AlarmDto alarmDto = alarm.getAlarmDto();
		
		AlarmType alarmType = getExistsAlarm(alarmDto, position.getLastValue());
		
		if (alarmType != AlarmType.NORMAL) {
			
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
				
			saveOrUpdatePositionAlarm(position, companyDetector, sensor, alarmType, emailStatus, smsStatus, action);
		}		
		
		return alarmType; 
	}
	
	private AlarmType getExistsAlarm(AlarmDto alarm, BigDecimal lastValue ) {
			
		AlarmType alarmType = AlarmType.NORMAL ;
				
		if(alarm != null) {
			
			if(!alarm.getAlarmOn() ) {				
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
		
		return alarmType;		
	}

	/**
	 * @param position
	 * @param companyDetector
	 * @param sensor
	 * @param alarmType
	 */
	public void saveOrUpdatePositionAlarm(Position position, CompanyDetector companyDetector, Sensor sensor, 
			AlarmType alarmType, EmailStatus emailStatus, SmsStatus smsStatus, String action) {
		
		PositionAlarm positionAlarm = repository.findByCompanyDetectorAndSensorAndAlarmType(companyDetector, sensor, alarmType);
		
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
