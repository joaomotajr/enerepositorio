package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.enums.DeviceType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.register.Generic;

public class GenericDto extends BaseDeviceDto {
	private Long uid;		
	private UnitMeterGases unitMeterGases;
	private DeviceType deviceType;
	
	public GenericDto() {
		
	}
	public GenericDto(Generic generic) {
		
		this.uid = generic.getUid();
		this.name = generic.getName();
		this.unitMeterGases = generic.getUnitMeterGases();
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
}
