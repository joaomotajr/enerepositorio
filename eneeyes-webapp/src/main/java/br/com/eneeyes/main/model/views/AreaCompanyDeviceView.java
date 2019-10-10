package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.state.UnitMeter;

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
	
	private String name;
	
	@Column(name = "local")
	private String local;
	
	@Column(name = "device_name")
	private String deviceName;
	
	@Column(name = "range_max")
	private Double rangeMax;
	
	@Column(name = "range_min")
	private Double rangeMin;
	
	@Column(name = "latitude")
	private Double latitude;	
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Lob
	@Column(name = "IMAGE", nullable = true)
	byte[] image;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;

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

	public String getName() {
		return name;
	}

	public String getLocal() {
		return local;
	}

	public String getDeviceName() {
		return deviceName;
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

	public byte[] getImage() {
		return image;
	}

	public UnitMeter getUnitMeter() {
		return unitMeter;
	} 	
}