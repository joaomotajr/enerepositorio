package br.com.eneeyes.main.service.historic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoricGroup;
import br.com.eneeyes.main.repository.historic.day.HistoricADayViewRepository;
import br.com.eneeyes.main.repository.historic.day.HistoricBDayViewRepository;
import br.com.eneeyes.main.repository.historic.day.HistoricCDayViewRepository;
import br.com.eneeyes.main.repository.historic.day.HistoricDDayViewRepository;
import br.com.eneeyes.main.repository.historic.day.HistoricDayViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class HistoricDayViewerService {		
	@Autowired
	private HistoricDayViewRepository repository;
		
	@Autowired
	private HistoricADayViewRepository repositoryA;
	
	@Autowired
	private HistoricBDayViewRepository repositoryB;
	
	@Autowired
	private HistoricCDayViewRepository repositoryC;
	
	@Autowired
	private HistoricDDayViewRepository repositoryD;
	
	public Result<?> findByCompanyDeviceAndInterval(Long companyDeviceId, IntervalType intervalType) {
		Result<IHistoricGroup> result = new Result<IHistoricGroup>();
			
		try {						
			Date out = new Date(); 
			Date in = new Date(out.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			List<IHistoricGroup> list = null;
			
			if(intervalType ==  IntervalType.UMA_HORA) {				
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
			}
			else if(intervalType ==  IntervalType.SEIS_HORAS || intervalType ==  IntervalType.DOZE_HORAS || intervalType ==  IntervalType.UM_DIA) {				
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			}
			else if(intervalType ==  IntervalType.DOIS_DIAS || intervalType ==  IntervalType.SETE_DIAS) {
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			}
			else if(intervalType ==  IntervalType.UM_MES) {																	
				in = Util.addMonth(out, -1);
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));				
			}			
			result.setList(list);			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}

	
	public Result<?> findByCompanyDeviceAndIntervalDays(Long companyDeviceId, Date in, Date out) {
		
		Result<IHistoricGroup> result = new Result<IHistoricGroup>();
		
		try {
		
			Date date = new Date();
			
			long diff = date.getTime() - in.getTime();
			long diffHoursIn = diff / (60 * 60 * 1000);
			int diffDaysIn = (int) ((date.getTime() - in.getTime()) / (1000 * 60 * 60 * 24));
			int diffDaysOut = (int) ((date.getTime() - out.getTime()) / (1000 * 60 * 60 * 24));
			
			List<IHistoricGroup> list = null;
			if(diffHoursIn <= 24) {								
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));				
			} else if (diffDaysIn > 30 && diffDaysOut > 30) {
				list = repositoryD.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);				
			} else if (diffDaysIn <= 2 && diffDaysOut <= 2) {
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else if (diffDaysIn <= 7 && diffDaysOut <= 7) {
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else if(diffDaysIn <= 7) {
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else if (diffDaysIn > 7 && diffDaysIn <= 30 && diffDaysOut > 7 && diffDaysOut <= 30) {
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else if(diffDaysIn <= 30) {
				list = repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
			} else {
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));							
				list.addAll(repositoryD.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;
	}

}
