package br.com.eneeyes.main.dto.register;

public abstract class BaseDeviceDto {
	
	String name;
	ManufacturerDto manufacturerDto;	
	String model;
	
	public final String getName() {
		return name;
	}
	
	public final void setName(String name) {
		this.name = name;
	}

	public final ManufacturerDto getManufacturerDto() {
		return manufacturerDto;
	}
	
	public final void setManufacturerDto(ManufacturerDto manufacturerDto) {
		this.manufacturerDto = manufacturerDto;
	}
	
	public final String getModel() {
		return model;
	}
	
	public final void setModel(String model) {
		this.model = model;
	}	

}
