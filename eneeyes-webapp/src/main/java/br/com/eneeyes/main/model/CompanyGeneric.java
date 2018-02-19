package br.com.eneeyes.main.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
				
		if(dto.getGenericDto() != null)
			this.generic = new Generic(dto.getGenericDto());
		
		if (dto.getAlarmDto()  != null) 
       		this.alarm = new Alarm(dto.getAlarmDto());
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
  
	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_DEVICE_ID", nullable = false)
	private CompanyDevice companyDevice;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="GENERIC_ID", nullable = false)
	private Generic generic;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="ALARM_ID", nullable = true)
	private Alarm alarm;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyGeneric", cascade = CascadeType.REMOVE)
//	private Set<PositionAlarm> positionAlarm;
//	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyGeneric", cascade = CascadeType.REMOVE)
//	private Set<Position> position;
	
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
		
	public CompanyDevice getCompanyDevice() {
		return companyDevice;
	}

	public void setCompanyDevice(CompanyDevice companyDevice) {
		this.companyDevice = companyDevice;
	}
	
	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public Generic getGeneric() {
		return generic;
	}

	public void setGeneric(Generic generic) {
		this.generic = generic;
	}	
	
}
