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
		
	@EmbeddedId
	private DashCompanyId dashCompanyId;
		
	@Column(name="companyDetector_id", nullable=false, insertable=false, updatable=false)
	private Long companyDetectorId;	

	@Column(name="company_id", nullable=false, insertable=false, updatable=false)
	private Long companyId;
	
	private String area;
	
	private String company;
	
	private String companyDetectorName;

	private Long detector_id;

	private String device;
	
	@Column(name="device_type", nullable=true)
	private int deviceType;
	
	private String units;
	
	public DashCompany() {
		
	}
	
	public final DashCompanyId getDashCompanyId() {
		return dashCompanyId;
	}

	public final void setDashCompanyId(DashCompanyId dashCompanyId) {
		this.dashCompanyId = dashCompanyId;
	}

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final Long getCompanyId() {
		return companyId;
	}

	public final void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public final String getArea() {
		return area;
	}

	public final void setArea(String area) {
		this.area = area;
	}

	public final String getCompany() {
		return company;
	}

	public final void setCompany(String company) {
		this.company = company;
	}

	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public final void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
	}

	public final Long getDetector_id() {
		return detector_id;
	}

	public final void setDetector_id(Long detector_id) {
		this.detector_id = detector_id;
	}

	public final String getDevice() {
		return device;
	}

	public final void setDevice(String device) {
		this.device = device;
	}

	public final int getDeviceType() {
		return deviceType;
	}

	public final void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public final String getUnits() {
		return units;
	}

	public final void setUnits(String units) {
		this.units = units;
	}
}