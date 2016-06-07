package br.com.eneeyes.main.model.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

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
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}	

}
