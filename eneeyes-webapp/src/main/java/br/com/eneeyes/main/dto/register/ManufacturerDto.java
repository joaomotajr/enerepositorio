package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.register.Manufacturer;

public class ManufacturerDto {
	private Long uid;
	private String name;
	
	public ManufacturerDto() {
		
	}
	public ManufacturerDto(Manufacturer manufacturer) {
		
		this.uid = manufacturer.getUid();
		this.name = manufacturer.getName();		
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
}
