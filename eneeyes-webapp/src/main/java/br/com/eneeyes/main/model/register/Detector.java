package br.com.eneeyes.main.model.register;

import java.util.ArrayList;
import java.util.List;

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

/**
 * Created by Junior on 06/06/2016.
 * Define tipos de dipositivos aceitos para Cadastro
 */

@Entity
@Table(name = "detector")
public class Detector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
//	@OneToOne(cascade=CascadeType.ALL, optional = true, fetch = FetchType.EAGER)
//	@JoinColumn(name="TRANSMITTER_ID")
//	private Transmitter transmitter;
	
	@OneToOne(mappedBy="detector", cascade=CascadeType.ALL, fetch = FetchType.EAGER)	
	private Transmitter transmitter;
	
	@OneToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "detector_sensors", joinColumns = @JoinColumn(name = "DETECTOR_ID", referencedColumnName = "UID"), 
								inverseJoinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName = "UID"))
	private List<Sensor> funcionarios = new ArrayList<Sensor>();	
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}	
	public Transmitter getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(Transmitter transmitter) {
		this.transmitter = transmitter;
	}

	public List<Sensor> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Sensor> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
