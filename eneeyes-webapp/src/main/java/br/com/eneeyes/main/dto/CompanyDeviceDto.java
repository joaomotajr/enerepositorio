package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.enums.DeviceType;

public class CompanyDeviceDto implements Comparable<CompanyDeviceDto> {

	private Long uid;
	private DeviceType deviceType;
	private AreaDto areaDto;
	private String name;

	public CompanyDeviceDto() {
		
	}
	
	public CompanyDeviceDto(CompanyDevice companyDevice) {
		this.uid = companyDevice.getUid();
		this.deviceType = companyDevice.getdeviceType();
		this.name = companyDevice.getName();
	}
	
	public final Long getUid() {
		return uid;
	}
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final DeviceType getDeviceType() {
		return deviceType;
	}
	public final void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
		
		if (deviceType == null ) {			
			this.deviceType = DeviceType.OUTROS;
		}	
		else { 
			this.deviceType = deviceType;
		}
	}
	
	public final AreaDto getAreaDto() {
		return areaDto;
	}

	public final void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}
	
	@Override
	public int compareTo(CompanyDeviceDto companyDeviceDto) {
		return companyDeviceDto.getUid().compareTo(this.uid);		
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}	
}
