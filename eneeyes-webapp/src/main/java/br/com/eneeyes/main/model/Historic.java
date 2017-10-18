package br.com.eneeyes.main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.eneeyes.main.dto.HistoricDto;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "historic")
@org.hibernate.annotations.Table(
		   appliesTo = "historic",
		   indexes = {
		      @Index(name="idxHistoricDate", columnNames = "LAST_UPDATE"),		      
		      @Index(name="idxHistoricCompanySensorAndDate", columnNames = {"COMPANY_DETECTOR_ID", "SENSOR_ID", "LAST_UPDATE"})
		   }
		)
public class Historic {

    public Historic() {
    	
    }
    
    public Historic(HistoricDto dto) {
    	
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();
    	this.value = dto.getValue();    	
    	this.companyDetectorId = dto.getCompanyDetectorId();
    	this.sensorId = dto.getSensorId();    	
    }
    
    @PostUpdate
    public void updateHistoricId() {
        if (getUid() != null) {
             this.setUid(getUid());
        }
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
}
