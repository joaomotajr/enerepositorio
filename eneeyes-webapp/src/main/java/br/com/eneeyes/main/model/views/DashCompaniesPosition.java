package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;


@Entity
@Table(name="dash_companies_position")
public class DashCompaniesPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DashCompaniesPosition() {		
	
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long uid;

	private Long company_id;
	private String company_name;
	private String unit_name;
	private String area_name;
	private String company_detector_name;
	private String sensor_name;
	private Long sensor_id;
	private String gas_name;	

	@Column(name = "last_value", nullable = true)
	private Double last_value;
	
	@Column(name = "alarm_type")
	private AlarmType alarmType;
	
	private Date last_update;
		
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
		
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
		
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
		
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
		
	public String getCompany_detector_name() {
		return company_detector_name;
	}
	public void setCompany_detector_name(String company_detector_name) {
		this.company_detector_name = company_detector_name;
	}
	
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}
	
	public Long getSensor_id() {
		return sensor_id;
	}
	
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
	
	public Double getLast_value() {
		return last_value;
	}
	public void setLast_value(Double last_value) {
		this.last_value = last_value;
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}	
		
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	public String getGas_name() {
		return gas_name;
	}
	
	public void setGas_name(String gas_name) {
		this.gas_name = gas_name;
	}
	
	@Column(name = "unit_meter_gases")
	private UnitMeterGases unitMeterGases;
	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}

	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}
}