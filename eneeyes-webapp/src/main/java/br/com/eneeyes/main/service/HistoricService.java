package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Historic;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.HistoricRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class HistoricService implements IService<HistoricDto> {
	//private static final int PAGE_SIZE = 50;
		
	@Autowired
	private HistoricRepository repository;
		
	@Autowired
	PositionService positionService;
	
	public Boolean saveByPositionUid(Long uid, String strValue) {
		
		BigDecimal value = new BigDecimal(strValue);
		
		value = value.divide(new BigDecimal(100000));
		
		Boolean ret = false;		
		Position position = positionService.findByUid(uid);
		
		if(position != null ) {	
			Historic historic = new Historic();
			historic.setCompanyDetector(position.getCompanyDetector());
			historic.setSensor(position.getSensor());		
			historic.setLastUpdate(new Date());
			historic.setValue(value);					
		
			try {
				this.save(new HistoricDto(historic));
				ret = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return ret;
	}

	@Override
	public BasicResult<?> save(HistoricDto dto) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		Historic historic = new Historic(dto);
		historic.setLastUpdate(new Date());
		historic = repository.save(historic);
		
		historic.setCompanyDetector(new CompanyDetector(dto.getCompanyDetectorDto()) );
		updatePosition(historic);				
				
		dto.setUid(historic.getUid());
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}
	
	private void updatePosition(Historic historic) {

		positionService.updatePosition(historic);
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
	
	public BasicResult<?> findByCompanyDetector(Long companyDetectorId) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(companyDetectorId);
		
		try {
			List<Historic> lista = repository.findByCompanyDetector(companyDetector);			
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
	
	public BasicResult<?> findByCompanyDetectorAndInterval(Long companyDetectorId, Integer periodo) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(companyDetectorId);
		
		try {
						
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * periodo));
			
			List<Historic> lista = repository.findByCompanyDetectorAndLastUpdateBetween(companyDetector, inicio, fim);			
			result = populateResult(lista);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(companyDetectorId);
		
		Sensor sensor = new Sensor();
		sensor.setUid(sensorId);
		
		try {					
			
			List<Historic> lista = repository.findByCompanyDetectorAndSensorAndLastUpdateBetween(companyDetector, sensor, dateIn, dateOut);			
			result = populateResult(lista);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	private static Date addMonth(Date date, int qtde) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, qtde);			
		
		return c.getTime();		
	}
	
	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(Long companyDetectorId, Long sensorId) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(companyDetectorId);
		
		Sensor sensor = new Sensor();
		sensor.setUid(sensorId);
		
		try {
						
			Date fim = new Date();									
			Date inicio = addMonth(fim, -1);
			
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

	public BasicResult<?> save(Long companyId, Long unitId, Long areaId, String companyDetectorName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
