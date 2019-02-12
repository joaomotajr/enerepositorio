package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.register.Generic;
import br.com.eneeyes.main.model.state.DeviceType;

public class GenericDto extends BaseDeviceDto {
	private Long uid;		
	private UnitMeterGases unitMeterGases;
	private DeviceType deviceType;
	private Double rangeMax;			
	private Double rangeMin;
	private String image;
	
	public GenericDto() {
		
	}
	public GenericDto(Generic e) {
		
		this.uid = e.getUid();
		this.name = e.getName();
		this.unitMeterGases = e.getUnitMeterGases();
		this.deviceType = e.getDeviceType();
		this.manufacturerDto = new ManufacturerDto(e.getManufacturer());
		this.model = e.getModel();
		this.rangeMax = e.getRangeMax();
		this.rangeMin = e.getRangeMin();
		
		if (e.getImage() != null) {
			byte[] image = e.getImage();
			this.image = new String(image);
		}
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}
	
	public void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}
	
	public DeviceType getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
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
	
	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = image;
	}
}
