package br.com.eneeyes.main.model.state;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.state.UnitMeterDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de PLC's e Controladoras
 */

@Entity
@Cacheable
@Table(name = "unit_meter")
public class UnitMeter {
	
	public UnitMeter() {
		
	}	
	
	public UnitMeter(UnitMeterDto dto) {
		this.uid = dto.getUid();
		this.description = dto.getDescription();
		this.symbol = dto.getSymbol();
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "DESCRIPTION", nullable = true)
	String description;
	
	@Column(name = "SYMBOL", nullable = false)
	private String symbol;
		
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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


