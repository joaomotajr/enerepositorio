package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.dto.register.GenericDto;
import br.com.eneeyes.main.model.CompanyGeneric;

public class CompanyGenericDto {

	private Long uid;
	private String name;
	private String description;		
	private Date date;
	private String local;
	private String serialNumber;
	private Double latitude;		
	private Double longitude;
	private CompanyDeviceDto companyDeviceDto;
	private GenericDto genericDto;
	
	public CompanyGenericDto() {
		
	}
	
	public CompanyGenericDto(CompanyGeneric e) {
    	
		this.uid = e.getUid();		
    	this.name = e.getName();
    	this.description = e.getDescription();
       	this.date = e.getDate();
       	this.local = e.getLocal();
       	this.serialNumber = e.getSerialNumber();
       	this.latitude = e.getLatitude();
       	this.longitude = e.getLongitude();
       	this.genericDto = new GenericDto(e.getGeneric());       	
       	this.companyDeviceDto = new CompanyDeviceDto(e.getCompanyDevice());       
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
	
	public GenericDto getGenericDto() {
		return genericDto;
	}

	public void setGenericDto(GenericDto genericDto) {
		this.genericDto = genericDto;
	}

	public CompanyDeviceDto getCompanyDeviceDto() {
		return companyDeviceDto;
	}

	public void setCompanyDeviceDto(CompanyDeviceDto companyDeviceDto) {
		this.companyDeviceDto = companyDeviceDto;
	}
	
}
