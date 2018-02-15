package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.LogOrigem;

@Entity
@Subselect("select * from historic_view_habc")
public class HistoricViewHABC implements IHistoric {

    public HistoricViewHABC() {
    	
    }     

    @Id	
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "VALUE")
	private BigDecimal value;
	
	@Column(name="COMPANY_DETECTOR_ID")
	private Long companyDetectorId;
	
	@Column(name = "LOG_ORIGEM")
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
