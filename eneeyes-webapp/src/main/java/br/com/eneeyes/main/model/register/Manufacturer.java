package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.ManufacturerDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Fabricantes
 */

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
	
	public Manufacturer() {
		
	}
	
	public Manufacturer(ManufacturerDto dto) {
		this.uid = dto.getUid();		
		this.name = dto.getName();
		this.initials = dto.getInitials();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = true)
	String name;
	
	@Column(name = "INITIALS", nullable = true)
	String initials;
		
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


