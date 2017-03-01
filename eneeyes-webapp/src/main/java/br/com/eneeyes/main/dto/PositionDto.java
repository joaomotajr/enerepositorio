package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.AlarmType;

public class PositionDto {
	
	private Long uid;
	private Date lastUpdate;
	private Double lastValue;	
    private CompanyDetectorDto companyDetectorDto;
    private SensorDto sensorDto;
    private AlarmType alarmType;
	
	public PositionDto() {
		
	}
	
	public PositionDto(Position position) {
		
		this.uid = position.getUid();
		this.lastUpdate = position.getLastUpdate();
    	this.lastValue = position.getLastValue();    	
    	this.sensorDto = new SensorDto(position.getSensor());
    	this.alarmType = position.getAlarmType();
    	this.companyDetectorDto = new CompanyDetectorDto(position.getCompanyDetector());  
	}
	
	public final Long getUid() {
		return uid;
	}
	
	public final void setUid(Long uid) {
		this.uid = uid;
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

}
