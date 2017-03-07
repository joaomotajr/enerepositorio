package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;

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
	private String last_update_date;
	private Long last_update_date_number;
	private int last_update_hour;
	private int ticks;
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
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public Long getLast_update_date_number() {
		return last_update_date_number;
	}
	public void setLast_update_date_number(Long last_update_date_number) {
		this.last_update_date_number = last_update_date_number;
	}
	public int getLast_update_hour() {
		return last_update_hour;
	}
	public void setLast_update_hour(int last_update_hour) {
		this.last_update_hour = last_update_hour;
	}
	public int getTicks() {
		return ticks;
	}
	public void setTicks(int ticks) {
		this.ticks = ticks;
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
