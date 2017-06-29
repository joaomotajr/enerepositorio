package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.model.CompanyDetectorMaintenanceHistoric;
import br.com.eneeyes.main.model.enums.HistoricMaintenaceType;

public class CompanyDetectorMaintenanceHistoricDto {
	
	private Long uid;
	private Date date;
	private String description;
	private CompanyDetectorDto companyDetectorDto;
	private HistoricMaintenaceType historicMaintenaceType;
	
	public CompanyDetectorMaintenanceHistoricDto() {
		
	}
	
	public CompanyDetectorMaintenanceHistoricDto(CompanyDetectorMaintenanceHistoric model) {
		
		this.uid = model.getUid();
		this.date = model.getDate();		
		this.description = model.getDescription();
		this.historicMaintenaceType = model.getHistoricMaintenaceType();
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
	
	public CompanyDetectorDto getCompanyDetectorDto() {
		return companyDetectorDto;
	}
	
	public void setCompanyDetectorDto(CompanyDetectorDto companyDetectorDto) {
		this.companyDetectorDto = companyDetectorDto;
	}
	
	public final HistoricMaintenaceType getHistoricMaintenaceType() {
		return historicMaintenaceType;
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
