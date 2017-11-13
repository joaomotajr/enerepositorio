package br.com.eneeyes.main.model.historic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historic_view_hour_30")
public class HistoricViewHour30 implements IHistoricGroup {

    public HistoricViewHour30() {
    	
    }     

    @Id	
	@Column(name = "UID")	
	private Long uid;	
	
	@Column(name="COMPANY_DETECTOR_ID")
	private Long companyDetectorId;
	
	@Column(name="SENSOR_ID")
	private Long sensorId;

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

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
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
