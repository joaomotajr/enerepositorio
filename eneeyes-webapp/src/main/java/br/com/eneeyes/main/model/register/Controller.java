package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.ControllerDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de PLC's e Controladoras
 */

@Entity
@Table(name = "controller")
public class Controller {
	
	public Controller() {
		
	}
	
	public Controller(ControllerDto dto) {
		this.uid = dto.getUid();		
		this.name = dto.getName();
		this.manufacturer = dto.getManufacturer();
		this.model = dto.getModel();	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = true)
	String name;

	@Column(name = "MANUFACTURER", nullable = true)
	String manufacturer;
	
	@Column(name = "MODEL", nullable = true)
	String model;
	
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

	public final String getManufacturer() {
		return manufacturer;
	}

	public final void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public final String getModel() {
		return model;
	}

	public final void setModel(String model) {
		this.model = model;
	}


}


