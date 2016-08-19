package br.com.eneeyes.main.model.register;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.DetectorDto;
import br.com.eneeyes.main.dto.register.SensorDto;

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
		
		if(dto.getSensorsDto() != null)
			this.sensors = parseSensors(dto.getSensorsDto());
	}
	
	private final Set<Sensor> parseSensors(List<SensorDto> sensors) {		
		Set<Sensor> lista = new HashSet<Sensor>();		
		
		for (SensorDto item   : sensors) {			
			lista.add(new Sensor(item));			
		}		
		return lista;		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="TRANSMITTER_ID", nullable = false)
	private Transmitter transmitter;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "detector_sensors",	 
	joinColumns = @JoinColumn(name = "DETECTOR_ID", referencedColumnName = "UID"), 
	inverseJoinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName = "UID"))	
	private Set<Sensor> sensors = new HashSet<Sensor>();
	
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

	public final Set<Sensor> getSensors() {
		return sensors;
	}

	public final void setSensores(Set<Sensor> sensors) {
		this.sensors = sensors;
	}
	
	public final String getName() {
		return name;
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
