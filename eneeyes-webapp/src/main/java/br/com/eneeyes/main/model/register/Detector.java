package br.com.eneeyes.main.model.register;

import java.io.UnsupportedEncodingException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.DetectorDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Detectores
 * Será Ponto forte da relação com sensores através de Tabelas associativa 
 */

@Entity
@Table(name = "detector")
public class Detector {
	
	public Detector() {
		
	}
	
	public Detector(DetectorDto dto) {
		this.uid = dto.getUid();
		this.name = dto.getName();
		
		if (dto.getManufacturerDto() != null)
			this.manufacturer = new Manufacturer(dto.getManufacturerDto());
		
		this.model = dto.getModel();		
		
		if (dto.getTransmitterDto() != null)
			this.transmitter = new Transmitter(dto.getTransmitterDto());
		
		if (dto.getImage() != null) {
			try {
					
				byte[] image = null;
				image = dto.getImage().getBytes("US-ASCII");
				this.setImage(image);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	
		}
		
		if (dto.getSensorDto() != null)
			this.sensor = new Sensor(dto.getSensorDto());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="TRANSMITTER_ID", nullable = false)
	private Transmitter transmitter;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="SENSOR_ID", nullable = false)
	private Sensor sensor;
	
	@Column(name = "NAME", nullable = true)
	String name;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="MANUFACTURER_ID", nullable = false)
	private Manufacturer manufacturer;
	
	@Column(name = "MODEL", nullable = true)
	String model;
	
	@Lob
	@Column(name = "IMAGE", nullable = true)
	byte[] image;	

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Transmitter getTransmitter() {
		return transmitter;
	}

	public final void setTransmitter(Transmitter transmitter) {
		this.transmitter = transmitter;
	}
	
	public final String getName() {
		return name;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
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
	
	public final byte[] getImage() {
		return image;
	}

	public final void setImage(byte[] image) {
		this.image = image;
	}
}
