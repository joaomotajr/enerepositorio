package br.com.eneeyes.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.DeviceType;

@Entity
@Table(name = "CompanyDevices")
public class CompanyDevices {

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
}
