package br.com.eneeyes.main.model.historic.exunion;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.historic.IHistoricGroup;

@Entity
@Subselect("select * from historic_view_day")
public class HistoricViewDay implements IHistoricGroup {

    public HistoricViewDay() {
    	
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
	
	@Column(name="AVG_VALUE")
	private BigDecimal avgValue;
	
	@Column(name="SUM_VALUE")
	private BigDecimal sumValue;	

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

	public BigDecimal getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(BigDecimal avgValue) {
		this.avgValue = avgValue;
	}

	public BigDecimal getSumValue() {
		return sumValue;
	}

	public void setSumValue(BigDecimal sumValue) {
		this.sumValue = sumValue;
	}
}
