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
import javax.persistence.Table;

import br.com.eneeyes.main.dto.CompanyDetectorMaintenanceHistoricDto;


@Entity
@Table(name = "company_detector_maintenance_historic")
public class CompanyDetectorMaintenanceHistoric {
	
	public CompanyDetectorMaintenanceHistoric() {
		
	}
	
	public CompanyDetectorMaintenanceHistoric(CompanyDetectorMaintenanceHistoricDto dto) {
		
		this.uid = dto.getUid();		
		this.description = dto.getDescription();
		this.date = dto.getDate();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
		
	@Column(name = "DATE", nullable = true)
	private Date date;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable = false)
	private CompanyDetector companyDetector;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public CompanyDetector getCompanyDetector() {
		return companyDetector;
	}

	public void setCompanyDetector(CompanyDetector companyDetector) {
		this.companyDetector = companyDetector;
	}
}


