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
import javax.persistence.PostLoad;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.PositionDto;
import br.com.eneeyes.main.model.enums.AlarmType;

@Entity
@Table(name = "position")
public class Position {

    public Position() {
    	
    }
    
    public Position(PositionDto dto) {
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();    	
    	this.lastValue = dto.getLastValue();
    	this.companyDevice = new CompanyDevice(dto.getCompanyDeviceDto());
    	this.alarmType = dto.getAlarmType();   	    	

    	this.historicId = dto.getHistoricId();
    }
    
    @PostLoad
    public void updateHistoricId() {
        if (getHistoricId() != null) {
             this.historicId = getHistoricId();
        }
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
	@JoinColumn(name="COMPANY_DEVICE_ID", nullable = false)
	private CompanyDevice companyDevice;
	
	@Column(name = "ALARM_TYPE", columnDefinition = "int default 0", nullable = false)
	private AlarmType alarmType;
		
	@Column(name="HISTORIC_ID", nullable = true)
	private Long historicId;
	
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
	
	public CompanyDevice getCompanyDevice() {
		return companyDevice;
	}

	public void setCompanyDevice(CompanyDevice companyDevice) {
		this.companyDevice = companyDevice;
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public final Long getHistoricId() {
		return historicId;
	}

	public final void setHistoricId(Long historicId) {
		this.historicId = historicId;
	}	
}
