package br.com.eneeyes.main.dto.procs;



import java.math.BigDecimal;

import br.com.eneeyes.main.dto.views.DashCompaniesPositionDto;
import br.com.eneeyes.main.model.procs.ProcDashboard;



public class ProcDashboardDto {
	
	public ProcDashboardDto() {	
	
	}	
	
	public ProcDashboardDto(ProcDashboard procDashboard) {
		
		this.uid = procDashboard.getUid();				
		this.value = procDashboard.getValue();
		
	}	
	
	private DashCompaniesPositionDto dashCompaniesPositionDto;	
	private Long uid;		
	private BigDecimal value;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final BigDecimal getValue() {
		return value;
	}

	public final void setValue(BigDecimal value) {
		this.value = value;
	}

	public final DashCompaniesPositionDto getDashCompaniesPositionDto() {
		return dashCompaniesPositionDto;
	}

	public final void setDashCompaniesPositionDto(DashCompaniesPositionDto dashCompaniesPositionDto) {
		this.dashCompaniesPositionDto = dashCompaniesPositionDto;
	}
	
}