package br.com.eneeyes.main.service.historic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoric;
import br.com.eneeyes.main.repository.historic.HistoricARepository;
import br.com.eneeyes.main.repository.historic.HistoricBRepository;
import br.com.eneeyes.main.repository.historic.HistoricCRepository;
import br.com.eneeyes.main.repository.historic.HistoricDRepository;
import br.com.eneeyes.main.repository.historic.HistoricRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class HistoricViewerService {		
	@Autowired
	private HistoricRepository repository;
		
	@Autowired
	private HistoricARepository repositoryA;
	
	@Autowired
	private HistoricBRepository repositoryB;
	
	@Autowired
	private HistoricCRepository repositoryC;
	
	@Autowired
	private HistoricDRepository repositoryD;
	
	public Result<?> findByCompanyDevicePreDefined(Long companyDeviceId, IntervalType intervalType) {
		Result<IHistoric> result = new Result<IHistoric>();
			
		try {						
			Date out = new Date(); 
			Date in = new Date(out.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			List<IHistoric> list = null;
			
			if(intervalType ==  IntervalType.UMA_HORA) {				
				list = repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
			}
			else if(intervalType ==  IntervalType.SEIS_HORAS || intervalType ==  IntervalType.DOZE_HORAS || intervalType ==  IntervalType.UM_DIA) {				
				list = repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			}
			else if(intervalType ==  IntervalType.DOIS_DIAS || intervalType ==  IntervalType.SETE_DIAS) {
				list = repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			}
			else if(intervalType ==  IntervalType.UM_MES) {																	
				in = Util.addMonth(out, -1);
				list = repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));				
			}			
			result.setList(list);
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}

	
	public Result<?> findByCompanyDeviceAndInterval(Long companyDeviceId, Date in, Date out) {
		
		Result<IHistoric> result = new Result<IHistoric>();
		
		try {
		
			Date date = new Date();
			
			long diff = date.getTime() - in.getTime();
			long diffHoursIn = diff / (60 * 60 * 1000);
			int diffDaysIn = (int) ((date.getTime() - in.getTime()) / (1000 * 60 * 60 * 24));
			int diffDaysOut = (int) ((date.getTime() - out.getTime()) / (1000 * 60 * 60 * 24));
			
			List<IHistoric> list = null;
			if (diffDaysIn <= 1 && diffDaysOut <= 1 && diffHoursIn <= 24) {
				list = repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));				
			} else if (diffDaysIn > 30 && diffDaysOut > 30) {
				list = repositoryD.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);				
//			} else if (diffDaysIn <= 2 && diffDaysOut <= 2) {
//				list = repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
//				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else if (diffDaysIn <= 7 && diffDaysOut <= 7) {
				list = repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
//			} else if(diffDaysIn <= 7) {
//				list = repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
//				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
//				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
				list = repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			} else {
				list = repositoryD.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out);
				list.addAll(repositoryC.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryB.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
				list.addAll(repositoryA.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));							
				list.addAll(repository.findByCompanyDeviceIdAndLastUpdateBetween(companyDeviceId, in, out));
			}
			result.setList(list);
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}

}
