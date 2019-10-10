package br.com.eneeyes.main.dto.views;

import br.com.eneeyes.main.model.state.UnitMeter;
import br.com.eneeyes.main.model.views.AreaCompanyDeviceView;


public class AreaCompanyDeviceViewDto {
		
	private Long uid;
	private Long areaId;
	private Long companyDeviceId;
	private String name;
	private String local;
	private String deviceName;
	private Double rangeMax;
	private Double rangeMin;
	private Double latitude;
	private Double longitude;
	private String image;	
	private UnitMeter unitMeter;

	public AreaCompanyDeviceViewDto(AreaCompanyDeviceView e) {	
		this.uid = e.getUid();
		this.areaId = e.getAreaId();
		this.companyDeviceId = e.getCompanyDeviceId();
		this.name = e.getName();
		this.local  = e.getLocal();
		this.deviceName = e.getDeviceName();
		this.rangeMax = e.getRangeMax();
		this.rangeMin = e.getRangeMin();
		this.latitude = e.getLatitude();
		this.longitude = e.getLongitude();
		this.unitMeter = e.getUnitMeter();
		
		if (e.getImage() != null) {
			byte[] image = e.getImage();
			this.image = new String(image);
		}
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public void setCompanyDeviceId(Long companyDeviceId) {
		this.companyDeviceId = companyDeviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Double getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(Double rangeMax) {
		this.rangeMax = rangeMax;
	}

	public Double getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(Double rangeMin) {
		this.rangeMin = rangeMin;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
	}	
		
}