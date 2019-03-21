package br.com.eneeyes.main.dto.state;

import br.com.eneeyes.main.model.state.UnitMeter;

public class UnitMeterDto {
	private Long uid;
	private String description;
	private String symbol;
			
	public UnitMeterDto() {
		
	}
	
	public UnitMeterDto(UnitMeter e) {		
		this.uid = e.getUid();
		this.description = e.getDescription();
		this.symbol = e.getSymbol();		
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public String getDescription() {
		return description;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setDescription(String description) {
		this.description = description;
	}		
}
