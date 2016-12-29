package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.dto.PositionAlarmDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.PositionAlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class PositionAlarmService implements IService<PositionAlarmDto> {
	
	@Inject
	private PositionAlarmRepository repository;
	
	@Autowired
	CompanyDetectorAlarmService companyDetectorAlarmAlarmService;

	@Override
	public BasicResult<?> save(PositionAlarmDto dto) {
//		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
//		
//		PositionAlarm positionAlarm = new PositionAlarm(dto);
//		positionAlarm = repository.save(positionAlarm);
//		
//		dto.setUid(positionAlarm.getUid());
//		result.setEntity(dto);
//		
//		result.setResultType( ResultMessageType.SUCCESS );
//		result.setMessage("Executado com sucesso.");					
//		
//		return result;
		
		// TODO Auto-generated method stub
		return null;
	}
	
    /** Método checar se houve violação de Alarme
     *   @return sem retorno  						*/
	public AlarmType checkAlarmLimits(Position position) {		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
				
		CompanyDetectorAlarmDto alarm = companyDetectorAlarmAlarmService.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
			
		AlarmType alarmType = AlarmType.NORMAL ;
		
		if(alarm != null) {
			
			if( position.getLastValue() > alarm.getAlarmDto().getAlarm3() ) {
				
				alarmType = AlarmType.EVACUACAO;
			}
			else if( position.getLastValue() > alarm.getAlarmDto().getAlarm2() ) {
				
				alarmType = AlarmType.ALERTA;
			}
			else if( position.getLastValue() > alarm.getAlarmDto().getAlarm1() ) {
				
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
	public void saveOrUpdatePositionAlarm(Position position, CompanyDetector companyDetector, Sensor sensor, AlarmType alarmType) {
		
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
		}
		else {
			
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setLastValue(position.getLastValue());				
		}
		
		repository.save(positionAlarm);
		
		//TODO - Inserir log de Sistema e Tratamento de Erro
	}	

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
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
				result.setMessage("Nenhuma area.");
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
				result.setMessage("Nenhuma area.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
}
