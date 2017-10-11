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
import br.com.eneeyes.main.model.views.PositionView;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.repository.views.PositionViewRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class PositionService implements IService<PositionDto> {
	
	@Autowired
	private PositionRepository repository;
	
	@Autowired
	private PositionViewRepository repositoryView;
	
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
		
		PositionView positionView = repositoryView.findByCompanyDetectorIdAndSensorId(historic.getCompanyDetector().getUid(), historic.getSensor().getUid());
		
		if (position != null) {		
			
			
			repository.updatePositionById(positionView.getAlarmType(), positionView.getLastValue(), positionView.getLastUpdate(), historic, positionView.getUid()); 
						
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
		}		
		
		return result;		
	}
	
	public void updatePositionAlarmType(AlarmType alarmType, Long companyDetectorId, Long sensorId) {
		
		repository.updateAlarmType(alarmType, companyDetectorId, sensorId);
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
				position.setAlarmType(AlarmType.WITHOUT);
				
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
		Result<PositionView> result = new Result<PositionView>();
		
		try {
			List<PositionView> lista = repositoryView.findByCompanyDetectorId(uid);
			
			if (lista != null) {				
								
				result.setList(lista);
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
	
	public List<Position> listOffline(Integer minutes) {
			
		Date now = new Date(); 
		Date minutesAgo = new Date(now.getTime() - (1000 * 60 * minutes));
		
		List<AlarmType> withoutOrOffAlarms = new ArrayList<AlarmType>();
		
		withoutOrOffAlarms.add(AlarmType.WITHOUT);
				
		return repository.findByLastUpdateLessThanAndAlarmTypeNotIn(minutesAgo, withoutOrOffAlarms);
	}
}
