package br.com.eneeyes.main.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.eneeyes.main.model.enums.LogOrigem;
import br.com.eneeyes.main.model.historic.Historic;

public class HistoricDto {
	
	private Long uid;
	private Date lastUpdate;
	private BigDecimal value;
	private Long companyDeviceId;
    private LogOrigem logOrigem;
	
	public HistoricDto() {
		
	}
	
	public HistoricDto(Historic historic) {
		
		this.uid = historic.getUid();
		this.lastUpdate = historic.getLastUpdate();
    	this.value = historic.getValue();   
    	this.companyDeviceId = historic.getCompanyDeviceId();
    	this.logOrigem = historic.getLogOrigem();    	
	}
	
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

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public final Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public final void setCompanyDeviceId(Long companyDeviceId) {
		this.companyDeviceId = companyDeviceId;
	}

	public final LogOrigem getLogOrigem() {
		return logOrigem;
	}

	public final void setLogOrigem(LogOrigem logOrigem) {
		this.logOrigem = logOrigem;
	}	
	
}
