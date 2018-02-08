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
@Table(name = "historic_c")
@org.hibernate.annotations.Table(
		   appliesTo = "historic_c",
		   indexes = {
		      @Index(name="idxHistoricCDate", columnNames = "LAST_UPDATE"),		      
		      @Index(name="idxHistoricCCompanySensorAndDate", columnNames = {"COMPANY_DETECTOR_ID",  "LAST_UPDATE"})
		   }
		)
public class HistoricC implements IHistoric {
   
	@Id	
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "VALUE", nullable = true)
	private BigDecimal value;
	
	@Column(name="COMPANY_DETECTOR_ID", nullable = false)
	private Long companyDetectorId;
	
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

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}


	public final LogOrigem getLogOrigem() {
		return logOrigem;
	}

	public final void setLogOrigem(LogOrigem logOrigem) {
		this.logOrigem = logOrigem;
	}	
		
}
