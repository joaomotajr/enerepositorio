package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;

@Entity
@Table(name = "position_view")
public class PositionView {

    public PositionView() {    	
    }     

	@Id	
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "LAST_VALUE")
	private BigDecimal lastValue;    
	
	@Column(name="COMPANY_DETECTOR_ID", nullable = false)
	private Long companyDetectorId;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="SENSOR_ID", nullable = false)
	private Sensor sensor;
	
	@Column(name = "ALARM_TYPE")
	private AlarmType alarmType;		
	
	@Column(name="HISTORIC_ID", nullable = true)
	private Long historicId;
	
	@Enumerated(EnumType.ORDINAL) 
	private AlarmType AlarmType() { 
	    return alarmType; 
	}
		
	public final Long getUid() {
		return uid;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final BigDecimal getLastValue() {
		return lastValue;
	}

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final Sensor getSensor() {
		return sensor;
	}

	public final Long getHistoricId() {
		return historicId;
	}

	public final AlarmType getAlarmType() {
		return alarmType;
	}		
}