package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from alarm_companydevice_view")
public class AlarmCompanyDeviceView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AlarmCompanyDeviceView() {		
	
	}	
	
	@Id	
	@Column(name = "uid")
	private Long uid;
	
	@Column(name = "alarm_id")
	private Long alarmId;
	
	@Column(name = "company_device_id")
	private Long companyDeviceId;
		
	@Column(name = "company_device_name")
	private String companyDetectorName;
	
	@Column(name = "company_device_local")
	private String companyDeviceLocal;
	
	@Column(name = "device_name")
	private String DeviceName;
	
	@Column(name = "range_max")
	private Double rangeMax;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public Long getAlarmId() {
		return alarmId;
	}

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public String getCompanyDeviceLocal() {
		return companyDeviceLocal;
	}

	public String getDeviceName() {
		return DeviceName;
	}

	public Double getRangeMax() {
		return rangeMax;
	}		
	
}