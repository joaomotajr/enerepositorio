package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Junior on 06/06/2016.
 * Define tipos de dipositivos aceitos para Cadastro
 */

@Entity
@Table(name = "device_type")
public class DeviceType  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;		

	@Column(name = "NAME", nullable = true)
	private String name;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "MANUFACTURER", nullable = true)
	private String manufacturer;
	
	@Column(name = "MODEL", nullable = true)
	private String model;
	
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

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
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



