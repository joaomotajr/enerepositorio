package br.com.eneeyes.main.model.register;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.enums.DetectionType;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Sensores que serão atribuídos ao Detectores.
 * Obs: Sensores e transmissores são componentes do detector e 
 * não entram como Dispositivos, por tanto, tem uma tabela própria
 */

@Entity
@Table(name = "sensor")
public class Sensor extends BaseDevice {
	
	public Sensor() {
		
	}
	
	public Sensor(SensorDto dto) {
		this.uid = dto.getUid();		
		this.detectionType = dto.getDetectionType();
		this.name = dto.getName();
		this.manufacturer = dto.getManufacturer();
		this.model = dto.getModel();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "DETECTION_TYPE", columnDefinition = "int default 0")
	private DetectionType detectionType;	

	@Enumerated(EnumType.ORDINAL) 
	private DetectionType DetectionType() { 
	    return detectionType; 
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "sensor_gases",	 
	joinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName = "UID"), 
	inverseJoinColumns = @JoinColumn(name = "GASES_ID", referencedColumnName = "UID"))	
	private Set<Gas> gases = new HashSet<Gas>();
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final DetectionType getDetectionType() {
		return detectionType;
	}

	public final void setDetectionType(DetectionType detectionType) {
		this.detectionType = detectionType;
	}
	
	public final Set<Gas> getGases() {
		return gases;
	}

	public final void setGases(Set<Gas> gases) {
		this.gases = gases;
	}
}
