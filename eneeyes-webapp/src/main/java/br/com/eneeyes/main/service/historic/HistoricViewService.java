package br.com.eneeyes.main.service.historic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoric;
import br.com.eneeyes.main.repository.historic.HistoricCRepository;
import br.com.eneeyes.main.repository.historic.HistoricHABCRepository;
import br.com.eneeyes.main.repository.historic.HistoricHABRepository;
import br.com.eneeyes.main.repository.historic.HistoricRepository;
import br.com.eneeyes.main.repository.historic.HistoricViewRepository;
import br.com.eneeyes.main.result.HistoricResult;

@Service
public class HistoricViewService {
	
	@Autowired
	private HistoricViewRepository repositoryView;
	
	@Autowired
	private HistoricRepository repositoryHA;
		
	@Autowired
	private HistoricHABRepository repositoryHAB;
	
	@Autowired
	private HistoricHABCRepository repositoryHABC;
	
	@Autowired
	private HistoricCRepository repositoryC;

	
	public HistoricResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		HistoricResult<?> result = new HistoricResult<IHistoric>();
			
		try {
						
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			Page<IHistoric> page = null;
			
			if(intervalType ==  IntervalType.UMA_HORA) {
				
				page= repositoryView.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));							
			}
			else if(intervalType ==  IntervalType.SEIS_HORAS || intervalType ==  IntervalType.DOZE_HORAS || intervalType ==  IntervalType.UM_DIA) {
				
				page= repositoryHA.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
			}
			else if(intervalType ==  IntervalType.DOIS_DIAS || intervalType ==  IntervalType.SETE_DIAS) {
				
				page= repositoryHAB.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
			}
			else if(intervalType ==  IntervalType.UM_MES) {
																	
				inicio = addMonth(fim, -1);								
				page= repositoryHABC.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
			}			
			
			result = getResults(page);			
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	
	public HistoricResult<?> getResults(Page<IHistoric> page) {
		
		HistoricResult<IHistoric> result = new HistoricResult<IHistoric>();
		
		if (page != null) {
			
			List<IHistoric> lista = new ArrayList<IHistoric>();
			for (IHistoric item : page) {
				
				lista.add(item);					
			}
			
			result.setFirstPage(page.isFirstPage());
			result.setLastPage(page.isLastPage());
			result.setTotalList(new Long(page.getTotalElements()).intValue());			
			result.setCountPages(page.getTotalPages());
			
			result.setList(lista);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");	
		} else {
			
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Nenhum Histórico.");
		}
		
		return result;
		
	}
	
	
	private static Date addMonth(Date date, int qtde) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, qtde);			
		
		return c.getTime();		
	}
	
	public HistoricResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		
		HistoricResult<?> result = new HistoricResult<IHistoric>();
		
		Date date = new Date();
		
		long diff = date.getTime() - dateIn.getTime();
		long diffHours = diff / (60 * 60 * 1000);
		int diffDays = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
		
		if(diffHours <= 1) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.UMA_HORA, currentPage, lenPage);
		}
		else if(diffHours <= 6) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.SEIS_HORAS, currentPage, lenPage);
		}
		else if(diffHours <= 12) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.DOZE_HORAS, currentPage, lenPage);
		}
		else if(diffHours <= 24) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.UM_DIA, currentPage, lenPage);
		}
		else if(diffDays <= 2) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.DOIS_DIAS, currentPage, lenPage);
		}
		else if(diffDays <= 7) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.SETE_DIAS, currentPage, lenPage);
		}
		else if(diffDays <= 30) {
			result = findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, IntervalType.UM_MES, currentPage, lenPage);
		}
		else {
			diff = date.getTime() - dateOut.getTime();
			
		}
		
		
		
		return result;
	}

}
