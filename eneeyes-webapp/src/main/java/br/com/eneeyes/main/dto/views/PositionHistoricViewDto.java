package br.com.eneeyes.main.dto.views;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.model.views.PositionHistoricView;

public class PositionHistoricViewDto implements Comparable<PositionHistoricViewDto> {
	
	public PositionHistoricViewDto() {
		
	
	}	
	
	public PositionHistoricViewDto(PositionHistoricView positionHistoricView) {
		
		this.uid = positionHistoricView.getUid();
		this.lastUpdate = positionHistoricView.getLastUpdate();	
		this.value = positionHistoricView.getValue();		
	}	
	
	private DashCompaniesPositionDto dashCompaniesPositionDto;	
	private Long uid;		
	private BigDecimal value;
	private Date lastUpdate;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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
	
	@Override
	public int compareTo(PositionHistoricViewDto positionHistoricViewDto) {
		return positionHistoricViewDto.getUid().compareTo(this.uid);		
	}
	
}