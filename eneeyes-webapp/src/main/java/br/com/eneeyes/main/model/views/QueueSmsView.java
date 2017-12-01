package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.enums.UnitMeterGases;


@Entity
@Table(name="queue_sms_view")
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
	private Long sensor_id;
	private BigDecimal last_value;
	private Date last_Update;	
	
	@Column(name = "alarm_type")
	private AlarmType alarmType;
	
	private String gas_name;
	
	@Column(name = "unit_meter_gases")
	private UnitMeterGases unitMeterGases;
	
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final SmsStatus getSmsStatus() {
		return smsStatus;
	}

	public final void setSmsStatus(SmsStatus smsStatus) {
		this.smsStatus = smsStatus;
	}

	
	public final String getAlarm_name() {
		return alarm_name;
	}

	public final void setAlarm_name(String alarm_name) {
		this.alarm_name = alarm_name;
	}	
	
	public final String getCelular() {
		return celular;
	}

	public final void setCelular(String celular) {
		this.celular = celular;
	}
	
	public final String getCelular1() {
		return celular1;
	}

	public final void setCelular1(String celular1) {
		this.celular1 = celular1;
	}
	
	public Long getCompany_detector_id() {
		return company_detector_id;
	}
	
	public void setCompany_detector_id(Long company_detector_id) {
		this.company_detector_id = company_detector_id;
	}
	
	public final String getCompany_detector_name() {
		return company_detector_name;
	}

	public final void setCompany_detector_name(String company_detector_name) {
		this.company_detector_name = company_detector_name;
	}
	
	public Long getSensor_id() {
		return sensor_id;
	}
	
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
	
	public final BigDecimal getLast_value() {
		return last_value;
	}

	public final void setLast_value(BigDecimal last_value) {
		this.last_value = last_value;
	}

	public final Date getLast_Update() {
		return last_Update;
	}

	public final void setLast_Update(Date last_Update) {
		this.last_Update = last_Update;
	}
	
	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}
	
	public String getGas_name() {
		return gas_name;
	}
	
	public void setGas_name(String gas_name) {
		this.gas_name = gas_name;
	}

	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}

	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}	
}