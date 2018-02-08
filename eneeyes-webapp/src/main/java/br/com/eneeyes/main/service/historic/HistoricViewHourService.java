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
import br.com.eneeyes.main.model.historic.IHistoricGroup;
import br.com.eneeyes.main.repository.historic.HistoricViewDayAllRepository;
import br.com.eneeyes.main.repository.historic.HistoricViewHour30Repository;
import br.com.eneeyes.main.repository.historic.HistoricViewHourRepository;
import br.com.eneeyes.main.result.GroupResult;

@Service
public class HistoricViewHourService {
	
	@Autowired
	private HistoricViewHourRepository repository;
	
	@Autowired
	private HistoricViewHour30Repository repository30;

	@Autowired
	private HistoricViewDayAllRepository repositoryAll;
	
	public GroupResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		GroupResult<?> result = new GroupResult<IHistoricGroup>();

		try {
			
			Date dataFim = new Date();			 
			Date dataInicio  = new Date();
			
			if(intervalType ==  IntervalType.UM_MES) {
				
				dataInicio = addMonth(dataFim, -1);
			}
			else {
				dataInicio = new Date(dataFim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));
			}

			Page<IHistoricGroup> page = null;
			page = repository.findByCompanyDetectorIdAndLastUpdateBetweenPaginated(companyDetectorId, dataInicio, dataFim, new PageRequest(currentPage, lenPage));
			
			result = getResults(page);
			
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

	public GroupResult<?> getResults(Page<IHistoricGroup> page) {
		
		GroupResult<IHistoricGroup> result = new GroupResult<IHistoricGroup>();
		
		if (page != null) {
			
			List<IHistoricGroup> lista = new ArrayList<IHistoricGroup>();
			for (IHistoricGroup item : page) {
				
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
	
	public GroupResult<?> findByCompanyDetectorAndSensorAndIntervalHours(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {

		GroupResult<?> result = new GroupResult<IHistoricGroup>();
		
		Date date = new Date();
						
		int diffDaysIn = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
		int diffDaysOut = (int) ((date.getTime() - dateOut.getTime()) / (1000 * 60 * 60 * 24));
			
		try {
			
			Page<IHistoricGroup> page = null;
			
			if (diffDaysIn >= 30 && diffDaysOut >= 30) {
				page = repository30.findByCompanyDetectorIdAndLastUpdateBetweenPaginated(companyDetectorId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			}
			else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
		
				page = repository.findByCompanyDetectorIdAndLastUpdateBetweenPaginated(companyDetectorId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			}
			else
			{
				page = repositoryAll.findByCompanyDetectorIdAndLastUpdateBetweenPaginated(companyDetectorId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			}
			
			result = getResults(page);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
}
