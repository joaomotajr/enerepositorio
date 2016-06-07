package br.com.eneeyes.main.model.register;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Todos os dipositivos existentes e tipificação
 */

@Entity
@Table(name = "device")
public class Device extends BaseDevice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@OneToOne(cascade=CascadeType.DETACH, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID")
	private DeviceType deviceType;

	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	
	public final DeviceType getDeviceType() {
		return deviceType;
	}

	public final void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}


}
