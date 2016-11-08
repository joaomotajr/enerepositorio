package br.com.eneeyes.main.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.model.register.Sensor;

@Entity
@Table(name = "historic")
public class Historic {

    public Historic() {
    	
    }
    
    public Historic(HistoricDto dto) {
    	this.uid = dto.getUid();
    	this.update = dto.getUpdate();
    	this.value = dto.getValue();    	
    	this.sensor = new Sensor(dto.getSensorDto());
    	this.companyDetector = new CompanyDetector(dto.getCompanyDetectorDto());
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "UP_DATE", nullable = false)
	private Date update;

	@Column(name = "VALUE", nullable = true)
	private Double value;
    		
	@ManyToOne(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_DETECTOR_ID", nullable = false)
	private CompanyDetector companyDetector;
	
	@ManyToOne(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="SENSOR_ID", nullable = false)
	private Sensor sensor;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
}
