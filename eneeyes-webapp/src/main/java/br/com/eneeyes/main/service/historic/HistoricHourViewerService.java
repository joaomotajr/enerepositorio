package br.com.eneeyes.main.service.historic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.main.dto.register.UidDto;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoricGroup;
import br.com.eneeyes.main.model.historic.hour.HistoricHourView;
import br.com.eneeyes.main.repository.historic.hour.HistoricAHourViewRepository;
import br.com.eneeyes.main.repository.historic.hour.HistoricBHourViewRepository;
import br.com.eneeyes.main.repository.historic.hour.HistoricCHourViewRepository;
import br.com.eneeyes.main.repository.historic.hour.HistoricDHourViewRepository;
import br.com.eneeyes.main.repository.historic.hour.HistoricGroupHourViewRepository;
import br.com.eneeyes.main.repository.historic.hour.HistoricHourViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class HistoricHourViewerService {		
	@Autowired
	private HistoricHourViewRepository repository;
		
	@Autowired
	private HistoricAHourViewRepository repositoryA;
		
	@Autowired
	private HistoricBHourViewRepository repositoryB;
	
	@Autowired
	private HistoricCHourViewRepository repositoryC;
	
	@Autowired
	private HistoricDHourViewRepository repositoryD;
	
	@Autowired
	private HistoricGroupHourViewRepository repositoryGroup; 
		
	public Result<?> findByCompanyDevicePreDefined(Long companyDeviceId, IntervalType intervalType) {
		Result<HistoricHourView> result = new Result<HistoricHourView>();
			
		try {						
			Date out = new Date(); 
			Date in = new Date(out.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			List<HistoricHourView> list = null;			
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

	
	public Result<?> findByCompanyDeviceAndIntervalGroupHours(Long companyDeviceId, Date in, Date out) {
		
		Result<HistoricHourView> result = new Result<HistoricHourView>();
		
		try {
		
			Date date = new Date();			
			long diff = date.getTime() - in.getTime();
			long diffHoursIn = diff / (60 * 60 * 1000);
			int diffDaysIn = (int) ((date.getTime() - in.getTime()) / (1000 * 60 * 60 * 24));
			int diffDaysOut = (int) ((date.getTime() - out.getTime()) / (1000 * 60 * 60 * 24));
			
			List<HistoricHourView> list = null;
			if (diffDaysIn <= 1 && diffDaysOut <= 1 && diffHoursIn <= 24) {						
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
	
	public Result<?> findByCompanyDevicesInAndIntervalGroupHours(UidDto ids, Date in, Date out) {

		Result<IHistoricGroup> result = new Result<IHistoricGroup>();
		
		try {
		
			Date date = new Date();
			
			long diff = date.getTime() - in.getTime();
			long diffHoursIn = diff / (60 * 60 * 1000);
			int diffDaysIn = (int) ((date.getTime() - in.getTime()) / (1000 * 60 * 60 * 24));
			int diffDaysOut = (int) ((date.getTime() - out.getTime()) / (1000 * 60 * 60 * 24));
			
			List<IHistoricGroup> list = null;

			List<Long> companyDeviceIds = new ArrayList<>();
			companyDeviceIds.addAll(ids.getListId());
			if (diffDaysIn <= 1 && diffDaysOut <= 1 && diffHoursIn <= 24) {								
				list = repository.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
				list.addAll(repositoryA.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));				
			} else if (diffDaysIn > 30 && diffDaysOut > 30) {
				list = repositoryD.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);				
//			} else if (diffDaysIn <= 2 && diffDaysOut <= 2) {
//				list = repository.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
//				list.addAll(repositoryA.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
			} else if (diffDaysIn <= 7 && diffDaysOut <= 7) {
				list = repository.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
				list.addAll(repositoryA.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
				list.addAll(repositoryB.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
//			} else if(diffDaysIn <= 7) {
//				list = repository.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
//				list.addAll(repositoryA.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
//				list.addAll(repositoryB.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
			} else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
				list = repository.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
				list.addAll(repositoryA.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
				list.addAll(repositoryB.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
				list.addAll(repositoryC.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
//			} else if(diffDaysIn <= 30) {
//				list = repositoryC.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
			} else {
				list = repository.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out);
				list.addAll(repositoryA.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
				list.addAll(repositoryB.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
				list.addAll(repositoryC.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));							
				list.addAll(repositoryD.findByCompanyDeviceInAndLastUpdateBetween(companyDeviceIds, in, out));
			}
			result.setList(list);
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;
	}
}
