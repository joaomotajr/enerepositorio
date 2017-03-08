package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="dash_companies")
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
	
	private String companyDetectorName;	
	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}
	public final void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
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