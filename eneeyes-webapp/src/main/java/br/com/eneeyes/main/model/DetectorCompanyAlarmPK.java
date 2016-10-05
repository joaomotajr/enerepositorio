package br.com.eneeyes.main.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detector_company_alarms database table.
 * 
 */
@Embeddable
public class DetectorCompanyAlarmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COMPANY_DETECTOR_ID", insertable=false, updatable=false, nullable=false)
	private Long companyDetectorId;

	@Column(name="ALARM_ID", insertable=false, updatable=false, nullable=false)
	private Long alarmId;
	
//	@Column(name="SENSOR_ID", insertable=false, updatable=false, unique=true, nullable=false)
//	private String sensorId;

	public DetectorCompanyAlarmPK() {
		
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetectorCompanyAlarmPK)) {
			return false;
		}
		DetectorCompanyAlarmPK castOther = (DetectorCompanyAlarmPK)other;
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