package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.model.HistoricAlarm;
import br.com.eneeyes.main.repository.HistoricAlarmRepository;

@Service
public class HistoricAlarmService {
		
	@Autowired
	private HistoricAlarmRepository repository;

		
	public void save(BigDecimal value, Long companyDetectorId, Long sensorId, Long historicId, Boolean alarmOn, 
			String alarmType, String emailStatus, String smsStatus, String action, String soundStatus, String sigmaStatus) {
		
		HistoricAlarm historicAlarm = new HistoricAlarm();
		
		historicAlarm.setUid(null);
		historicAlarm.setLastUpdate(new Date());
		historicAlarm.setCompanyDetectorId(companyDetectorId);
		historicAlarm.setSensorId(sensorId);
		historicAlarm.setHistoricId(historicId);
		historicAlarm.setAlarmOn(alarmOn);
		historicAlarm.setAlarmType(alarmType);
		historicAlarm.setEmailStatus(emailStatus);
		historicAlarm.setSmsStatus(smsStatus);
		historicAlarm.setActionStatus(action);
		historicAlarm.setSoundStatus(soundStatus);
		historicAlarm.setSigmaStatus(sigmaStatus);
		historicAlarm.setValue(value);	
		
		repository.save(historicAlarm);
	}
	
}


