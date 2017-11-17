package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.HistoricAlarm;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;
import br.com.eneeyes.main.repository.HistoricAlarmRepository;
import br.com.eneeyes.main.result.HistoricAlarmResult;

@Service
public class HistoricAlarmService {
		
	@Autowired
	private HistoricAlarmRepository repository;
	
	public HistoricAlarmResult<HistoricAlarm> findByCompanyDetectorAndSensorAndInterval(Long companyDetectorId, Long sensorId, IntervalType intervalType, Integer currentPage, Integer lenPage) {
		HistoricAlarmResult<HistoricAlarm> result = new HistoricAlarmResult<HistoricAlarm>();
			
		try {
			
			Date fim = new Date(); 
			Date inicio = new Date(fim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			Page<HistoricAlarm> page = null;
			
			if(intervalType ==  IntervalType.UM_MES) {
																	
				inicio = Util.addMonth(fim, -1);								
				page = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));								
			}
			else
			{
				page= repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, inicio, fim, new PageRequest(currentPage, lenPage));
			}
			
			result = getResults(page);			
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public HistoricAlarmResult<HistoricAlarm> findByCompanyDetectorAndSensorAndIntervalDays(Long companyDetectorId, Long sensorId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		HistoricAlarmResult<HistoricAlarm> result = new HistoricAlarmResult<HistoricAlarm>();
			
		try {			
			
			Page<HistoricAlarm> page = null;																	
											
			page = repository.findByCompanyDetectorIdAndSensorIdAndLastUpdateBetweenPaginated(companyDetectorId, sensorId, dateIn, dateOut, new PageRequest(currentPage, lenPage));			
			
			result = getResults(page);			
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	private HistoricAlarmResult<HistoricAlarm> getResults(Page<HistoricAlarm> page) {
		
		HistoricAlarmResult<HistoricAlarm> result = new HistoricAlarmResult<HistoricAlarm>();
		
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

		
	public void save(BigDecimal value, Long companyDetectorId, Long sensorId, Long historicId, Boolean alarmOn, 
			AlarmType alarmType, EmailStatus emailStatus, SmsStatus smsStatus, String action, SoundStatus soundStatus, SigmaStatus sigmaStatus) {
		
		HistoricAlarm historicAlarm = new HistoricAlarm();
		
		historicAlarm.setDate(new Date());
		historicAlarm.setCompanyDetectorId(companyDetectorId);
		historicAlarm.setSensorId(sensorId);
		historicAlarm.setHistoricId(historicId);
		historicAlarm.setAlarmOn(alarmOn);
		historicAlarm.setAlarmType(alarmType);
		historicAlarm.setEmailStatus(emailStatus);
		historicAlarm.setSmsStatus(smsStatus);
		historicAlarm.setAction(action);
		historicAlarm.setSoundStatus(soundStatus);
		historicAlarm.setSigmaStatus(sigmaStatus);
		historicAlarm.setValue(value);	
		
		repository.save(historicAlarm);
	}
	
	public void save(BigDecimal value, Long companyDetectorId, Long sensorId, Long historicId, AlarmType alarmType) {
		
		HistoricAlarm historicAlarm = new HistoricAlarm();
		
		historicAlarm.setUid(null);
		historicAlarm.setDate(new Date());
		historicAlarm.setCompanyDetectorId(companyDetectorId);
		historicAlarm.setSensorId(sensorId);
		historicAlarm.setHistoricId(historicId);		
		historicAlarm.setAlarmType(alarmType);		
		historicAlarm.setValue(value);	
		
		repository.save(historicAlarm);
	}
	
	
	public void save(BigDecimal value, Long companyDetectorId, Long sensorId, Long historicId, AlarmDto alarm, AlarmType alarmType) {
		
		HistoricAlarm historicAlarm = new HistoricAlarm();
		
		
		SigmaStatus sigmaStatus = null;
		if(alarm.getAlarmSigma() != null && alarm.getAlarmSigma())
			sigmaStatus = SigmaStatus.ON;
		else
			sigmaStatus = SigmaStatus.OFF;
		
		EmailStatus emailStatus = null;
		if(alarm.getAlarmEmail() != null && alarm.getAlarmEmail())
			emailStatus = EmailStatus.PENDENT;
		else
			emailStatus = EmailStatus.OFF;
		
		SmsStatus smsStatus = null;
		if(alarm.getAlarmSms() != null && alarm.getAlarmSms())
			smsStatus = SmsStatus.PENDENT;
		else
			smsStatus = SmsStatus.OFF;
		
		String action = null;
		if(alarmType == AlarmType.DETECCAO)
			action = alarm.getAction1();
		else if(alarmType == AlarmType.ALERTA)
			action = alarm.getAction2();
		else if(alarmType == AlarmType.EVACUACAO)
			action = alarm.getAction3();
		else if(alarmType == AlarmType.OFFLINE)
			action = alarm.getAction4();
		
		SoundStatus soundStatus = null;
		if(alarm.getAlarmSound())
			soundStatus = SoundStatus.ON;
		else
			soundStatus = SoundStatus.OFF;
		
		historicAlarm.setUid(null);
		historicAlarm.setDate(new Date());
		historicAlarm.setCompanyDetectorId(companyDetectorId);
		historicAlarm.setSensorId(sensorId);
		historicAlarm.setHistoricId(historicId);
		historicAlarm.setAlarmOn(alarm.getAlarmOn());
		historicAlarm.setAlarmType(alarmType);
		historicAlarm.setEmailStatus(emailStatus);
		historicAlarm.setSmsStatus(smsStatus);
		historicAlarm.setAction(action);
		historicAlarm.setSoundStatus(soundStatus);
		historicAlarm.setSigmaStatus(sigmaStatus);
		historicAlarm.setValue(value);	
		
		repository.save(historicAlarm);	
	}
	
}


