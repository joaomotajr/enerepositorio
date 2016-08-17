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

import br.com.eneeyes.main.dto.CompanyDetectorDto;
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
  
	@ManyToOne(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_DEVICE_ID", nullable = false)
	private CompanyDevice companyDevice;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DETECTOR_ID", nullable = false)
	private Detector detector;

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
