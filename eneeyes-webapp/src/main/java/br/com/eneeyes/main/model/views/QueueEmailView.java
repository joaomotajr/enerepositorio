package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.enums.UnitMeterGases;

@Entity
@Subselect("select * from queue_email_view")
public class QueueEmailView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public QueueEmailView() {		
	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;

	@Column(name = "email_status")
	private EmailStatus emailStatus;

	private String alarm_name;
	private String email;
	private String email1;
	private Long company_detector_id;
	private String company_detector_name;
	private String company_detector_local;
	private String company_name;
	private String unit_name;
	private String area_name;
	private String area_local;
	
	private Long sensor_id;
	private BigDecimal last_value;
	private Date last_Update;
	
	@Column(name = "alarm_type")
	private AlarmType alarmType;
	
	private String gas_name;
		
	@Column(name = "unit_meter_gases")
	private UnitMeterGases unitMeterGases;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public EmailStatus getEmailStatus() {
		return emailStatus;
	}

	public String getAlarm_name() {
		return alarm_name;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail1() {
		return email1;
	}

	public Long getCompany_detector_id() {
		return company_detector_id;
	}

	public String getCompany_detector_name() {
		return company_detector_name;
	}

	public String getCompany_detector_local() {
		return company_detector_local;
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

	public String getArea_local() {
		return area_local;
	}

	public Long getSensor_id() {
		return sensor_id;
	}

	public BigDecimal getLast_value() {
		return last_value;
	}

	public Date getLast_Update() {
		return last_Update;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public String getGas_name() {
		return gas_name;
	}

	public UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}
	
}