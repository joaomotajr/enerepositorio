package br.com.eneeyes.main.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.CompanyDetectorDto;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.register.Detector;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Detectores das Empresas.
 */

@Entity
@Table(name = "company_detector")
public class CompanyDetector {
	
    public CompanyDetector() {
    	
    }
    
    public CompanyDetector(CompanyDetectorDto dto) {
    	
    	this.uid = dto.getUid();		
    	this.name = dto.getName();
    	this.description = dto.getDescription();    			
    	this.date = dto.getDate();    	  
    	this.local = dto.getLocal();
		this.latitude = dto.getLatitude();
		this.longitude = dto.getLongitude();
		
		if(dto.getDetectorDto() != null)
			this.detector = new Detector(dto.getDetectorDto());

    }   

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
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
	
	@Column(name = "UNIT_METER_GASES", columnDefinition = "int default 0")
	private UnitMeterGases unitMeterGases;

	@Enumerated(EnumType.ORDINAL) 
	private UnitMeterGases UnitMeterGases() { 
	    return unitMeterGases; 
	}
	
	@Column(name = "RANGE_MAX", nullable = true)		
	private Double RangeMax;
	
	@Column(name = "RANGE_MIN", nullable = true)		
	private Double RangeMin;
	
	@Column(name = "RANGE_UNIT", nullable = true)		
	private Double RangeUnit;
  
	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_DEVICE_ID", nullable = false)
	private CompanyDevice companyDevice;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DETECTOR_ID", nullable = false)
	private Detector detector;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyDetector", cascade = CascadeType.REMOVE)
	private Set<Position> position;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyDetector", cascade = CascadeType.REMOVE)
	private Set<Historic> historic;
			
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
	
	public Detector getDetector() {
		return detector;
	}

	public void setDetector(Detector detector) {
		this.detector = detector;
	}
}
