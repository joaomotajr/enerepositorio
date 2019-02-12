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
	private String serialNumber;	
	private Double latitude;		
	private Double longitude;	
	private Date deliveryDate;
	private Integer garantyDays;
	private String descriptionDelivery;
	private Date installDate;
	private Integer maintenanceInterval;
	private String descriptionInstall;
	private CompanyDeviceDto companyDeviceDto;	
	private DetectorDto detectorDto;
	
	public CompanyDetectorDto() {
		
	}
	
	public CompanyDetectorDto(CompanyDetector companyDetector) {
    	
		this.uid = companyDetector.getUid();		
    	this.name = companyDetector.getName();
    	this.description = companyDetector.getDescription();
       	this.date = companyDetector.getDate();
       	this.local = companyDetector.getLocal();
       	this.serialNumber = companyDetector.getSerialNumber();
       	this.latitude = companyDetector.getLatitude();
       	this.longitude = companyDetector.getLongitude();
       	this.deliveryDate = companyDetector.getDeliveryDate();
		this.garantyDays = companyDetector.getGarantyDays();		
		this.descriptionDelivery = companyDetector.getDescriptionDelivery();
		this.installDate = companyDetector.getInstallDate();
		this.maintenanceInterval = companyDetector.getMaintenanceInterval();
		this.descriptionInstall = companyDetector.getDescriptionInstall();
       	
       	this.detectorDto = new DetectorDto(companyDetector.getDetector());
       	
       	if (companyDetector.getCompanyDevice()  != null) 
       		this.companyDeviceDto = new CompanyDeviceDto(companyDetector.getCompanyDevice());
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
	
	public final String getSerialNumber() {
		return serialNumber;
	}

	public final void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public final Double getLatitude() {
		return latitude;
	}
	
	public final void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public final Double getLongitude() {
		return longitude;
	}

	public final void setLongitude(Double longitude) {
		this.longitude = longitude;
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
	
	public final Date getDeliveryDate() {
		return deliveryDate;
	}

	public final void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public final Integer getGarantyDays() {
		return garantyDays;
	}

	public final void setGarantyDays(Integer garantyDays) {
		this.garantyDays = garantyDays;
	}

	public final String getDescriptionDelivery() {
		return descriptionDelivery;
	}

	public final void setDescriptionDelivery(String descriptionDelivery) {
		this.descriptionDelivery = descriptionDelivery;
	}

	public final Date getInstallDate() {
		return installDate;
	}

	public final void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public final Integer getMaintenanceInterval() {
		return maintenanceInterval;
	}

	public final void setMaintenanceInterval(Integer maintenanceInterval) {
		this.maintenanceInterval = maintenanceInterval;
	}

	public final String getDescriptionInstall() {
		return descriptionInstall;
	}

	public final void setDescriptionInstall(String descriptionInstall) {
		this.descriptionInstall = descriptionInstall;
	}	
}
