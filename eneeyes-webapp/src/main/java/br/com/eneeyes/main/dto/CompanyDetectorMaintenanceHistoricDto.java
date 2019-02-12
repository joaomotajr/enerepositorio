package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.model.CompanyDetectorMaintenanceHistoric;
import br.com.eneeyes.main.model.enums.HistoricMaintenaceType;

public class CompanyDetectorMaintenanceHistoricDto {
	
	private Long uid;
	private Date date;
	private String description;
	private Long companyDetectorId;
	private HistoricMaintenaceType historicMaintenaceType;
	
	public CompanyDetectorMaintenanceHistoricDto() {
		
	}
	
	public CompanyDetectorMaintenanceHistoricDto(CompanyDetectorMaintenanceHistoric model) {
		
		this.uid = model.getUid();
		this.date = model.getDate();		
		this.description = model.getDescription();
		this.historicMaintenaceType = model.getHistoricMaintenaceType();
		this.companyDetectorId = model.getCompanyDetectorId();
	}

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
		
	public final HistoricMaintenaceType getHistoricMaintenaceType() {
		return historicMaintenaceType;
	}

	public Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final void setHistoricMaintenaceType(HistoricMaintenaceType historicMaintenaceType) {
				
		if (historicMaintenaceType == null ) {			
			this.historicMaintenaceType = HistoricMaintenaceType.DESCONHECIDO;
		}	
		else { 
			this.historicMaintenaceType = historicMaintenaceType;
		}
		
	}
}
