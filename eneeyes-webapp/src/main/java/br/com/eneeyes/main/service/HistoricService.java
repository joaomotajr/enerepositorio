package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Historic;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.HistoricRepository;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class HistoricService implements IService<HistoricDto> {
	private static final int PAGE_SIZE = 50;
		
	@Inject
	private HistoricRepository repository;
	
	@Inject
	private PositionRepository positionRepository;

	@Override
	public BasicResult<?> save(HistoricDto dto) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		Historic historic = new Historic(dto);
		historic.setLastUpdate(new Date());
		historic = repository.save(historic);
		
		//TODO Está perdendo os valores do CompanyDetector - Checar 
		historic.setCompanyDetector(new CompanyDetector(dto.getCompanyDetectorDto()) );
		updatePosition(historic);				
				
		dto.setUid(historic.getUid());
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}
	
	private void updatePosition(Historic historic) {
					
		Position position = new Position();
		
		position = positionRepository.findByCompanyDetectorAndSensor(historic.getCompanyDetector(), historic.getSensor()); 

		if (position != null) {		

			position.setLastUpdate(historic.getLastUpdate());
			position.setLastValue(historic.getValue());
			
			positionRepository.save(position);
		}
		else {
			// TODO Criar Log de Erros e Incosistências do Sistema
		}
	}


	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResult<?> findOne(Long uid) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		try {
			Historic item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new HistoricDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Histórico.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}	
	
	public BasicResult<?> findByCompanyDetector(Long uid) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		try {
			List<Historic> lista = repository.findByCompanyDetector(companyDetector);			
			result = populateResult(lista);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public BasicResult<?> findByCompanyDetectorAndInterval(Long uid, Integer periodo) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		try {
						
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() + (1000 * 60 * 60 * periodo));
			
			List<Historic> lista = repository.findByCompanyDetectorAndLastUpdateBetween(companyDetector, inicio, fim);			
			result = populateResult(lista);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, Integer periodo) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(companyDetectorId);
		
		Sensor sensor = new Sensor();
		sensor.setUid(sensorId);
		
		try {
						
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * periodo));
			
			List<Historic> lista = repository.findByCompanyDetectorAndSensorAndLastUpdateBetween(companyDetector, sensor, inicio, fim);			
			result = populateResult(lista);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	private Result<HistoricDto> populateResult(List<Historic> lista) {
		
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		if (lista != null) {
			
			List<HistoricDto> dto = new ArrayList<HistoricDto>();
			
			for (Historic historic   : lista) {					
				dto.add(new HistoricDto(historic) );
			}
							
			result.setList(dto);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
		} else {
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Nenhum Histórico.");
		}
		
		return result;
	} 

	
	public Result<?> listAll() {
		
		Result<HistoricDto> result = new Result<HistoricDto>(); 	
		
		try {
			List<Historic> lista = repository.findAll();
			result = populateResult(lista);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public Result<?> listPageByCompanyDetector(Long uid, Integer pageNumber) {
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		Result<HistoricDto> result = new Result<HistoricDto>(); 	
		
		try {
			Page<Historic> lista = repository.findByCompanyDetector(companyDetector, new PageRequest(pageNumber, PAGE_SIZE));
									
			if (lista != null) {
				
				List<HistoricDto> dto = new ArrayList<HistoricDto>();
				
				for (Historic historic   : lista) {					
					dto.add(new HistoricDto(historic) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Histórico.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}

	
}
