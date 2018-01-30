package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.enums.DeviceType;
import br.com.eneeyes.main.model.enums.UnitMeter;
import br.com.eneeyes.main.model.register.Generic;

public class GenericDto extends BaseDeviceDto {
	private Long uid;		
	private UnitMeter unitMeter;
	private DeviceType deviceType;
	
	public GenericDto() {
		
	}
	public GenericDto(Generic generic) {
		
		this.uid = generic.getUid();
		this.name = generic.getName();
		this.unitMeter = generic.getUnitMeter();
		this.deviceType = generic.getDeviceType();
		this.manufacturerDto = new ManufacturerDto(generic.getManufacturer());
		this.model = generic.getModel();
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public UnitMeter getUnitMeter() {
		return unitMeter;
	}
	
	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
	}
	
	public DeviceType getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}	
}
