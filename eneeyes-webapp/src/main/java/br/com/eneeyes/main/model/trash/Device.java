package br.com.eneeyes.main.model.trash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.DeviceType;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Todos os dipositivos existentes e tipificação
 */

@Entity
@Table(name = "device")
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	@Column(name = "DEVICE_TYPE", columnDefinition = "int default 0")
	private DeviceType deviceType;

	@Enumerated(EnumType.ORDINAL) 
	private DeviceType DeviceType() { 
	    return deviceType; 
	}
	
	public DeviceType getDeviceType() {
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
