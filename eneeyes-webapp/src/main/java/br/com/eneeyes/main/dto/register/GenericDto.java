package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.enums.DeviceType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.register.Generic;

public class GenericDto extends BaseDeviceDto {
	private Long uid;		
	private UnitMeterGases unitMeterGases;
	private DeviceType deviceType;
	private Double rangeMax;			
	private Double rangeMin;
	
	public GenericDto() {
		
	}
	public GenericDto(Generic e) {
		
		this.uid = e.getUid();
		this.name = e.getName();
		this.unitMeterGases = e.getUnitMeterGases();
		this.deviceType = e.getDeviceType();
		this.manufacturerDto = new ManufacturerDto(e.getManufacturer());
		this.model = e.getModel();
		this.rangeMin = e.getRangeMax();
		this.rangeMax = e.getRangeMin();
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
	
}
