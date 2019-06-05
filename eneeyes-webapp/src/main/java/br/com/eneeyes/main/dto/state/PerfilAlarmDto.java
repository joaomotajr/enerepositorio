package br.com.eneeyes.main.dto.state;

import br.com.eneeyes.main.model.state.PerfilAlarm;

public class PerfilAlarmDto {
	private Long uid;
	private String type;
	private String description;
	private String symbol;
	
	public PerfilAlarmDto() {
		
	}
	
	public PerfilAlarmDto(PerfilAlarm e) {		
		this.uid = e.getUid();
		this.type = e.getType();
		this.description = e.getDescription();
		this.symbol = e.getSymbol();
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}	
}
