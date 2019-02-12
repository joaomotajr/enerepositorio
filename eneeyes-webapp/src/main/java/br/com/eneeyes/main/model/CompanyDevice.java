package br.com.eneeyes.main.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.CompanyDeviceDto;
import br.com.eneeyes.main.model.state.DeviceType;

@Entity
@Table(name = "company_device")
public class CompanyDevice {
	
	public CompanyDevice() {
	
	}
	
	public CompanyDevice(Long uid) {
		
		this.uid = uid;
		
	}
	
	public CompanyDevice(CompanyDeviceDto dto) {
		
		this.uid = dto.getUid();
		this.deviceType = dto.getDeviceType();
		this.name = dto.getName();
		
		if (dto.getAlarmDto()  != null) 
			this.alarm = new Alarm(dto.getAlarmDto());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID", nullable = false)	
	private DeviceType deviceType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AREA_ID", nullable=false)
    private Area area;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyDevice", cascade = CascadeType.REMOVE)
	private Set<PositionAlarm> positionAlarm;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyDevice", cascade = CascadeType.REMOVE)
	private Set<Position> position;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="ALARM_ID", nullable = true)
	private Alarm alarm;
	
	@Column(name = "NAME", nullable = true)
	private String name;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
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

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	
}
