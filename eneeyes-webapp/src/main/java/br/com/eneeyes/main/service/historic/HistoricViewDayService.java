package br.com.eneeyes.main.service.historic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoric;
import br.com.eneeyes.main.repository.historic.HistoricViewDay30Repository;
import br.com.eneeyes.main.repository.historic.HistoricViewDayRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.HistoricResult;

@Service
public class HistoricViewDayService {
	
	@Autowired
	private HistoricViewDayRepository repository;
	
	@Autowired
	private HistoricViewDay30Repository repository30;
	
	public HistoricResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		HistoricResult<?> result = new HistoricResult<IHistoric>();
			
		try {
			
			Date dataFim = new Date(); 
			Date dataInicio = new Date(dataFim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));
			
			Page<IHistoric> page = null;
			
			page = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dataInicio, dataFim, new PageRequest(currentPage, lenPage));
			
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
	
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		HistoricResult<?> result = new HistoricResult<IHistoric>();
		
		Date date = new Date();
						
		int diffDaysIn = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
		int diffDaysOut = (int) ((date.getTime() - dateOut.getTime()) / (1000 * 60 * 60 * 24));
			
		try {
			
			if (diffDaysIn > 30 && diffDaysOut > 30) {
				
				result = getResults(repository30.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			else
			{
				result = getResults(repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage)));
			}
			
			Page<IHistoric> page = null;
			page = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage));
			
			result = getResults(page);
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

}
