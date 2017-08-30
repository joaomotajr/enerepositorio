package br.com.eneeyes.main.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.model.register.Detector;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro dos Detectores das Empresas.
 */

@Entity
@Table(name = "company_detector_shadow")
public class CompanyDetectorShadow {
	public CompanyDetectorShadow() {   	
    
    }	  

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "SERIAL_NUMBER", nullable = true)
	private String serialNumber;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "DATE", nullable = true)
	private Date date;  
		
	@Column(name = "LOCAL", nullable = true)		
	private String local;
	
	@Column(name = "LATITUDE", nullable = true)		
	private Double latitude;
	
	@Column(name = "LONGITUDE", nullable = true)		
	private Double longitude;
	
	@Column(name = "DELIVERY_DATE", nullable = true)
	private Date deliveryDate;
	
	@Column(name = "GARANTY_DAYS", nullable = true)
	private Integer garantyDays;
	
	@Column(name = "DESCRIPTION_DELIVERY", nullable = true)
	private String descriptionDelivery;
	
	@Column(name = "INSTALL_DATE", nullable = true)
	private Date installDate;
	
	@Column(name = "MAINTENANCE_INTERVAL", nullable = true)
	private Integer maintenanceInterval;
		
	@Column(name = "DESCRIPTION_INSTALL", nullable = true)
	private String descriptionInstall;
  	
	@Column(name="COMPANY_DEVICE_ID", nullable = false)
	private Long companyDeviceId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="DETECTOR_ID", nullable = false)
	private Detector detector;
				
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {	
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}
	
	public final String getLocal() {
		return local;
	}

	public final void setLocal(String local) {
		this.local = local;
	}
	
	public final String getSerialNumber() {
		return serialNumber;
	}

	public final void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public final Double getLatitude() {
		return latitude;
	}

	public final void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public final Double getLongitude() {
		return longitude;
	}

	public final void setLongitude(Double longitude) {
		this.longitude = longitude;
	}	
	
	public final Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public final void setCompanyDeviceId(Long companyDeviceId) {
		this.companyDeviceId = companyDeviceId;
	}

	public Detector getDetector() {
		return detector;
	}

	public void setDetector(Detector detector) {
		this.detector = detector;
	}
	
	public final Date getDeliveryDate() {
		return deliveryDate;
	}

	public final void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public final Integer getGarantyDays() {
		return garantyDays;
	}

	public final void setGarantyDays(Integer garantyDays) {
		this.garantyDays = garantyDays;
	}

	public final String getDescriptionDelivery() {
		return descriptionDelivery;
	}

	public final void setDescriptionDelivery(String descriptionDelivery) {
		this.descriptionDelivery = descriptionDelivery;
	}

	public final Date getInstallDate() {
		return installDate;
	}

	public final void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public final Integer getMaintenanceInterval() {
		return maintenanceInterval;
	}

	public final void setMaintenanceInterval(Integer maintenanceInterval) {
		this.maintenanceInterval = maintenanceInterval;
	}

	public final String getDescriptionInstall() {
		return descriptionInstall;
	}

	public final void setDescriptionInstall(String descriptionInstall) {
		this.descriptionInstall = descriptionInstall;
	}

}
