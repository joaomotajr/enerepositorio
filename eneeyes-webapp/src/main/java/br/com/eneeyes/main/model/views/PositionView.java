package br.com.eneeyes.main.model.views;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;

@Entity
@Subselect("select * from position_view")
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
	
	@Column(name="COMPANY_DEVICE_ID", nullable = false)
	private Long companyDeviceId;
	
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

	public final Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public final Long getHistoricId() {
		return historicId;
	}

	public final AlarmType getAlarmType() {
		return alarmType;
	}		
}
