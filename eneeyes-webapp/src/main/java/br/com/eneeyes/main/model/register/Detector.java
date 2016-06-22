package br.com.eneeyes.main.model.register;

import java.util.HashSet;
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
import javax.persistence.OneToMany;
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
public class Detector extends BaseDevice {
	
	public Detector() {
		
	}
	
	public Detector(DetectorDto dto) {
		this.uid = dto.getUid();
		this.name = dto.getName();
		this.manufacturer = dto.getManufacturer();
		this.model = dto.getModel();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="TRANSMITTER_ID", nullable = false)
	private Transmitter transmitter;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "detector_sensors",	 
	joinColumns = @JoinColumn(name = "DETECTOR_ID", referencedColumnName = "UID"), 
	inverseJoinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName = "UID"))	
	private Set<Sensor> sensors = new HashSet<Sensor>();
	
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
}
