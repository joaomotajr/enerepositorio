package br.com.eneeyes.main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;

@Entity
@Table(name = "historic_alarm")
public class HistoricAlarm {

    public HistoricAlarm() {
    	
    }
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "VALUE", nullable = false)
	private BigDecimal value;    	
	
	@Column(name="COMPANY_DETECTOR_ID", nullable = false)
	private Long companyDetectorId;

//	@Column(name="SENSOR_ID", nullable = false)
//	private Long sensorId;
	
	@Column(name="HISTORIC_ID", nullable = false)
	private Long historicId;
	
	@Column(name = "ALARM_TYPE", nullable = false)
	private AlarmType alarmType;	
	
	@Column(name="ALARM_ON", nullable = true)
	private Boolean alarmOn;
	
	@Column(name="EMAIL_STATUS", nullable = true)
	private EmailStatus emailStatus;
	
	@Column(name="SMS_STATUS", nullable = true)
	private SmsStatus smsStatus;
	
	@Column(name="ACTION", nullable = true)
	private String action;
	
	@Column(name="SOUND_STATUS", nullable = true)
	private SoundStatus soundStatus;
	
	@Column(name="SIGMA_STATUS", nullable = true)
	private SigmaStatus sigmaStatus;
	
	@Lob
	@Column(name="ALARM", nullable = true)
	private String alarm;
		
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
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

//	public final Long getSensorId() {
//		return sensorId;
//	}
//
//	public final void setSensorId(Long sensorId) {
//		this.sensorId = sensorId;
//	}

	public final Long getHistoricId() {
		return historicId;
	}

	public final void setHistoricId(Long historicId) {
		this.historicId = historicId;
	}

	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public final Boolean getAlarmOn() {
		return alarmOn;
	}

	public final void setAlarmOn(Boolean alarmOn) {
		this.alarmOn = alarmOn;
	}

	public final EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public final void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}

	public final SmsStatus getSmsStatus() {
		return smsStatus;
	}

	public final void setSmsStatus(SmsStatus smsStatus) {
		this.smsStatus = smsStatus;
	}

	public final String getAction() {
		return action;
	}

	public final void setAction(String action) {
		this.action = action;
	}

	public final SoundStatus getSoundStatus() {
		return soundStatus;
	}

	public final void setSoundStatus(SoundStatus soundStatus) {
		this.soundStatus = soundStatus;
	}

	public final SigmaStatus getSigmaStatus() {
		return sigmaStatus;
	}

	public final void setSigmaStatus(SigmaStatus sigmaStatus) {
		this.sigmaStatus = sigmaStatus;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}	
}
