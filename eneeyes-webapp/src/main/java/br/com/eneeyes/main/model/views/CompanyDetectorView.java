package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from company_detector_view")
public class CompanyDetectorView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CompanyDetectorView() {		
	
	}	
	
	@Id
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "COMPANY_DETECTOR_ID")
	private Long companyDetectorId;
	
	@Column(name = "COMPANY_DETECTOR_NAME")
	private String companyDetectorName;
	
	@Column(name = "DETECTOR_ID")
	private Long detectorId;
	
	@Column(name = "DETECTOR_NAME")
	private String detectorName;
	
	@Column(name = "SENSOR_ID")
	private Long sensorId;
	
	@Column(name = "SENSOR_NAME")
	private String sensorName;

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public final void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
	}

	public final Long getDetectorId() {
		return detectorId;
	}

	public final void setDetectorId(Long detectorId) {
		this.detectorId = detectorId;
	}

	public final String getDetectorName() {
		return detectorName;
	}

	public final void setDetectorName(String detectorName) {
		this.detectorName = detectorName;
	}

	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public final String getSensorName() {
		return sensorName;
	}

	public final void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	
}