package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.state.DeviceType;

@Entity
@Subselect("select * from dash_companies_alarm2")
public class DashCompaniesAlarm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DashCompaniesAlarm() {		
	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;
	
	private Long company_id;	
	private String company_name;
	private String unit_name;
	private String area_name;
	private String company_detector_name;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID", nullable = false)
	private DeviceType deviceType;
	
	private String company_detector_local;
	private Double last_value;
		
	@Column(name = "alarm_type")
	private AlarmType alarmType;
	
	@Column(name = "email_status")
	private EmailStatus emailStatus;
	
	@Column(name = "sigma_status")
	private SigmaStatus sigmaStatus;
	
	@Column(name = "sms_status")
	private SmsStatus smsStatus;
	
	@Column(name = "sound_status")
	private SoundStatus soundStatus;
	
	@Column(name = "alarm_status")
	private AlarmStatus alarmStatus;
	
	private Date first_update;
	private Date last_update;
	private String action;
	
	@Column(name = "GAS_NAME")
	private String gasName;
	
	@Column(name = "UNIT_METER_GASES")
	private UnitMeterGases unitMeterGases;	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public String getCompany_detector_name() {
		return company_detector_name;
	}

	public String getCompany_detector_local() {
		return company_detector_local;
	}

	public Double getLast_value() {
		return last_value;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public SigmaStatus getSigmaStatus() {
		return sigmaStatus;
	}

	public SmsStatus getSmsStatus() {
		return smsStatus;
	}

	public SoundStatus getSoundStatus() {
		return soundStatus;
	}

	public AlarmStatus getAlarmStatus() {
		return alarmStatus;
	}

	public Date getFirst_update() {
		return first_update;
	}

	public Date getLast_update() {
		return last_update;
	}
	
	public String getAction() {
		return action;
	}

	public String getGasName() {
		return gasName;
	}

	public UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}
	
	public DeviceType getDeviceType() {
		return deviceType;
	}
}
