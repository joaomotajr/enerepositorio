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
		this.meter = dto.getMeter();
		this.description = dto.getDescription();
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "METER", nullable = true)
	String meter;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
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


