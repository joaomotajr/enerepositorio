package br.com.eneeyes.main.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.HistoricAlarm;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;
import br.com.eneeyes.main.repository.HistoricAlarmRepository;

@Service
public class HistoricAlarmService {
		
	@Autowired
	private HistoricAlarmRepository repository;

		
	public void save(BigDecimal value, Long companyDetectorId, Long sensorId, Long historicId, Boolean alarmOn, 
			AlarmType alarmType, EmailStatus emailStatus, SmsStatus smsStatus, String action, SoundStatus soundStatus, SigmaStatus sigmaStatus) {
		
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
		historicAlarm.setAction(action);
		historicAlarm.setSoundStatus(soundStatus);
		historicAlarm.setSigmaStatus(sigmaStatus);
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
		historicAlarm.setLastUpdate(new Date());
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


