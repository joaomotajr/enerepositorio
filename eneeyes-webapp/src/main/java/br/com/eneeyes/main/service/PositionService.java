package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.PositionDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Historic;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Service
public class PositionService implements IService<PositionDto> {
	
	@Autowired
	private PositionRepository repository;
	
	@Autowired
	PositionAlarmService positionAlarmService;	
	
	@Override
	public BasicResult<?> save(PositionDto dto) {		
		// TODO Auto-generated method stub
		return null;
	}

	public BasicResult<?> updatePosition(Historic historic) {

		Result<PositionDto> result = new Result<PositionDto>();
		
		Position position = new Position();
		
		position = repository.findByCompanyDetectorAndSensor(historic.getCompanyDetector(), historic.getSensor());
		
		if (position != null) {		
			
			position.setCompanyDetector(historic.getCompanyDetector());
			AlarmType alarmType = positionAlarmService.checkAlarmLimits(position);

			position.setAlarmType(alarmType);
			position.setLastUpdate(historic.getLastUpdate());
			position.setLastValue(historic.getValue());
			
			repository.save(position);
			
			//TODO Position não possui CompanyDetector, o objeto é Lazy pq é chave de pesquisa
			position.setCompanyDetector(historic.getCompanyDetector());
			
			if (alarmType != AlarmType.NORMAL) {				
				positionAlarmService.saveOrUpdatePositionAlarm(position, historic.getCompanyDetector(), historic.getSensor(), alarmType);
			}
						
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
		}
		else {		
			
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Inconsistencia na Posição.");
		}		
		
		return result;		
	}	
	
	public void createInitialPosition(CompanyDetector companyDetector) {
		
		Set<Sensor> sensors = companyDetector.getDetector().getSensors();
		
		for (Sensor sensor   : sensors) {
		
			if(repository.countByCompanyDetectorAndSensor(companyDetector, sensor) == 0) {
	
				Position position = new Position();	

				position.setCompanyDetector(companyDetector);
				position.setSensor(sensor);
				position.setLastUpdate(new Date());
				position.setLastValue((double) 0);
				position.setAlarmType(AlarmType.NORMAL);
				
				repository.save(position);
			}
		}
	}

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Position findByUid(Long uid) {
			
		return repository.findOne(uid);				
	}
	
	
	public BasicResult<?> findOne(Long uid) {
		Result<PositionDto> result = new Result<PositionDto>();
		
		try {
			Position item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new PositionDto(item));
				
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
		Result<PositionDto> result = new Result<PositionDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		try {
			List<Position> lista = repository.findByCompanyDetector(companyDetector);
			
			if (lista != null) {
				
				List<PositionDto> dto = new ArrayList<PositionDto>();
				
				for (Position position   : lista) {					
					dto.add(new PositionDto(position) );
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
		
		Result<PositionDto> result = new Result<PositionDto>(); 	
		
		try {
			List<Position> lista = repository.findAll();

			if (lista != null) {
				
				List<PositionDto> dto = new ArrayList<PositionDto>();
				
				for (Position position   : lista) {					
					dto.add(new PositionDto(position) );
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
