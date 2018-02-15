package br.com.eneeyes.main.model;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.PositionAlarmDto;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;

@Entity
@Table(name = "position_alarm")
public class PositionAlarm {

    public PositionAlarm() {
    	
    }
    
    public PositionAlarm(PositionAlarmDto dto) {
    	this.uid = dto.getUid();
    	this.firstUpdate = dto.getFirstUpdate();
    	this.lastUpdate = dto.getLastUpdate();    	
    	this.lastValue = dto.getLastValue();    	
    	this.companyDetector = new CompanyDetector(dto.getCompanyDetectorDto());
    	this.alarmType = dto.getAlarmType();
    	this.alarmStatus = dto.getAlarmStatus();
    	this.statusUpdate = dto.getStatusUpdate();
    	this.emailStatus = dto.getEmailStatus();
    	this.smsStatus = dto.getSmsStatus();
    	this.action = dto.getAction();
    	this.soundStatus = dto.getSoundStatus();
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "FIRST_UPDATE", nullable = false)
	private Date firstUpdate;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "LAST_VALUE", nullable = true)
	private BigDecimal lastValue;
    		
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable = false)
	private CompanyDetector companyDetector;
	    
	@Column(name = "ALARM_TYPE", columnDefinition = "int default 0")
	private AlarmType alarmType;	

	@Enumerated(EnumType.ORDINAL) 
	private AlarmType AlarmType() { 
	    return alarmType; 
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}
	
	@Column(name = "ALARM_STATUS", columnDefinition = "int default 0", nullable = true)
	private AlarmStatus alarmStatus;	

	@Enumerated(EnumType.ORDINAL) 
	private AlarmStatus AlarmStatus() { 
	    return alarmStatus; 
	}

	public final AlarmStatus getAlarmStatus() {
		return alarmStatus;
	}

	public final void setAlarmStatus(AlarmStatus alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	
	@Column(name = "STATUS_UPDATE", nullable = true)
	private Date statusUpdate;
	
	@Column(name = "EMAIL_STATUS", columnDefinition = "int default 0", nullable = true)
	private EmailStatus emailStatus;	

	@Enumerated(EnumType.ORDINAL) 
	private EmailStatus EmailStatus() { 
	    return emailStatus; 
	}
	
	public final EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public final void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}	
	
	@Column(name = "SMS_STATUS", columnDefinition = "int default 0", nullable = true)
	private SmsStatus smsStatus;	

	@Enumerated(EnumType.ORDINAL) 
	private SmsStatus SmsStatus() { 
	    return smsStatus; 
	}
	
	public final SmsStatus getSmsStatus() {
		return smsStatus;
	}

	public final void setSmsStatus(SmsStatus smsStatus) {
		this.smsStatus = smsStatus;
	}
	
	@Column(name = "ACTION", nullable = true, length=300)		
	private String action;	
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy="positionAlarm", cascade = CascadeType.REMOVE)
	private Set<PositionAlarmMessage> positionAlarmMessages = new HashSet<PositionAlarmMessage>();
	
	@Column(name = "SOUND_STATUS", columnDefinition = "int default 0" , nullable = true)
	private SoundStatus soundStatus;
	
	public final SoundStatus getSoundStatus() {
		return soundStatus;
	}

	public final void setSoundStatus(SoundStatus soundStatus) {
		this.soundStatus = soundStatus;
	}

	@Enumerated(EnumType.ORDINAL) 
	private SoundStatus soundStatus() { 
	    return soundStatus; 
	}
	
	@Column(name = "SIGMA_STATUS", columnDefinition = "int default 0" , nullable = true)
	private SigmaStatus sigmaStatus;
	
	public final SigmaStatus getSigmaStatus() {
		return sigmaStatus;
	}

	public final void setSigmaStatus(SigmaStatus sigmaStatus) {
		this.sigmaStatus = sigmaStatus;
	}

	@Enumerated(EnumType.ORDINAL) 
	private SigmaStatus sigmaStatus() { 
	    return sigmaStatus; 
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final Date getFirstUpdate() {
		return firstUpdate;
	}

	public final void setFirstUpdate(Date firstUpdate) {
		this.firstUpdate = firstUpdate;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLastValue() {
		return lastValue;
	}

	public void setLastValue(BigDecimal lastValue) {
		this.lastValue = lastValue;
	}
	
	public CompanyDetector getCompanyDetector() {
		return companyDetector;
	}

	public void setCompanyDetector(CompanyDetector companyDetector) {
		this.companyDetector = companyDetector;
	}	
	
	public Date getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	
	public final String getAction() {
		return action;
	}

	public final void setAction(String action) {
		this.action = action;
	}
	
	public final Set<PositionAlarmMessage> getPositionAlarmMessages() {
		return positionAlarmMessages;
	}

	public final void setPositionAlarmMessages(Set<PositionAlarmMessage> positionAlarmMessages) {
		this.positionAlarmMessages = positionAlarmMessages;
	}
	
}
