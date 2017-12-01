package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="dash_detectors_maintenance")
public class DashDetectorsMaintenanceView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DashDetectorsMaintenanceView() {		
	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;
	private String company_name;
	private String unit_name;
	private String area_name;
	private String company_detector_name;	
	private byte[] image;
	private Date install_date;
	private Date last_date;
	private Integer maintenance_interval;	
	
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final String getCompany_name() {
		return company_name;
	}

	public final void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public final String getUnit_name() {
		return unit_name;
	}

	public final void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public final String getArea_name() {
		return area_name;
	}

	public final void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public final String getCompany_detector_name() {
		return company_detector_name;
	}

	public final void setCompany_detector_name(String company_detector_name) {
		this.company_detector_name = company_detector_name;
	}

	public final String getImage() {
			
		if (image != null) {
			byte[] img = image;
			return new String(img);
		}
		else 
			return null;
	}

	public final void setImage(byte[] image) {
		this.image = image;
	}

	public final Date getInstall_date() {
		return install_date;
	}

	public final void setInstall_date(Date install_date) {
		this.install_date = install_date;
	}

	public final Date getLast_date() {
		return last_date;
	}

	public final void setLast_date(Date last_date) {
		this.last_date = last_date;
	}
	
	public final Integer getMaintenance_interval() {
		return maintenance_interval;
	}

	public final void setMaintenance_interval(Integer maintenance_interval) {
		this.maintenance_interval = maintenance_interval;
	}
}