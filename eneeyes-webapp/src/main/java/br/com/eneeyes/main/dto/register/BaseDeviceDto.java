package br.com.eneeyes.main.dto.register;

public abstract class BaseDeviceDto {
	
	protected String name;
	protected String manufacturer;
	protected String model;
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getManufacturer() {
		return manufacturer;
	}
	public final void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public final String getModel() {
		return model;
	}
	public final void setModel(String model) {
		this.model = model;
	}	

}
