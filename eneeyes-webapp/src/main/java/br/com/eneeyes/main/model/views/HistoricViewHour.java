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
	
	private Date last_update;
	private BigDecimal value;    
	private Long company_detector_id;
	private Long sensor_id;
	
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getLast_update() {
		return last_update;
	}

	public final void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	public final BigDecimal getValue() {
		return value;
	}

	public final void setValue(BigDecimal value) {
		this.value = value;
	}

	public final Long getCompany_detector_id() {
		return company_detector_id;
	}

	public final void setCompany_detector_id(Long company_detector_id) {
		this.company_detector_id = company_detector_id;
	}

	public final Long getSensor_id() {
		return sensor_id;
	}

	public final void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}

}
