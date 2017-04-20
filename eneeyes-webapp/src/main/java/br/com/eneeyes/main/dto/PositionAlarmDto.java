package br.com.eneeyes.main.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;

public class PositionAlarmDto {
	
	private Long uid;
	private Date firstUpdate;
	private Date lastUpdate;
	private BigDecimal lastValue;	
    private CompanyDetectorDto companyDetectorDto;
    private SensorDto sensorDto;
    private AlarmType alarmType;    
    private AlarmStatus alarmStatus;
    private Date statusUpdate;
    private EmailStatus emailStatus;
	private SmsStatus smsStatus;
	private String action;
	private SoundStatus soundStatus;
	private SigmaStatus sigmaStatus;
	
	public PositionAlarmDto() {
		
	}
	
	public PositionAlarmDto(PositionAlarm positionAlarm) {
		
		this.uid = positionAlarm.getUid();
		this.firstUpdate = positionAlarm.getFirstUpdate();
		this.lastUpdate = positionAlarm.getLastUpdate();
    	this.lastValue = positionAlarm.getLastValue();    	
    	this.sensorDto = new SensorDto(positionAlarm.getSensor());
    	this.alarmType = positionAlarm.getAlarmType();
    	this.alarmStatus = positionAlarm.getAlarmStatus();
    	this.statusUpdate = positionAlarm.getStatusUpdate();
    	this.emailStatus = positionAlarm.getEmailStatus();
    	this.smsStatus = positionAlarm.getSmsStatus();
    	this.action = positionAlarm.getAction();
    	this.soundStatus = positionAlarm.getSoundStatus();
    	this.sigmaStatus = positionAlarm.getSigmaStatus();
	}
	
	public final Long getUid() {
		return uid;
	}
	
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getFirstUpdate() {
		return firstUpdate;
	}

	public final void setFirstUpdate(Date firstUpdate) {
		this.firstUpdate = firstUpdate;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLastValue() {
		return lastValue;
	}

	public void setLastValue(BigDecimal lastValue) {
		this.lastValue = lastValue;
	}

	public CompanyDetectorDto getCompanyDetectorDto() {
		return companyDetectorDto;
	}

	public void setCompanyDetectorDto(CompanyDetectorDto companyDetectorDto) {
		this.companyDetectorDto = companyDetectorDto;
	}
	
	public final SensorDto getSensorDto() {
		return sensorDto;
	}

	public final void setSensorDto(SensorDto sensorDto) {
		this.sensorDto = sensorDto;
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
		
		if (alarmType == null ) {			
			this.alarmType = AlarmType.NORMAL;
		}	
		else { 
			this.alarmType = alarmType;
		}
	}
	
	public final AlarmStatus getAlarmStatus() {
		return alarmStatus;
	}
	
	public final void setAlarmStatus(AlarmStatus alarmStatus) {
		this.alarmStatus = alarmStatus;
		
		if (alarmStatus == null ) {			
			this.alarmStatus = AlarmStatus.CREATED;
		}	
		else { 
			this.alarmStatus = alarmStatus;
		}
	}

	public Date getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	
	public final EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public final void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}

	public final SmsStatus getSmsStatus() {
		return smsStatus;
	}

	public final void setSmsStatus(SmsStatus smsStatus) {
		this.smsStatus = smsStatus;
	}
	
	public final String getAction() {
		return action;
	}

	public final void setAction(String action) {
		this.action = action;
	}
	
	public final SoundStatus getSoundStatus() {
		return soundStatus;
	}
	
	public final void setSoundStatus(SoundStatus soundStatus) {
		this.soundStatus = soundStatus;
		
		if (soundStatus == null ) {			
			this.soundStatus = SoundStatus.OFF;
		}	
		else { 
			this.soundStatus = soundStatus;
		}
	}
	
	public final SigmaStatus getSigmaStatus() {
		return sigmaStatus;
	}

	public final void setSigmaStatus(SigmaStatus sigmaStatus) {
		this.sigmaStatus = sigmaStatus;
		
		if (sigmaStatus == null ) {			
			this.sigmaStatus = SigmaStatus.OFF;
		}	
		else { 
			this.sigmaStatus = sigmaStatus;
		}
	}	
	
}
