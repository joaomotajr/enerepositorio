package br.com.eneeyes.main.service.historic;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.historic.Historic;
import br.com.eneeyes.main.repository.historic.HistoricRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;
import br.com.eneeyes.main.service.PositionService;
import br.com.eneeyes.main.service.buss.ProcessAlarmService;

@Service
public class HistoricService implements IService<HistoricDto> {
		
	@Autowired
	private HistoricRepository repository;
		
	@Autowired
	PositionService positionService;
	
	@Autowired
	ProcessAlarmService processAlarmService;
	
	public Boolean saveByPositionUid(Long uid, String strValue) {
		
		BigDecimal value = new BigDecimal(strValue);
		
		value = value.divide(new BigDecimal(100000));
		
		Boolean ret = false;		
		Position position = positionService.findByUid(uid);
		
		if(position != null ) {	
			Historic historic = new Historic();

			historic.setCompanyDetectorId(position.getCompanyDetector().getUid());
			historic.setSensorId(position.getSensor().getUid());
			historic.setLastUpdate(new Date());
			historic.setValue(value);
			
			repository.save(historic);
		
			try {
				processAlarmService.Execute(historic);
				ret = true;
				
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		return ret;
	}
		
	public Historic saveByPosition(Position position) {
		
		Historic historic = new Historic();
		
		historic.setCompanyDetectorId(position.getCompanyDetector().getUid());
		historic.setSensorId(position.getSensor().getUid());
		historic.setLastUpdate(new Date());
		historic.setValue(position.getLastValue());
		
		historic = repository.save(historic);
	
		return historic;
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
	
//	public BasicResult<?> findByCompanyDetector(Long companyDetectorId) {
//		Result<HistoricDto> result = new Result<HistoricDto>();
//		
//		try {
//			//List<Historic> lista = repository.findByCompanyDetector(companyDetector);			
//			List<Historic> lista = repository.findByCompanyDetectorId(companyDetectorId);
//			result = populateResult(lista);
//			
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
		
//	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, Integer periodo) {
//		Result<HistoricDto> result = new Result<HistoricDto>();
//		
//		Sensor sensor = new Sensor();
//		sensor.setUid(sensorId);
//		
//		try {
//						
//			Date fim = new Date(); 
//			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * periodo));
//			
//			List<Historic> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, inicio, fim);
//			result = populateResult(lista);
//			
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
	
//	public BasicResult<?> findByCompanyDetectorAndInterval(Long companyDetectorId, Integer periodo) {
//		Result<HistoricDto> result = new Result<HistoricDto>();
//		
//		try {
//						
//			Date fim = new Date(); 
//			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * periodo));
//			
//			List<Historic> lista = repository.findByCompanyDetectorIdAndLastUpdateBetween(companyDetectorId, inicio, fim);
//			result = populateResult(lista);
//			
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
		
//	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut) {
//		Result<HistoricDto> result = new Result<HistoricDto>();
//		
//		Sensor sensor = new Sensor();
//		sensor.setUid(sensorId);
//		
//		try {					
//			
//			List<Historic> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, dateIn, dateOut);			
//			result = populateResult(lista);
//			
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
	
//	private static Date addMonth(Date date, int qtde) {
//		
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.add(Calendar.MONTH, qtde);			
//		
//		return c.getTime();		
//	}
	
//	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(Long companyDetectorId, Long sensorId) {
//		Result<HistoricDto> result = new Result<HistoricDto>();
//		
//		Sensor sensor = new Sensor();
//		sensor.setUid(sensorId);
//		
//		try {
//						
//			Date fim = new Date();									
//			Date inicio = addMonth(fim, -1);
//			
//			List<Historic> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, inicio, fim);
//			result = populateResult(lista);
//			
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
	
//	private Result<HistoricDto> populateResult(List<Historic> lista) {
//		
//		Result<HistoricDto> result = new Result<HistoricDto>();
//		
//		if (lista != null) {
//			
//			List<HistoricDto> dto = new ArrayList<HistoricDto>();
//			
//			for (Historic historic   : lista) {					
//				dto.add(new HistoricDto(historic) );
//			}
//							
//			result.setList(dto);
//			result.setResultType( ResultMessageType.SUCCESS );
//			result.setMessage("Executado com sucesso.");
//		} else {
//			result.setIsError(true);
//			result.setResultType( ResultMessageType.ERROR );
//			result.setMessage("Nenhum Histórico.");
//		}
//		
//		return result;
//	} 
	
//	public Result<?> listAll() {
//		
//		Result<HistoricDto> result = new Result<HistoricDto>(); 	
//		
//		try {
//			List<Historic> lista = repository.findAll();
//			result = populateResult(lista);
//			
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;	
//
//	}

	public BasicResult<?> save(Long companyId, Long unitId, Long areaId, String companyDetectorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResult<?> save(HistoricDto dto) {
		 Result<HistoricDto> result = new Result<HistoricDto>();
				
		Historic historic = new Historic(dto);
		historic.setLastUpdate(new Date());
		
		historic = repository.save(historic);
		
		dto.setUid(historic.getUid());		
		result.setEntity(dto);
				
		try {
			processAlarmService.Execute(historic);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Unidade Gravada com Sucesso.");
			
		} catch (Exception e) {
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Erro ao gravar Historico.");				
			e.printStackTrace();
		}						
		
		return result;
	}
	
}
