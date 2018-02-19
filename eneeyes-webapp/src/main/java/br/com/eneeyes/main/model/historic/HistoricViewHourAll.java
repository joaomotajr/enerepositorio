package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from historic_view_hour_all")
public class HistoricViewHourAll implements IHistoricGroup {

    public HistoricViewHourAll() {
    	
    }     

    @Id	
	@Column(name = "UID")	
	private Long uid;	
	
	@Column(name="COMPANY_DEVICE_ID")
	private Long companyDeviceId;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
			
	@Column(name="MAX_VALUE")
	private BigDecimal maxValue;
	
	@Column(name="MIN_VALUE")
	private BigDecimal minValue;

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public final void setCompanyDeviceId(Long companyDeviceId) {
		this.companyDeviceId = companyDeviceId;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public final BigDecimal getMaxValue() {
		return maxValue;
	}

	public final void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public final BigDecimal getMinValue() {
		return minValue;
	}

	public final void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}
}
