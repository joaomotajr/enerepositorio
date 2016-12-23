package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.PositionDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class PositionService implements IService<PositionDto> {
	//private static final int PAGE_SIZE = 50;
	
	@Inject
	private PositionRepository repository;	

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
	
	private void checkAlarm(Position position) {
		
		PositionAlarm positionAlarm = new PositionAlarm();
		positionAlarm.setUid((long) 1);
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
	
//	public BasicResult<?> findBySensor(Long uid) {
//		Result<PositionDto> result = new Result<PositionDto>();
//		
//		Sensor sensor = new Sensor();
//		sensor.setUid(uid);
//		
//		try {
//			Position item = repository.findBySensor(sensor);
//
//			if (item != null) {
//				
//				result.setEntity(new PositionDto(item));
//				
//				result.setResultType( ResultMessageType.SUCCESS );
//				result.setMessage("Executado com sucesso.");
//			} else {
//				result.setIsError(true);
//				result.setResultType( ResultMessageType.ERROR );
//				result.setMessage("Nenhuma Posição.");
//			}
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
	
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
	
//	public Result<?> listPageByArea(Long unitId, Integer pageNumber) {
//		
//		Result<PositionDto> result = new Result<PositionDto>(); 	
//		
//		try {
//			Page<Position> lista = repository.findByAreaId(unitId, new PageRequest(pageNumber, PAGE_SIZE));
//			
//			if (lista != null) {
//				
//				List<PositionDto> dto = new ArrayList<PositionDto>();
//				
//				for (Position position   : lista) {					
//					dto.add(new PositionDto(position) );
//				}
//								
//				result.setList(dto);
//				result.setResultType( ResultMessageType.SUCCESS );
//				result.setMessage("Executado com sucesso.");
//			} else {
//				result.setIsError(true);
//				result.setResultType( ResultMessageType.ERROR );
//				result.setMessage("Nenhuma area.");
//			}
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;	
//		
//	}

	
}
