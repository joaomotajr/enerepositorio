package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.dto.register.DetectorDto;
import br.com.eneeyes.main.model.CompanyDetector;

public class CompanyDetectorDto {

	private Long uid;
	private String name;
	private String description;		
	private Date date;
	private String local;
	private CompanyDeviceDto companyDeviceDto;	
	private DetectorDto detectorDto;

	public CompanyDetectorDto() {
		
	}
	
	public CompanyDetectorDto(CompanyDetector companyDetector) {
    	
		this.uid = companyDetector.getUid();		
    	this.name = companyDetector.getName();
    	this.description = companyDetector.getDescription();
       	this.date = companyDetector.getDate();
       	this.setLocal(companyDetector.getLocal());
       	this.detectorDto = new DetectorDto(companyDetector.getDetector()) ;
	}
		
	public final Long getUid() {
		return uid;
	}
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public DetectorDto getDetectorDto() {
		return detectorDto;
	}

	public void setDetectorDto(DetectorDto detectorDto) {
		this.detectorDto = detectorDto;
	}
	
	public CompanyDeviceDto getCompanyDeviceDto() {
		return companyDeviceDto;
	}

	public void setCompanyDeviceDto(CompanyDeviceDto companyDeviceDto) {
		this.companyDeviceDto = companyDeviceDto;
	}
}
