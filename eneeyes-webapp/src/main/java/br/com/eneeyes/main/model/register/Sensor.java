package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.DetectionType;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Sensores que serão atribuídos ao Detectores.
 * Obs: Sensores e transmissores são componentes do detector e 
 * não entram como Dispositivos, por tanto, tem uma tabela própria
 */

@Entity
@Table(name = "sensor")
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@ManyToOne	
	@JoinTable(name = "detector_sensors", 
	joinColumns = @JoinColumn(name = "SENSOR_ID", referencedColumnName = "UID") , 
	inverseJoinColumns = @JoinColumn(name = "DETECTOR_ID", referencedColumnName = "UID"))
	private Detector detector;
	
	@Column(name = "DETECTION_TYPE", columnDefinition = "int default 0")
	private DetectionType detectionType;	

	@Enumerated(EnumType.ORDINAL) 
	private DetectionType DetectionType() { 
	    return detectionType; 
	}
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Detector getDetector() {
		return detector;
	}

	public final void setDetector(Detector detector) {
		this.detector = detector;
	}

	public final DetectionType getDetectionType() {
		return detectionType;
	}

	public final void setDetectionType(DetectionType detectionType) {
		this.detectionType = detectionType;
	}

}
