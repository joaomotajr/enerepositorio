package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.eneeyes.main.model.enums.LogOrigem;

@Entity
@Table(name = "historic_b")
@org.hibernate.annotations.Table(
		   appliesTo = "historic_b",
		   indexes = {
		      @Index(name="idxHistoricBDate", columnNames = "LAST_UPDATE"),		      
		      @Index(name="idxHistoricBCompanyDeviceAndDate", columnNames = {"COMPANY_DEVICE_ID",  "LAST_UPDATE"})
		   }
		)
public class HistoricB {
   
	@Id	
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "VALUE", nullable = true)
	private BigDecimal value;
	
	@Column(name="COMPANY_DEVICE_ID", nullable = false)
	private Long companyDeviceId;
	
	@Column(name = "LOG_ORIGEM", nullable = true)
	private LogOrigem logOrigem;
		
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
