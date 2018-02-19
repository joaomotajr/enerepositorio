package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from alarm_companydetector_sensor_view")
public class AlarmCompanyDetectorSensorView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AlarmCompanyDetectorSensorView() {		
	
	}	
	
	@Id	
	@Column(name = "uid")
	private Long uid;
	
	private Long alarm_id;
	private Long company_device_id;
	private Long company_detector_id;
	private Long sensor_id;
	private String company_detector_name;
	private String company_detector_local;	
	private Double range_max;
	private String sensor_name;
		
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getAlarm_id() {
		return alarm_id;
	}
	
	public void setAlarm_id(Long alarm_id) {
		this.alarm_id = alarm_id;
	}
	
	public Long getCompany_detector_id() {
		return company_detector_id;
	}
	
	public void setCompany_detector_id(Long company_detector_id) {
		this.company_detector_id = company_detector_id;
	}	
	
	public Long getCompany_device_id() {
		return company_device_id;
	}

	public void setCompany_device_id(Long company_device_id) {
		this.company_device_id = company_device_id;
	}

	public Long getSensor_id() {
		return sensor_id;
	}
	
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
		
	public String getCompany_detector_name() {
		return company_detector_name;
	}

	public void setCompany_detector_name(String company_detector_name) {
		this.company_detector_name = company_detector_name;
	}
	
	public final String getCompany_detector_local() {
		return company_detector_local;
	}

	public final void setCompany_detector_local(String company_detector_local) {
		this.company_detector_local = company_detector_local;
	}

	public final Double getRange_max() {
		return range_max;
	}

	public final void setRange_max(Double range_max) {
		this.range_max = range_max;
	}
	
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}	

}