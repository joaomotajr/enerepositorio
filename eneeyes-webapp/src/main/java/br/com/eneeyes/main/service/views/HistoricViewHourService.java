package br.com.eneeyes.main.service.views;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.HistoricViewHour;
import br.com.eneeyes.main.repository.views.HistoricViewHourRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Named
public class HistoricViewHourService {
	
	@Inject
	private HistoricViewHourRepository repository;
	
	public Result<?> listAll() {
		
		Result<HistoricViewHour> result = new Result<HistoricViewHour>();
		
		try {
			List<HistoricViewHour> lista = repository.findAll();

			if (lista != null) {
				
				result.setList(lista);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}
	
	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, Integer periodo) {
		Result<HistoricViewHour> result = new Result<HistoricViewHour>();
			
		try {
			
			Date dataFim = new Date(); 
			Date dataInicio = new Date(dataFim.getTime() - (1000 * 60 * 60 * periodo));
			
			List<HistoricViewHour> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, dataInicio, dataFim);
			
			if (lista != null) {
			
				result.setList(lista);
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
	
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut) {
		Result<HistoricViewHour> result = new Result<HistoricViewHour>();
			
		try {				
			
			List<HistoricViewHour> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, dateIn, dateOut);
			
			if (lista != null) {
				
				result.setList(lista);
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
	
	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(Long companyDetectorId, Long sensorId) {
		Result<HistoricViewHour> result = new Result<HistoricViewHour>();
		
		try {
			
			Date dataFim = new Date(); 
			Date dataInicio = addMonth(dataFim, -1);
			
			List<HistoricViewHour> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, dataInicio, dataFim);
			
			if (lista != null) {
				
				result.setList(lista);
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
	
	private static Date addMonth(Date date, int qtde) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, qtde);			
		
		return c.getTime();		
	}

}