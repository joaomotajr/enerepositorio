package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.state.UnitMeter;

@Entity
@Subselect("select * from queue_sms_view")
public class QueueSmsView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public QueueSmsView() {	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;

	@Column(name = "sms_status")
	private SmsStatus smsStatus;

	private String alarm_name;
	private String celular;
	private String celular1;
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
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public SmsStatus getSmsStatus() {
		return smsStatus;
	}

	public String getAlarm_name() {
		return alarm_name;
	}

	public String getCelular() {
		return celular;
	}

	public String getCelular1() {
		return celular1;
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

	public UnitMeter getUnitMeter() {
		return unitMeter;
	}	
}