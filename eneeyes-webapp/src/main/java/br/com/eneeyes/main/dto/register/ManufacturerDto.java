package br.com.eneeyes.main.dto.register;

import br.com.eneeyes.main.model.register.Manufacturer;

public class ManufacturerDto {
	private Long uid;
	private String name;
	private String initials;
	
	public ManufacturerDto() {
		
	}
	public ManufacturerDto(Manufacturer manufacturer) {
		
		this.uid = manufacturer.getUid();
		this.name = manufacturer.getName();		
		this.initials = manufacturer.getInitials();
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
	
	public final String getInitials() {
		return initials;
	}
	public final void setInitials(String initials) {
		this.initials = initials;
	}
}
