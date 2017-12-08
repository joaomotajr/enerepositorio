package br.com.eneeyes.main.service.historic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoric;
import br.com.eneeyes.main.repository.historic.HistoricCRepository;
import br.com.eneeyes.main.repository.historic.HistoricDRepository;
import br.com.eneeyes.main.repository.historic.HistoricHABCRepository;
import br.com.eneeyes.main.repository.historic.HistoricHABRepository;
import br.com.eneeyes.main.repository.historic.HistoricHARepository;
import br.com.eneeyes.main.repository.historic.HistoricViewRepository;
import br.com.eneeyes.main.result.GroupResult;

@Service
public class HistoricViewService {
	
	@Autowired
	private HistoricViewRepository repositoryView;
	
	@Autowired
	private HistoricHARepository repositoryHA;
		
	@Autowired
	private HistoricHABRepository repositoryHAB;
	
	@Autowired
	private HistoricHABCRepository repositoryHABC;
	
	@Autowired
	private HistoricCRepository repositoryC;
	
	@Autowired
	private HistoricDRepository repositoryD;
	
	public GroupResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		GroupResult<?> result = new GroupResult<IHistoric>();
			
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
																	
				inicio = Util.addMonth(fim, -1);								
				page= repositoryHABC.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
			}			
			
			result = getResults(page);			
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	private GroupResult<?> getResults(Page<IHistoric> page) {
		
		GroupResult<IHistoric> result = new GroupResult<IHistoric>();
		
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
	
	public GroupResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		
		GroupResult<?> result = new GroupResult<IHistoric>();
		
		try {
		
			Date date = new Date();
			
			long diff = date.getTime() - dateIn.getTime();
			long diffHoursIn = diff / (60 * 60 * 1000);
			int diffDaysIn = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
			int diffDaysOut = (int) ((date.getTime() - dateOut.getTime()) / (1000 * 60 * 60 * 24));
			
			if(diffHoursIn <= 24) {
				
				result = getResults(repositoryHA.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			else if (diffDaysIn > 30 && diffDaysOut > 30) {
				
				result = getResults(repositoryD.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			else if (diffDaysIn <= 2 && diffDaysOut <= 2) {
				
				result = getResults(repositoryHA.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));			
			}
			else if (diffDaysIn <= 7 && diffDaysOut <= 7) {
				
				result = getResults(repositoryHAB.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));			
			}						
			else if(diffDaysIn <= 7) {
				
				result = getResults(repositoryHAB.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			else if (diffDaysIn > 7 && diffDaysIn <= 30 && diffDaysOut > 7 && diffDaysOut <= 30) {
				result = getResults(repositoryHABC.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			else if(diffDaysIn <= 30) {
				
				result = getResults(repositoryC.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			
			else {
				
				result = getResults(repositoryHABC.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));			
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;
	}

}
