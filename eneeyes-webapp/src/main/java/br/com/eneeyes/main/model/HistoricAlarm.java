package br.com.eneeyes.main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historic_alarm")
public class HistoricAlarm {

    public HistoricAlarm() {
    	
    }
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "VALUE", nullable = true)
	private BigDecimal value;    	
	
	@Column(name="COMPANY_DETECTOR_ID", nullable = false)
	private Long companyDetectorId;

	@Column(name="SENSOR_ID", nullable = false)
	private Long sensorId;
	
	@Column(name="HISTORIC_ID", nullable = false)
	private Long historicId;
	
	@Column(name = "ALARM_TYPE", nullable = false)
	private String alarmType;	
	
	@Column(name="ALARM_ON", nullable = false)
	private Boolean alarmOn;
	
	@Column(name="EMAIL_STATUS", nullable = false)
	private String emailStatus;
	
	@Column(name="SMS_STATUS", nullable = false)
	private String smsStatus;
	
	@Column(name="ACTION_STATUS", nullable = true)
	private String actionStatus;
	
	@Column(name="SOUND_STATUS", nullable = false)
	private String soundStatus;
	
	@Column(name="SIGMA_STATUS", nullable = false)
	private String sigmaStatus;
		
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
	
	public final String getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	
	public final Long getHistoricId() {
		return historicId;
	}

	public final void setHistoricId(Long historicId) {
		this.historicId = historicId;
	}
	
	public final Boolean getAlarmOn() {
		return alarmOn;
	}

	public final void setAlarmOn(Boolean alarmOn) {
		this.alarmOn = alarmOn;
	}
	
	public final String getEmailStatus() {
		return emailStatus;
	}

	public final void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public final String getSmsStatus() {
		return smsStatus;
	}

	public final void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public final String getActionStatus() {
		return actionStatus;
	}

	public final void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public final String getSoundStatus() {
		return soundStatus;
	}

	public final void setSoundStatus(String soundStatus) {
		this.soundStatus = soundStatus;
	}

	public final String getSigmaStatus() {
		return sigmaStatus;
	}

	public final void setSigmaStatus(String sigmaStatus) {
		this.sigmaStatus = sigmaStatus;
	}
}
