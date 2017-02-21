package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="dash_companies_position")
public class DashCompaniesPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DashCompaniesPosition() {		
	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	private String company_name;
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	private String unit_name;
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	
	private String area_name;
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	private String company_detector_name;
	public String getCompany_detector_name() {
		return company_detector_name;
	}
	public void setCompany_detector_name(String company_detector_name) {
		this.company_detector_name = company_detector_name;
	}
	
	private Long sensor_id;
	public Long getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
	
	@Column(name="device_type", nullable=true)
	private int deviceType;
	public final int getDeviceType() {
		return deviceType;
	}
	public final void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}	
	
	private Date last_update;
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	

}