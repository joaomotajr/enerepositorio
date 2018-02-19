package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.HistoricAlarm;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.repository.HistoricAlarmRepository;
import br.com.eneeyes.main.result.GroupResult;
import br.com.eneeyes.main.service.buss.AlarmParams;

@Service
public class HistoricAlarmService {
		
	@Autowired
	private HistoricAlarmRepository repository;
	
	public GroupResult<?> findByCompanyDeviceAndInterval(Long companyDeviceId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		GroupResult<?> result = new GroupResult<HistoricAlarm>();
			
		try {
			
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			Page<HistoricAlarm> page = null;
			
			if(intervalType ==  IntervalType.UM_MES) {
																	
				inicio = Util.addMonth(fim, -1);
				page = repository.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, inicio, fim, new PageRequest(currentPage, lenPage));
			}
			else
			{
				page= repository.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, inicio, fim, new PageRequest(currentPage, lenPage));
			}
			
			result = getResults(page);			
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public GroupResult<?> findByCompanyDeviceAndIntervalDays(Long companyDeviceId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		GroupResult<?> result = new GroupResult<HistoricAlarm>();
			
		try {			
			
			Page<HistoricAlarm> page = null;																	
											
			page = repository.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn, dateOut, new PageRequest(currentPage, lenPage));			
			
			result = getResults(page);			
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	private GroupResult<?> getResults(Page<HistoricAlarm> page) {
		
		GroupResult<HistoricAlarm> result = new GroupResult<HistoricAlarm>();
		
		if (page != null) {
			
			List<HistoricAlarm> lista = new ArrayList<HistoricAlarm>();
			for (HistoricAlarm item : page) {
				
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

	public void save(BigDecimal value, Long companyDeviceId, Long historicId, AlarmDto alarmDto, AlarmType alarmType, AlarmParams alarmParams ) {		
		HistoricAlarm historicAlarm = new HistoricAlarm();
		
		historicAlarm.setDate(new Date());
		historicAlarm.setCompanyDeviceId(companyDeviceId);
		historicAlarm.setHistoricId(historicId);
		historicAlarm.setAlarmOn(alarmDto.getAlarmOn());
		historicAlarm.setAlarmType(alarmType);
		historicAlarm.setEmailStatus(alarmParams.getEmailStatus());
		historicAlarm.setSmsStatus(alarmParams.getSmsStatus());
		historicAlarm.setAction(alarmParams.getAction());
		historicAlarm.setSoundStatus(alarmParams.getSoundStatus());
		historicAlarm.setSigmaStatus(alarmParams.getSigmaStatus());
		historicAlarm.setValue(value);
		historicAlarm.setAlarm(new Gson().toJson(alarmDto));
						
		repository.save(historicAlarm);
	}
	
	public void save(BigDecimal value, Long companyDeviceId, Long historicId, AlarmType alarmType) {	
		HistoricAlarm historicAlarm = new HistoricAlarm();
		
		historicAlarm.setUid(null);
		historicAlarm.setDate(new Date());
		historicAlarm.setCompanyDeviceId(companyDeviceId);
		historicAlarm.setHistoricId(historicId);		
		historicAlarm.setAlarmType(alarmType);		
		historicAlarm.setValue(value);	
		
		repository.save(historicAlarm);
	}
	
}


