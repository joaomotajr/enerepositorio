package br.com.eneeyes.main.model;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.PositionDto;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;

@Entity
@Table(name = "position")
public class Position {

    public Position() {
    	
    }
    
    public Position(PositionDto dto) {
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();    	
    	this.lastValue = dto.getLastValue();    	
    	this.sensor = new Sensor(dto.getSensorDto());
    	this.alarmType = dto.getAlarmType();
    	this.companyDetector = new CompanyDetector(dto.getCompanyDetectorDto());
    	this.historic.setUid(dto.getHistoricDto().getUid()); 
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "LAST_VALUE", nullable = true)
	private BigDecimal lastValue;
    		
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable = false)
	private CompanyDetector companyDetector;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="SENSOR_ID", nullable = false)
	private Sensor sensor;
	
	@Column(name = "ALARM_TYPE", columnDefinition = "int default 0", nullable = false)
	private AlarmType alarmType;
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="HISTORIC_ID", nullable = true)
	private Historic historic;

	@Enumerated(EnumType.ORDINAL) 
	private AlarmType AlarmType() { 
	    return alarmType; 
	}
		
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
	
	public final Sensor getSensor() {
		return sensor;
	}

	public final void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public final Historic getHistoric() {
		return historic;
	}

	public final void setHistoric(Historic historic) {
		this.historic = historic;
	}	
}
