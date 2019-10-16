package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.dto.PositionAlarmDto;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;
import br.com.eneeyes.main.repository.PositionAlarmRepository;
import br.com.eneeyes.main.repository.singleton.AlarmSingletonRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.buss.AlarmParams;
import br.com.eneeyes.main.service.views.CompanyDeviceAlarmViewService;

@Service
public class PositionAlarmService implements IService<PositionAlarmDto> {
	
	@Autowired
	private PositionAlarmRepository repository;
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	private CompanyDeviceAlarmViewService companyDetectorAlarmViewService;
	
	@Autowired
	HistoricAlarmService historicAlarmService;
	
	public AlarmType checkAndUpdateAlarmsAndActions(Position position) {		
		return checkAndUpdateAlarmsAndActions(position, false);
	}
	
	public AlarmType checkAndUpdateAlarmsAndActions(Position position, Boolean offLine) {		

		CompanyDevice companyDevice = new CompanyDevice(position.getCompanyDevice().getUid());		
		if(AlarmSingletonRepository.init()) {
			AlarmSingletonRepository.populate(companyDetectorAlarmViewService.findAll());
		}		
		
		AlarmDto alarmDto = AlarmSingletonRepository.findByCompanyDevice(companyDevice.getUid());
		
		AlarmType alarmType = AlarmType.NORMAL;
		if(offLine)
			alarmType = AlarmType.OFFLINE;
		else if(alarmDto == null)
			alarmType = AlarmType.OFF;
		else {
			alarmType = getExistsAlarm(alarmDto, position.getLastValue());
		}
		
		if (alarmType != AlarmType.NORMAL && alarmType != AlarmType.OFF) {			
			AlarmParams alarmParams = new AlarmParams(alarmDto, alarmType);
			historicAlarmService.save(position.getLastValue(), companyDevice.getUid(), position.getHistoricId(), alarmDto, alarmType, alarmParams);

			if(alarmDto.getAlarmOn())			
				updatePositionAlarm(position, companyDevice, alarmType, alarmParams);		
		}				
		return alarmType; 
	}

	
	AlarmType getExistsAlarm(AlarmDto alarm, BigDecimal lastValue ) {
			
		AlarmType alarmType = AlarmType.NORMAL;				
		if(alarm != null) {
			
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

	/**
	 * @param position
	 * @param companyDetector
	 * @param sensor
	 * @param alarmType
	 */
	public void updatePositionAlarm(Position position, CompanyDevice companyDevice, AlarmType alarmType, AlarmParams alarmParams) {			
		List<AlarmStatus> solvedOrCancelesAlarms = new ArrayList<AlarmStatus>();
		
		solvedOrCancelesAlarms.add(AlarmStatus.SOLVED);
		solvedOrCancelesAlarms.add(AlarmStatus.CANCELED);

		PositionAlarm positionAlarm = repository.findByCompanyDeviceAndAlarmTypeAndAlarmStatusNotIn(companyDevice, alarmType, solvedOrCancelesAlarms);
		
		if(positionAlarm == null) {
			
			positionAlarm =  new PositionAlarm();
			
			positionAlarm.setCompanyDevice(companyDevice);
			positionAlarm.setFirstUpdate(new Date());
			positionAlarm.setLastValue(position.getLastValue());
			positionAlarm.setAlarmStatus(AlarmStatus.CREATED);
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setAlarmType(alarmType);
			positionAlarm.setEmailStatus(alarmParams.getEmailStatus());
			positionAlarm.setSmsStatus(alarmParams.getSmsStatus());
			positionAlarm.setAction(alarmParams.getAction());
			positionAlarm.setSoundStatus(alarmParams.getSoundStatus());
			positionAlarm.setSigmaStatus(alarmParams.getSigmaStatus());
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
	
	public BasicResult<?> updateAlarmStatus(Long uid, AlarmStatus alarmStatus) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		try {
			repository.updateAlarmStatus(alarmStatus, uid);			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}
	
	public BasicResult<?> updateAlarmStatusByCompanyDeviceId(Long companyDeviceId, AlarmStatus alarmStatus, AlarmType alarmType) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		List<AlarmStatus> solvedOrCancelesAlarms = new ArrayList<AlarmStatus>();		
		solvedOrCancelesAlarms.add(AlarmStatus.SOLVED);
		solvedOrCancelesAlarms.add(AlarmStatus.CANCELED);
		
		try {
			repository.updateAlarmStatusByCompanyDevice(alarmStatus, companyDeviceId, alarmType, solvedOrCancelesAlarms);			
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
		CompanyDevice companyDevice = new CompanyDevice();
		companyDevice.setUid(uid);
		
		try {
			List<PositionAlarm> lista = repository.findByCompanyDevice(companyDevice);
			
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
