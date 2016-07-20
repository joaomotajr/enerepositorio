package br.com.eneeyes.main.model.register;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.TransmitterDto;
import br.com.eneeyes.main.model.enums.CommProtocol;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Transmissores
 * Obs: Sensores e transmissores são componentes do detector e não entram 
 * como Dispositivos, por tanto, tem uma tabela própria
 */

@Entity
@Table(name = "transmitter")
public class Transmitter {
	
	public Transmitter() {
		
	}
	
	public Transmitter(TransmitterDto dto) {
	
		this.uid = dto.getUid();
		this.name = dto.getName();
		this.manufacturer = dto.getManufacturer();
		this.model = dto.getModel();
		this.commProtocol = dto.getCommProtocol();		 
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = true)
	String name;

//	@Column(name = "MANUFACTURER", nullable = true)
//	String manufacturer;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="MANUFACTURER_ID", nullable = false)
	private Manufacturer manufacturer;
	
	
	@Column(name = "MODEL", nullable = true)
	String model;
	
	@Column(name = "COMM_PROTOCOL", columnDefinition = "int default 0")
	private CommProtocol commProtocol;	

	@Enumerated(EnumType.ORDINAL) 
	private CommProtocol CommProtocol() { 
	    return commProtocol; 
	}
		
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

//	public final String getManufacturer() {
//		return manufacturer;
//	}
//
//	public final void setManufacturer(String manufacturer) {
//		this.manufacturer = manufacturer;
//	}
	
	public final Manufacturer getManufacturer() {
		return manufacturer;
	}

	public final void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public final String getModel() {
		return model;
	}

	public final void setModel(String model) {
		this.model = model;
	}
	
	public final CommProtocol getCommProtocol() {
		return commProtocol;
	}

	public final void setCommProtocol(CommProtocol commProtocol) {
		this.commProtocol = commProtocol;
	}
}
