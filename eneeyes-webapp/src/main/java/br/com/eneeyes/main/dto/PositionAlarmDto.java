package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;

public class PositionAlarmDto {
	
	private Long uid;
	private Date firstUpdate;
	private Date lastUpdate;
	private Double lastValue;	
    private CompanyDetectorDto companyDetectorDto;
    private SensorDto sensorDto;
    private AlarmType alarmType;    
    private AlarmStatus alarmStatus;
    private Date statusUpdate;

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

	public Double getLastValue() {
		return lastValue;
	}

	public void setLastValue(Double lastValue) {
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
			this.alarmStatus = AlarmStatus.CHECKED;
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
}