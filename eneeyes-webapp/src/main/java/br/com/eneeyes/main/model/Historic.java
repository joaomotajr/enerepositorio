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

import br.com.eneeyes.main.dto.HistoricDto;

@Entity
@Table(name = "historic")
public class Historic {

    public Historic() {
    	
    }
    
    public Historic(HistoricDto dto) {
    	
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();
    	this.value = dto.getValue();    	
    	this.companyDetectorId = dto.getCompanyDetectorId();
    	this.sensorId = dto.getSensorId();
//    	this.sensor = new Sensor(dto.getSensorDto());
//    	this.companyDetector = new CompanyDetector(dto.getCompanyDetectorDto());    	
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
    		
//	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
//	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable = false)
//	private CompanyDetector companyDetector;	
	
	@Column(name="COMPANY_DETECTOR_ID", nullable = false)
	private Long companyDetectorId;
	
//	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
//	@JoinColumn(name="SENSOR_ID", nullable = false)
//	private Sensor sensor;	
	
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
	
//	public CompanyDetector getCompanyDetector() {
//		return companyDetector;
//	}
//
//	public void setCompanyDetector(CompanyDetector companyDetector) {
//		this.companyDetector = companyDetector;
//	}
//	
//	public Sensor getSensor() {
//		return sensor;
//	}
//
//	public void setSensor(Sensor sensor) {
//		this.sensor = sensor;
//	}	
	
}
