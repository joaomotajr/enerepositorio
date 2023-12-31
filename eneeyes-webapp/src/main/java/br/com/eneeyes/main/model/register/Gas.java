package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.GasDto;

/**
 * Created by Junior on 06/06/2016.
 * cadastrar todas as informações dos gases a serem utilizados pelo 
 * sistema assim como informações descritivas.
 */

@Entity
@Table(name = "gas")
public class Gas {
	
	public Gas() {
		
	}
	
	public Gas(GasDto dto) {
		this.uid = dto.getUid();
		this.name = dto.getName();		
		this.cas = dto.getCas();
		this.formula = dto.getFormula();	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;	

	@Column(name = "NAME", nullable = true)
	private String name;
	
	@Column(name = "CAS", nullable = true)
	private String cas;
	
	@Column(name = "FORMULA", nullable = true)
	private String formula;
			
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

	public final String getCas() {
		return cas;
	}

	public final void setCas(String cas) {
		this.cas = cas;
	}

	public final String getFormula() {
		return formula;
	}

	public final void setFormula(String formula) {
		this.formula = formula;
	}

}
