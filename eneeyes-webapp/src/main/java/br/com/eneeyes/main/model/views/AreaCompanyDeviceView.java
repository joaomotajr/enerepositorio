package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from area_companydevice_view")
public class AreaCompanyDeviceView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AreaCompanyDeviceView() {		
	
	}	
	
	@Id	
	@Column(name = "uid")
	private Long uid;
	
	@Column(name = "area_id")
	private Long areaId;
	
	@Column(name = "company_device_id")
	private Long companyDeviceId;
		
	@Column(name = "company_device_name")
	private String companyDetectorName;
	
	@Column(name = "company_device_local")
	private String companyDeviceLocal;
	
	@Column(name = "device_name")
	private String DeviceName;
	
	@Column(name = "range_max")
	private Double rangeMax;
	
	@Column(name = "range_min")
	private Double rangeMin;
	
	@Column(name = "latitude")
	private Double latitude;	
	
	@Column(name = "longitude")
	private Double longitude;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public Long getAreaId() {
		return areaId;
	}

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public String getCompanyDeviceLocal() {
		return companyDeviceLocal;
	}

	public String getDeviceName() {
		return DeviceName;
	}

	public Double getRangeMax() {
		return rangeMax;
	}
	
	public Double getRangeMin() {
		return rangeMax;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}	
}