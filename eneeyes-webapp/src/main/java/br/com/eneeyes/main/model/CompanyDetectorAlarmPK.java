package br.com.eneeyes.main.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detector_company_alarms database table.
 * 
 */
@Embeddable
public class CompanyDetectorAlarmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COMPANY_DETECTOR_ID", updatable=false, nullable=false)
	private Long companyDetectorId;

	@Column(name="ALARM_ID", updatable=false, nullable=false)
	private Long alarmId;
	
	@Column(name="SENSOR_ID", updatable=false, nullable=false)
	private Long sensorId;

	public CompanyDetectorAlarmPK() {
		
	}
	
	public Long getCompanyDetectorId() {
		return this.companyDetectorId;
	}
	
	public void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}
	
	public Long getAlarmId() {
		return this.alarmId;
	}
	
	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}
	
	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CompanyDetectorAlarmPK)) {
			return false;
		}
		CompanyDetectorAlarmPK castOther = (CompanyDetectorAlarmPK)other;
		return 
			this.companyDetectorId.equals(castOther.companyDetectorId)
			&& this.alarmId.equals(castOther.alarmId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyDetectorId.hashCode();
		hash = hash * prime + this.alarmId.hashCode();
		
		return hash;
	}
}