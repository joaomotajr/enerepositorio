package br.com.eneeyes.main.model;

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

import br.com.eneeyes.main.dto.CompanyDeviceDto;
import br.com.eneeyes.main.model.enums.DeviceType;

@Entity
@Table(name = "company_device")
public class CompanyDevice {
	
	public CompanyDevice() {
	
	}
	
	public CompanyDevice(CompanyDeviceDto dto) {
		
		this.uid = dto.getUid();
		this.deviceType = dto.getDeviceType();
		this.name = dto.getName();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "DEVICE_TYPE", columnDefinition = "int default 0")
	private DeviceType deviceType;
	
	@Enumerated(EnumType.ORDINAL) 
	private DeviceType DeviceType() { 
	    return deviceType; 
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AREA_ID", nullable=false)
    private Area area;
		
	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "companyDevice")
	private CompanyDetector companyDetector;
	
	@Column(name = "NAME", nullable = true)
	private String name;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public DeviceType getdeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {		
		if (deviceType == null ) {			
			this.deviceType = DeviceType.OUTROS;
		}	
		else { 
			this.deviceType = deviceType;
		}
	}
	
	public final Area getArea() {
		return area;
	}

	public final void setArea(Area area) {
		this.area = area;
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
}
