package br.com.eneeyes.main.service.views;

import java.text.SimpleDateFormat;
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
			
			SimpleDateFormat sdfr = new SimpleDateFormat("yyyyddMM");
			
			Date dataFim = new Date(); 
			Date dataInicio = new Date(dataFim.getTime() - (1000 * 60 * 60 * periodo));
			
			Long diaFim = Long.parseLong(sdfr.format(dataFim));
			Long diaInicio = Long.parseLong(sdfr.format(dataInicio));
			
			List<HistoricViewHour> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(
					companyDetectorId, sensorId, diaInicio, diaFim, getHours(dataInicio), getHours(dataFim));
			
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
			
			SimpleDateFormat sdfr = new SimpleDateFormat("yyyyddMM");
					
			Long diaFim = Long.parseLong(sdfr.format(dateOut));
			Long diaInicio = Long.parseLong(sdfr.format(dateIn));
			
			List<HistoricViewHour> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(
					companyDetectorId, sensorId, diaInicio, diaFim, getHours(dateIn), getHours(dateOut));
				
			
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
			
			SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMMdd");
			
			Date dataFim = new Date(); 
			Date dataInicio = addMonth(dataFim, -1);
			
			Long diaFim = Long.parseLong(sdfr.format(dataFim));
			Long diaInicio = Long.parseLong(sdfr.format(dataInicio));
			
			List<HistoricViewHour> lista = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(
					companyDetectorId, sensorId, diaInicio, diaFim, getHours(dataInicio), getHours(dataFim));
			
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
	
	private static int getHours(Date date) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);					
		
		return c.get(Calendar.HOUR_OF_DAY);
	}

}
