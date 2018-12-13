package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from dash_companies2")
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

	@Column(name="company_id", nullable=false, insertable=false, updatable=false)
	private Long companyId;	
	public final Long getCompanyId() {
		return companyId;
	}
	
	private String company;	
	public final String getCompany() {
		return company;
	}
	
	private String unit;	
	
	public final String getUnit() {
		return unit;
	}

	@Column(name="unit_id")
	private Long unitId;	
		
	public Long getUnitId() {
		return unitId;
	}

	@Column(name="company_device_id", nullable=false, insertable=false, updatable=false)
	private Long companyDeviceId;	

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	private String area;	
	
	public final String getArea() {
		return area;
	}
	
	@Column(name="area_id")
	private Long areaId;	
		
	public Long getAreaId() {
		return areaId;
	}

	private String device;	
	public final String getDevice() {
		return device;
	}
	
	@Column(name="companyDetector_name")
	private String companyDetectorName;	
	
	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}
	
	@Column(name="companyDetector_local")
	private String companyDetectorLocal;
			
	public String getCompanyDetectorLocal() {
		return companyDetectorLocal;
	}

	@Column(name="sensor_name")
	private String sensorName;	
	
	public final String getSensorName() {
		return sensorName;
	}
	
	@Column(name="detector_name")
	private String detectorName;	

	public String getDetectorName() {
		return detectorName;
	}

	@Column(name="range_max")
	private Double rangeMax;	
	
	public Double getRangeMax() {
		return rangeMax;
	}	
	
	@Column(name="device_type", nullable=true)
	private int deviceType;
	
	public final int getDeviceType() {
		return deviceType;
	}
}