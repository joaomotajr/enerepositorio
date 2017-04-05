package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historic_view_hour")
public class HistoricViewHour {

    public HistoricViewHour() {
    	
    }     

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")	
	private Long uid;
	private Long company_detector_id;
	private Long sensor_id;
	private Date last_update;
	private int ticks;
	private BigDecimal value;	
	private BigDecimal max_value;
	private BigDecimal min_value;
		
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getCompany_detector_id() {
		return company_detector_id;
	}
	public void setCompany_detector_id(Long company_detector_id) {
		this.company_detector_id = company_detector_id;
	}
	public Long getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
	public final Date getLast_update() {
		return last_update;
	}
	public final void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	public int getTicks() {
		return ticks;
	}
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
	public final BigDecimal getValue() {
		return value;
	}
	public final void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getMax_value() {
		return max_value;
	}
	public void setMax_value(BigDecimal max_value) {
		this.max_value = max_value;
	}
	public BigDecimal getMin_value() {
		return min_value;
	}
	public void setMin_value(BigDecimal min_value) {
		this.min_value = min_value;
	}
}
