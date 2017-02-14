package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.Historic;

public class HistoricDto {
	
	private Long uid;
	private Date lastUpdate;
	private Double value;	
    private CompanyDetectorDto companyDetectorDto;
    private SensorDto sensorDto;
	
	public HistoricDto() {
		
	}
	
	public HistoricDto(Historic historic) {
		
		this.uid = historic.getUid();
		this.lastUpdate = historic.getLastUpdate();
    	this.value = historic.getValue();    	
    	this.companyDetectorDto = new CompanyDetectorDto(historic.getCompanyDetector());
    	this.sensorDto = new SensorDto(historic.getSensor());    	    	
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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

}
