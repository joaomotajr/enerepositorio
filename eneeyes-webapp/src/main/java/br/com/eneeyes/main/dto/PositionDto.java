package br.com.eneeyes.main.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.AlarmType;

public class PositionDto {
	
	private Long uid;
	private Date lastUpdate;
	private BigDecimal lastValue;	
    private CompanyDeviceDto companyDeviceDto;
    private AlarmType alarmType;
    private Long historicId;
	
	public PositionDto() {
		
	}
	
	public PositionDto(Position position) {
		
		this.uid = position.getUid();
		this.lastUpdate = position.getLastUpdate();
    	this.lastValue = position.getLastValue();
    	this.companyDeviceDto = new CompanyDeviceDto(position.getCompanyDevice());
    	this.alarmType = position.getAlarmType();    	
    	
    	this.historicId = position.getHistoricId();    		
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

	public BigDecimal getLastValue() {
		return lastValue;
	}

	public void setLastValue(BigDecimal lastValue) {
		this.lastValue = lastValue;
	}
	
	public CompanyDeviceDto getCompanyDeviceDto() {
		return companyDeviceDto;
	}

	public void setCompanyDeviceDto(CompanyDeviceDto companyDeviceDto) {
		this.companyDeviceDto = companyDeviceDto;
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

	public final Long getHistoricId() {
		return historicId;
	}

	public final void setHistoricId(Long historicId) {
		this.historicId = historicId;
	}
}
