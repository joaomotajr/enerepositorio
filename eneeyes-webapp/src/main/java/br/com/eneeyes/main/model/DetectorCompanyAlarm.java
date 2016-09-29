package br.com.eneeyes.main.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.DetectorCompanyAlarmDto;


/**
 * The persistent class for the detector_company_alarms database table.
 * 
 */
@Entity
@Table(name="detector_company_alarms")
@NamedQuery(name="DetectorCompanyAlarm.findAll", query="SELECT d FROM DetectorCompanyAlarm d")
public class DetectorCompanyAlarm implements Serializable {
	private static final long serialVersionUID = 1L;

	public DetectorCompanyAlarm() {
		
	}
	
	public DetectorCompanyAlarm(DetectorCompanyAlarmDto dto) {
		
		this.alarm = new Alarm( dto.getAlarmDto());		
		this.sensorId = dto.getSensorId();		
	}
	
	@EmbeddedId
	private DetectorCompanyAlarmPK id;

	@Column(name="SENSOR_ID", nullable=false)
	private BigInteger sensorId;

	@ManyToOne
	@JoinColumn(name="ALARM_ID", nullable=false, insertable=false, updatable=false)
	private Alarm alarm;

	@ManyToOne
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable=false, insertable=false, updatable=false)
	private CompanyDetector companyDetector;

	public DetectorCompanyAlarmPK getId() {
		return this.id;
	}

	public void setId(DetectorCompanyAlarmPK id) {
		this.id = id;
	}

	public BigInteger getSensorId() {
		return this.sensorId;
	}

	public void setSensorId(BigInteger sensorId) {
		this.sensorId = sensorId;
	}

	public Alarm getAlarm() {
		return this.alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public CompanyDetector getCompanyDetector() {
		return this.companyDetector;
	}

	public void setCompanyDetector(CompanyDetector companyDetector) {
		this.companyDetector = companyDetector;
	}

}