package br.com.eneeyes.main.model.register;

import javax.persistence.Column;

public abstract class BaseDevice {
	
	@Column(name = "NAME", nullable = true)
	protected String name;

	@Column(name = "MANUFACTURER", nullable = true)
	protected String manufacturer;
	
	@Column(name = "MODEL", nullable = true)
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
