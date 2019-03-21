package br.com.eneeyes.main.dto.state;

import br.com.eneeyes.main.model.state.UnitMeter;

public class UnitMeterDto {
	private Long uid;
	private String meter;
	private String description;
		
	public UnitMeterDto() {
		
	}
	
	public UnitMeterDto(UnitMeter e) {		
		this.uid = e.getUid();
		this.meter = e.getMeter();
		this.description = e.getDescription();		
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public String getMeter() {
		return meter;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
}
