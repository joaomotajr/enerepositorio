package br.com.eneeyes.main.model.state;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de PLC's e Controladoras
 */

@Entity
@Cacheable
@Table(name = "device_type")
public class DeviceType {
	
	public DeviceType() {
		
	}	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "TYPE", nullable = true)
	String type;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

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
	
}


