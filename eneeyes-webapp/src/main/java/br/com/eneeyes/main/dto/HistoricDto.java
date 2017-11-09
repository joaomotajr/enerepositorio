package br.com.eneeyes.main.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.model.historic.Historic;

public class HistoricDto {
	
	private Long uid;
	private Date lastUpdate;
	private BigDecimal value;	
//    private CompanyDetectorDto companyDetectorDto;
//    private SensorDto sensorDto;
	private Long companyDetectorId;
    private Long sensorId;
	
	public HistoricDto() {
		
	}
	
	public HistoricDto(Historic historic) {
		
		this.uid = historic.getUid();
		this.lastUpdate = historic.getLastUpdate();
    	this.value = historic.getValue();   
//    	this.sensorDto = new SensorDto(historic.getSensor());   
//    	this.companyDetectorDto = new CompanyDetectorDto(historic.getCompanyDetector());   
    	this.companyDetectorId = historic.getCompanyDetectorId();
    	this.sensorId = historic.getSensorId();
    	
	}
	
	public final Long getUid() {
		return uid;
	}
	
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
	
//	public CompanyDetectorDto getCompanyDetectorDto() {
//		return companyDetectorDto;
//	}
//
//	public void setCompanyDetectorDto(CompanyDetectorDto companyDetectorDto) {
//		this.companyDetectorDto = companyDetectorDto;
//	}
//	
//	public final SensorDto getSensorDto() {
//		return sensorDto;
//	}
//
//	public final void setSensorDto(SensorDto sensorDto) {
//		this.sensorDto = sensorDto;
//	}

}
