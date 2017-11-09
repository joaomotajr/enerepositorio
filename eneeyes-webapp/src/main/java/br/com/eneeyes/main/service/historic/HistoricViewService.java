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
import br.com.eneeyes.main.model.historic.HistoricC;
import br.com.eneeyes.main.model.historic.IHistoric;
import br.com.eneeyes.main.repository.historic.HistoricARepository;
import br.com.eneeyes.main.repository.historic.HistoricBRepository;
import br.com.eneeyes.main.repository.historic.HistoricCRepository;
import br.com.eneeyes.main.repository.historic.HistoricHABRepository;
import br.com.eneeyes.main.repository.historic.HistoricRepository;
import br.com.eneeyes.main.repository.historic.HistoricViewRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.HistoricResult;
import br.com.eneeyes.main.result.Result;

@Service
public class HistoricViewService {
	
	@Autowired
	private HistoricViewRepository repositoryView;
	
	@Autowired
	private HistoricRepository repositoryHA;
	
	@Autowired
	private HistoricARepository repositoryA;
	
	@Autowired
	private HistoricHABRepository repositoryHAB;
	
	@Autowired
	private HistoricBRepository repositoryB;
	
	@Autowired
	private HistoricCRepository repositoryC;
	
	
//	public Result<?> listAll() {
//		
//		Result<HistoricView> result = new Result<HistoricView>();
//		
//		try {
//			List<HistoricView> lista = repositoryView.findAll();
//
//			if (lista != null) {
//				
//				result.setList(lista);				
//				result.setResultType( ResultMessageType.SUCCESS );
//				result.setMessage("Executado com sucesso.");
//			} else {
//				result.setIsError(true);
//				result.setResultType( ResultMessageType.ERROR );
//				result.setMessage("Nenhuma Compania.");
//			}
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;	
//		
//	}
	
	public HistoricResult<?> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, Integer periodo, Integer currentPage, Integer lenPage) {
		HistoricResult<?> result = new HistoricResult<IHistoric>();
			
		try {
						
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * periodo));								
			
			Page<IHistoric> page = null;
			
			if(periodo <= 1) {
				
				page= repositoryView.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));							
			}
			else if(periodo <= 24) {
				
				page= repositoryHA.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
			}
			else if(periodo <= 168) {
				
				page= repositoryHAB.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
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
	
	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(Long companyDetectorId, Long sensorId) {
		Result<HistoricC> result = new Result<HistoricC>();
		
		try {
						
			Date fim = new Date();									
			Date inicio = addMonth(fim, -1);
			
			List<HistoricC> lista = repositoryC.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, inicio, fim);			
			
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
	
//	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut) {
//	Result<HistoricView> result = new Result<HistoricView>();
//		
//	try {					
//		
//		List<HistoricView> lista = new ArrayList<HistoricView>();
//		// = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetween(companyDetectorId, sensorId, dateIn, dateOut);
//		
//		if (lista != null) {
//			
//			result.setList(lista);
//			result.setResultType( ResultMessageType.SUCCESS );
//			result.setMessage("Executado com sucesso.");	
//		} else {
//			
//			result.setIsError(true);
//			result.setResultType( ResultMessageType.ERROR );
//			result.setMessage("Nenhum Histórico.");
//		}
//		
//	} catch (Exception e) {
//		result.setIsError(true);
//		result.setMessage(e.getMessage());
//	}
//	
//	return result;
//}

}
