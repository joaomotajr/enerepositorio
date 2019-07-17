package br.com.eneeyes.main.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.eneeyes.main.dto.CompanyGenericDto;
import br.com.eneeyes.main.model.register.Generic;

/**
 * Created by Junior on 19/02/2018.
 * Cadastro dos Generics das Empresas.
 */

@Entity
@Table(name = "company_generic")
@org.hibernate.annotations.Table(
		   appliesTo = "company_generic",
		   indexes = {
		      @Index(name="idxGenericName", columnNames = "name"),
		      @Index(name="idxGenericCompanyDevice", columnNames = "COMPANY_DEVICE_ID"),
		      @Index(name="idxGenericNameDate", columnNames = {"name", "date"})
		   }
		)

public class CompanyGeneric {

	public CompanyGeneric() {   	
    
    }
	
    public CompanyGeneric(Long uid) {    	
    	this.uid = uid;
    }
    
    public CompanyGeneric(CompanyGenericDto dto) {
    	
    	this.uid = dto.getUid();		
    	this.name = dto.getName();
    	this.description = dto.getDescription();    			
    	this.date = dto.getDate();    	  
    	this.local = dto.getLocal();
    	this.serialNumber = dto.getSerialNumber();
    	this.latitude = dto.getLatitude();
		this.longitude = dto.getLongitude();
				
		if(dto.getGenericDto() != null)
			this.generic = new Generic(dto.getGenericDto());
		
		if (dto.getCompanyDeviceDto()  != null) 
       		this.companyDevice = new CompanyDevice(dto.getCompanyDeviceDto());
		
	}   

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "SERIAL_NUMBER", nullable = true)
	private String serialNumber;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "DATE", nullable = true)
	private Date date;  
		
	@Column(name = "LOCAL", nullable = true)		
	private String local;
	
	@Column(name = "LATITUDE", nullable = true)		
	private Double latitude;
	
	@Column(name = "LONGITUDE", nullable = true)		
	private Double longitude;
  
	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_DEVICE_ID", nullable = false)
	private CompanyDevice companyDevice;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="GENERIC_ID", nullable = false)
	private Generic generic;
	
	@Column(name = "HISTORIC_ID", nullable = true)
	private Long historicId;
			
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
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
	
	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}
	
	public final String getLocal() {
		return local;
	}

	public final void setLocal(String local) {
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
		
	public CompanyDevice getCompanyDevice() {
		return companyDevice;
	}

	public void setCompanyDevice(CompanyDevice companyDevice) {
		this.companyDevice = companyDevice;
	}

	public Generic getGeneric() {
		return generic;
	}

	public void setGeneric(Generic generic) {
		this.generic = generic;
	}	
	
}
