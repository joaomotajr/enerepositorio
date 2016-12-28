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
		Result<PositionDto> result = new Result<PositionDto>();
		
		Position position = new Position(dto);
		position = repository.save(position);
		
		checkAlarm(position);
		
		dto.setUid(position.getUid());
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}

	public BasicResult<?> updatePosition(Historic historic) {

		Result<PositionDto> result = new Result<PositionDto>();
		
		Position position = new Position();
		
		position = repository.findByCompanyDetectorAndSensor(historic.getCompanyDetector(), historic.getSensor()); 

		if (position != null) {		

			position.setLastUpdate(historic.getLastUpdate());
			position.setLastValue(historic.getValue());
			
			repository.save(position);
			
			//TODO Position não possui CompanyDetector, o objeto é Lazy pq é chave de pesquisa
			position.setCompanyDetector(historic.getCompanyDetector());
			
			checkAlarm(position);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
		}
		else {		
			
			//TODO Testar
			//createInitialPosition(historic.getCompanyDetector());
			
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Inconsistencia na Posição.");
		}		
		
		return result;		
	}	
	
	private void checkAlarm(Position position) {
		
		positionAlarmService.checkAlarmLimits(position);
	}
	
	@SuppressWarnings("unused")
	private void createInitialPosition(CompanyDetector companyDetector) {
		
		Set<Sensor> sensors = companyDetector.getDetector().getSensors();
		
		for (Sensor sensor   : sensors) {
		
			if(repository.countByCompanyDetectorAndSensor(companyDetector, sensor) == 0) {
	
				Position position = new Position();	

				position.setCompanyDetector(companyDetector);
				position.setSensor(sensor);
				position.setLastUpdate(new Date());
				position.setLastValue((double) 0);
				
				repository.save(position);
			}
		}
	}

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
