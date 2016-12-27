package br.com.eneeyes.main.model;

import java.util.Date;

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
import javax.persistence.Table;

import org.aspectj.lang.annotation.AfterReturning;

import br.com.eneeyes.main.dto.PositionAlarmDto;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;

@Entity
@Table(name = "position_alarm")
public class PositionAlarm {

    public PositionAlarm() {
    	
    }
    
    public PositionAlarm(PositionAlarmDto dto) {
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();
    	this.lastValue = dto.getLastValue();    	
    	this.sensor = new Sensor(dto.getSensorDto());
    	this.alarmType = dto.getAlarmType();
    	this.alarmStatus = dto.getAlarmStatus();
    	this.statusUpdate = dto.getStatusUpdate(); 
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "LAST_VALUE", nullable = true)
	private Double lastValue;
    		
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable = false)
	private CompanyDetector companyDetector;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="SENSOR_ID", nullable = false)
	private Sensor sensor;
	    
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
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Double getLastValue() {
		return lastValue;
	}

	public void setLastValue(Double lastValue) {
		this.lastValue = lastValue;
	}
	
	public CompanyDetector getCompanyDetector() {
		return companyDetector;
	}

	public void setCompanyDetector(CompanyDetector companyDetector) {
		this.companyDetector = companyDetector;
	}
	
	public final Sensor getSensor() {
		return sensor;
	}

	public final void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public Date getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
}
