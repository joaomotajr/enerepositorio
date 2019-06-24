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
import br.com.eneeyes.main.model.historic.IHistoricHourGroup;
import br.com.eneeyes.main.repository.historic.HistoricViewDay30Repository;
import br.com.eneeyes.main.repository.historic.HistoricViewDayAllRepository;
import br.com.eneeyes.main.repository.historic.HistoricViewDayRepository;
import br.com.eneeyes.main.result.GroupResult;

@Service
public class HistoricViewDayService {
	
	@Autowired
	private HistoricViewDayRepository repository;
	
	@Autowired
	private HistoricViewDay30Repository repository30;
	
	@Autowired
	private HistoricViewDayAllRepository repositoryAll;
	
	public GroupResult<?> findByCompanyDeviceAndInterval(Long companyDeviceId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		GroupResult<?> result = new GroupResult<IHistoricHourGroup>();
			
		try {
			
			Date dataFim = new Date();			 
			Date dataInicio  = new Date();			
			if(intervalType ==  IntervalType.UM_MES) {				
				dataInicio = addMonth(dataFim, -1);
			} else {
				dataInicio = new Date(dataFim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));
			}			
			
			Page<IHistoricHourGroup> page = null;
			page = repository.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dataInicio, dataFim, new PageRequest(currentPage, lenPage));
			
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
	
	public GroupResult<?> getResults(Page<IHistoricHourGroup> page) {
		
		GroupResult<IHistoricHourGroup> result = new GroupResult<IHistoricHourGroup>();
		
		if (page != null) {
			
			List<IHistoricHourGroup> lista = new ArrayList<IHistoricHourGroup>();
			for (IHistoricHourGroup item : page) {				
				lista.add(item);					
			}
			
			result.setFirstPage(page.isFirst());
			result.setLastPage(page.isLast());
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
	
	public GroupResult<?> findByCompanyDeviceAndIntervalDays(Long companyDeviceId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		GroupResult<?> result = new GroupResult<IHistoricHourGroup>();
		
		Date date = new Date();
						
		int diffDaysIn = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
		int diffDaysOut = (int) ((date.getTime() - dateOut.getTime()) / (1000 * 60 * 60 * 24));
			
		try {
			
			Page<IHistoricHourGroup> page = null;
			
			if (diffDaysIn >= 30 && diffDaysOut >= 30) {				
				page = repository30.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			} else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
				page = repository.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			} else			{
				page = repositoryAll.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			}			
			result = getResults(page);			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}

}
