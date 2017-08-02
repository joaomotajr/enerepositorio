package br.com.eneeyes.main.service.buss;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;

public class AlarmParams {
		
	private AlarmType alarmType;
	private AlarmDto alarm;
		
	public AlarmParams() {		
	}
	
	public AlarmParams(AlarmDto alarm, AlarmType alarmType) {
		this.alarm = alarm;
		this.alarmType = alarmType;
	}
	
	public final AlarmDto getAlarm() {
		return alarm;
	}

	public final void setAlarm(AlarmDto alarm) {
		this.alarm = alarm;		
	}	

	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public final SigmaStatus getSigmaStatus() {
		
		SigmaStatus sigmaStatus = null;
		
		if(this.alarm.getAlarmSigma() != null && this.alarm.getAlarmSigma())
			sigmaStatus = SigmaStatus.ON;
		else
			sigmaStatus = SigmaStatus.OFF;
		
		return sigmaStatus;
	}

	public final void setSigmaStatus(SigmaStatus sigmaStatus) {
	}

	public final EmailStatus getEmailStatus() {
		
		EmailStatus emailStatus = null;
		
		if(this.alarm.getAlarmEmail() != null && this.alarm.getAlarmEmail())
			emailStatus = EmailStatus.PENDENT;
		else
			emailStatus = EmailStatus.OFF;
		
		return emailStatus;
	}
	

	public final SmsStatus getSmsStatus() {
		
		SmsStatus smsStatus = null;
		if(this.alarm.getAlarmSms() != null && this.alarm.getAlarmSms())
			smsStatus = SmsStatus.PENDENT;
		else
			smsStatus = SmsStatus.OFF;
		
		return smsStatus;
	}

	public final SoundStatus getSoundStatus() {
		
		SoundStatus soundStatus = null;
		
		if(this.alarm.getAlarmSound())
			soundStatus = SoundStatus.ON;
		else
			soundStatus = SoundStatus.OFF;
		
		return soundStatus;
	}

	public final String getAction() {
		
		String action = null;
		if(this.alarmType == AlarmType.DETECCAO)
			action = alarm.getAction1();
		else if(this.alarmType == AlarmType.ALERTA)
			action = alarm.getAction2();
		else if(this.alarmType == AlarmType.EVACUACAO)
			action = alarm.getAction3();
		
		return action;
	}
	
}
