package br.com.eneeyes.main.service.historic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.day.HistoricDayView;
import br.com.eneeyes.main.repository.historic.day.HistoricGroupDayViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class HistoricDayViewerService {		
	
	@Autowired
	private HistoricGroupDayViewRepository repositoryGroup; 
	
	public Result<?> findByCompanyDevicePreDefined(Long companyDeviceId, IntervalType intervalType) {
		Result<HistoricDayView> result = new Result<HistoricDayView>();
		
		try {						
			Date out = new Date(); 
			Date in = new Date(out.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			List<HistoricDayView> list = null;			
			if(intervalType ==  IntervalType.UMA_HORA) {				
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenH(companyDeviceId, in, out);
			} else if(intervalType ==  IntervalType.SEIS_HORAS || 
					intervalType ==  IntervalType.DOZE_HORAS || intervalType ==  IntervalType.UM_DIA) {								
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHA(companyDeviceId, in, out);
			} else if(intervalType ==  IntervalType.DOIS_DIAS || intervalType ==  IntervalType.SETE_DIAS) {
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHAB(companyDeviceId, in, out);
			} else if(intervalType ==  IntervalType.UM_MES) {																	
				in = Util.addMonth(out, -1);
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHABC(companyDeviceId, in, out);								
			}
			result.setList(list);				
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}

	
	public Result<?> findByCompanyDeviceAndIntervalGroupDays(Long companyDeviceId, Date in, Date out) {		
		Result<HistoricDayView> result = new Result<HistoricDayView>();
		
		try {		
			Date date = new Date();			
			long diff = date.getTime() - in.getTime();
			long diffHoursIn = diff / (60 * 60 * 1000);
			int diffDaysIn = (int) ((date.getTime() - in.getTime()) / (1000 * 60 * 60 * 24));
			int diffDaysOut = (int) ((date.getTime() - out.getTime()) / (1000 * 60 * 60 * 24));
						
			List<HistoricDayView> list = null;
			if(diffHoursIn <= 24) {						
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHA(companyDeviceId, in, out);								
			} else if (diffDaysIn > 30 && diffDaysOut > 30) {
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenD(companyDeviceId, in, out);				
//			} else if (diffDaysIn <= 2 && diffDaysOut <= 2) {
//				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHA(companyDeviceId, in, out);
			} else if (diffDaysIn <= 7 && diffDaysOut <= 7) {
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHAB(companyDeviceId, in, out);				
//			} else if(diffDaysIn <= 7) {
//				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHAB(companyDeviceId, in, out);				
			} else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHABC(companyDeviceId, in, out);				
			} else {
				list = repositoryGroup.findByCompanyDeviceIdAndLastUpdateBetweenHABCD(companyDeviceId, in, out);
			}
			result.setList(list);
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}
}
