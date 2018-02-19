package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from dash_companies")
@NamedQuery(name="DashCompany.findAll", query="SELECT d FROM DashCompany d")
public class DashCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DashCompany() {		
	}
	
	@EmbeddedId
	private DashCompanyId dashCompanyId;
	public final DashCompanyId getDashCompanyId() {
		return dashCompanyId;
	}
	public final void setDashCompanyId(DashCompanyId dashCompanyId) {
		this.dashCompanyId = dashCompanyId;
	}
		
	@Column(name="companyDetector_id", nullable=false, insertable=false, updatable=false)
	private Long companyDetectorId;	
	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}
	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	@Column(name="company_id", nullable=false, insertable=false, updatable=false)
	private Long companyId;	
	public final Long getCompanyId() {
		return companyId;
	}
	public final void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	private String company;	
	public final String getCompany() {
		return company;
	}
	public final void setCompany(String company) {
		this.company = company;
	}
	
	private String unit;	
	
	public final String getUnit() {
		return unit;
	}
	
	public final void setUnit(String unit) {
		this.unit = unit;
	}

	private Long unit_id;	
	
	public Long getUnit_id() {
		return unit_id;
	}
	
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	
	@Column(name="company_device_id", nullable=false, insertable=false, updatable=false)
	private Long companyDeviceId;	

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}
	
	public void setCompanyDeviceId(Long companyDeviceId) {
		this.companyDeviceId = companyDeviceId;
	}

	private String area;	
	
	public final String getArea() {
		return area;
	}
	
	public final void setArea(String area) {
		this.area = area;
	}
	
	private Long area_id;	
	
	public Long getArea_id() {
		return area_id;
	}
	
	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}
	
	private String device;	
	
	public final String getDevice() {
		return device;
	}
	
	public final void setDevice(String device) {
		this.device = device;
	}
	
	@Column(name="companyDetector_name")
	private String companyDetectorName;	
	
	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}
	
	public final void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
	}
	
	@Column(name="sensor_name")
	private String sensorName;	
	
	public final String getSensorName() {
		return sensorName;
	}
	
	public final void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	
	@Column(name="detector_name")
	private String detectorName;	

	public String getDetectorName() {
		return detectorName;
	}
	
	public void setDetectorName(String detectorName) {
		this.detectorName = detectorName;
	}

	@Column(name="range_max")
	private Double rangeMax;	
	
	public Double getRangeMax() {
		return rangeMax;
	}
	
	public void setRangeMax(Double rangeMax) {
		this.rangeMax = rangeMax;
	}

	private Long detector_id;	
	
	public final Long getDetector_id() {
		return detector_id;
	}
	
	public final void setDetector_id(Long detector_id) {
		this.detector_id = detector_id;
	}
	
	@Column(name="device_type", nullable=true)
	private int deviceType;
	
	public final int getDeviceType() {
		return deviceType;
	}
	
	public final void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

}