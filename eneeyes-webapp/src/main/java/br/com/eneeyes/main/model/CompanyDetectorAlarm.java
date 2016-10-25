package br.com.eneeyes.main.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;


/**
 * The persistent class for the detector_company_alarms database table.
 * 
 */
@Entity
@Table(name="company_detector_alarms")
@NamedQuery(name="CompanyDetectorAlarm.findAll", query="SELECT d FROM CompanyDetectorAlarm d")
public class CompanyDetectorAlarm implements Serializable {
	private static final long serialVersionUID = 1L;

	public CompanyDetectorAlarm() {
		
	}
	
	public CompanyDetectorAlarm(CompanyDetectorAlarmDto dto) {
				
		this.alarm = new Alarm( dto.getAlarmDto());
		this.companyDetector = new CompanyDetector ( dto.getCompanyDetectorDto() );

		this.uid = (new CompanyDetectorAlarmPK());
		this.uid.setAlarmId(dto.getAlarmDto().getUid());
		this.uid.setCompanyDetectorId(dto.getCompanyDetectorDto().getUid());
		this.uid.setSensorId(dto.getSensorId());

	}
	
	@EmbeddedId
	private CompanyDetectorAlarmPK uid;

	@ManyToOne
	@JoinColumn(name="ALARM_ID", nullable=false, insertable=false, updatable=false)
	private Alarm alarm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable=false, insertable=false, updatable=false)
	private CompanyDetector companyDetector;

	public CompanyDetectorAlarmPK getId() {
		return this.uid;
	}

	public void setId(CompanyDetectorAlarmPK uid) {
		this.uid = uid;
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