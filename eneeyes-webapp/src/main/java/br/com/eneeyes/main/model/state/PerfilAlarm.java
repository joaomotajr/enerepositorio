package br.com.eneeyes.main.model.state;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.state.PerfilAlarmDto;

/**
 * Created by Junior on 06/06/2016.
 * 
 */

@Entity
@Cacheable
@Table(name = "perfil_alarm")
public class PerfilAlarm {
	
	public PerfilAlarm() {
		
	}	
	
	public PerfilAlarm(PerfilAlarmDto dto) {		
		this.uid = dto.getUid();
		this.type = dto.getType();	
		this.description = dto.getDescription();
		this.symbol = dto.getSymbol();		
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "TYPE", nullable = true)
	String type;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "SYMBOL", nullable = false)
	private String symbol;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
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