package br.com.eneeyes.main.service;

import java.math.BigDecimal;
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
	
	@Autowired
	CompanyDetectorService companyDetectorService;
	
	@Override
	public BasicResult<?> save(PositionDto dto) {		
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param historic
	 * @return
	 * Atualiza posição do dispositivo, e delega checagem de limites
	 * para possiveis providências.
	 */
	public BasicResult<?> updatePositionAndCheckAlarmByHistoric(Historic historic) {

		Result<PositionDto> result = new Result<PositionDto>();
		
		Position position = new Position();
		
		position = repository.findByCompanyDetectorAndSensor(historic.getCompanyDetector(), historic.getSensor());
		
		if (position != null) {		
			
			position.setCompanyDetector(historic.getCompanyDetector());
			position.setLastUpdate(historic.getLastUpdate());
			position.setLastValue(historic.getValue());
			position.setHistoric(historic);
			
			AlarmType alarmType = positionAlarmService.checkAndUpdateAlarmsAndActions(position);
			position.setAlarmType(alarmType);
			
			repository.save(position);

			position.setCompanyDetector(historic.getCompanyDetector());
						
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
				position.setLastValue(BigDecimal.ZERO);
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
	
	public Position findByCompanyDetectorAndSensor(CompanyDetector companyDetector, Sensor sensor) {
		
		return repository.findByCompanyDetectorAndSensor(companyDetector, sensor);
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
				result.setMessage("Nenhuma Posição.");
			}
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public BasicResult<?> findByAreaId(Long uid) {
		Result<PositionDto> result = new Result<PositionDto>();
		
		List<CompanyDetector> lista = companyDetectorService.findByAreaId(uid);
		
		try {
			List<Position> postions = repository.findByCompanyDetectorIn(lista);
			
			if (lista != null) {
				
				List<PositionDto> dto = new ArrayList<PositionDto>();
				
				for (Position position   : postions) {					
					dto.add(new PositionDto(position) );
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
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}	
	
	public List<Position> listOffline() {
			
		Date now = new Date(); 
		Date minutesAgo = new Date(now.getTime() - (1000 * 60 * 5));
		
//		List<Position> list = repository.findByLastUpdateLessThan(minutesAgo);
//				
//		List<PositionDto> dto = new ArrayList<PositionDto>();
//		
//		for (Position item   : list) {					
//			dto.add(new PositionDto(item) );
//		}
//		
//		return list;
		
		return repository.findByLastUpdateLessThan(minutesAgo);
	}
}
