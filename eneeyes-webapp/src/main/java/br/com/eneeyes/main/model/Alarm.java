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
import javax.persistence.Table;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.enums.DeviceType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.register.Gas;
import br.com.eneeyes.main.model.views.CompanyView;

@Entity
@Table(name = "alarm")
public class Alarm {
	
	public Alarm() {
		
	}
	
	public Alarm(AlarmDto dto) {
		
		this.uid = dto.getUid();
		this.name = dto.getName();		
		this.deviceType = dto.getDeviceType();
		this.unitMeterGases = dto.getUnitMeterGases();
		
		if(dto.getGasDto() != null)
			this.gas = new Gas(dto.getGasDto());		
				
		this.alarm1 = dto.getAlarm1();
		this.alarm2 = dto.getAlarm2();
		this.alarm3 = dto.getAlarm3();
		this.alarm11 = dto.getAlarm11();
		this.alarm22 = dto.getAlarm22();
		this.alarm33 = dto.getAlarm33();
		this.alarmSound = dto.getAlarmSound();
		this.alarmEmail = dto.getAlarmEmail();				
		this.email = dto.getEmail();
		this.email1 = dto.getEmail1();
		this.alarmSms = dto.getAlarmSms();
		this.celular = dto.getCelular();
		this.celular1 = dto.getCelular1();
		this.alarmOn = dto.getAlarmOn();
		this.alarm2On = dto.getAlarm2On();
		this.alarm3On = dto.getAlarm3On();
		this.alarmSigma = dto.getAlarmSigma();
		this.alarmAction = dto.getAlarmAction();
		this.action1 = dto.getAction1();
		this.action2 = dto.getAction2();
		this.action3 = dto.getAction3();
		this.action4 = dto.getAction4();
		this.company = dto.getCompanyDto();		
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "UNIT_METER_GASES", columnDefinition = "int default 0")
	private UnitMeterGases unitMeterGases;

	@Enumerated(EnumType.ORDINAL) 
	private UnitMeterGases UnitMeterGases() { 
	    return unitMeterGases; 
	}
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="GAS_ID", nullable = true)
	private Gas gas;		
	
	@Column(name = "DEVICE_TYPE", columnDefinition = "int default 0")
	private DeviceType deviceType;
	
	@Enumerated(EnumType.ORDINAL) 
	private DeviceType DeviceType() { 
	    return deviceType; 
	}
	
	@Column(name = "ALARM_1")		
	private Double alarm1;
	
	@Column(name = "ALARM_2")		
	private Double alarm2;
	
	@Column(name = "ALARM_3")		
	private Double alarm3;
	
	@Column(name = "ALARM_11", nullable = true)		
	private Double alarm11;
	
	@Column(name = "ALARM_22", nullable = true)		
	private Double alarm22;
	
	@Column(name = "ALARM_33", nullable = true)		
	private Double alarm33;
	
	@Column(name = "ALARM_ON", nullable = true, columnDefinition = "Boolean default true")		
	private Boolean alarmOn;
	
	@Column(name = "ALARM2_ON", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarm2On;
	
	@Column(name = "ALARM3_ON", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarm3On;
	
	@Column(name = "ALARM_SIGMA", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSigma;
	
	@Column(name = "ALARM_SOUND", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSound;
	
	@Column(name = "ALARM_EMAIL", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmEmail;
	
	@Column(name = "EMAIL", nullable = true)		
	private String email;
	
	@Column(name = "EMAIL1", nullable = true)		
	private String email1;
	
	@Column(name = "ALARM_SMS", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSms;
	
	@Column(name = "CELULAR", nullable = true)		
	private String celular;
	
	@Column(name = "CELULAR1", nullable = true)		
	private String celular1;
	
	@Column(name = "ALARM_ACTION", nullable = true)		
	private Boolean alarmAction;
	
	@Column(name = "ACTION1", nullable = true, length=300)		
	private String action1;
	
	@Column(name = "ACTION2", nullable = true, length=300)		
	private String action2;
	
	@Column(name = "ACTION3", nullable = true, length=300)		
	private String action3;	
	
	@Column(name = "ACTION4", nullable = true, length=300)		
	private String action4;	
		
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID", nullable = false)
	private CompanyView company;
		
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
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

	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}

	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}
	
	public final Gas getGas() {
		return gas;
	}

	public final void setGas(Gas gas) {
		this.gas = gas;
	}	 

	public final Double getAlarm1() {
		return alarm1;
	}

	public final void setAlarm1(Double alarm1) {
		this.alarm1 = alarm1;
	}

	public final Double getAlarm2() {
		return alarm2;
	}

	public final void setAlarm2(Double alarm2) {
		this.alarm2 = alarm2;
	}

	public final Double getAlarm3() {
		return alarm3;
	}	

	public Double getAlarm11() {
		return alarm11;
	}

	public void setAlarm11(Double alarm11) {
		this.alarm11 = alarm11;
	}

	public Double getAlarm22() {
		return alarm22;
	}

	public void setAlarm22(Double alarm22) {
		this.alarm22 = alarm22;
	}

	public Double getAlarm33() {
		return alarm33;
	}

	public void setAlarm33(Double alarm33) {
		this.alarm33 = alarm33;
	}

	public final void setAlarm3(Double alarm3) {
		this.alarm3 = alarm3;
	}

	public final Boolean getAlarmOn() {
		return alarmOn;
	}

	public final void setAlarmOn(Boolean alarmOn) {
		this.alarmOn = alarmOn;
	}	
	
	public Boolean getAlarm2On() {
		return alarm2On;
	}

	public void setAlarm2On(Boolean alarm2On) {
		this.alarm2On = alarm2On;
	}

	public Boolean getAlarm3On() {
		return alarm3On;
	}

	public void setAlarm3On(Boolean alarm3On) {
		this.alarm3On = alarm3On;
	}

	public final Boolean getAlarmSigma() {
		return alarmSigma;
	}

	public final void setAlarmSigma(Boolean alarmSigma) {
		this.alarmSigma = alarmSigma;
	}
	
	public CompanyView getCompany() {
		return company;
	}

	public void setCompany(CompanyView company) {
		this.company = company;
	}
	
	public final Boolean getAlarmSound() {
		return alarmSound;
	}

	public final void setAlarmSound(Boolean alarmSound) {
		this.alarmSound = alarmSound;
	}
	
	public final Boolean getAlarmEmail() {
		return alarmEmail;
	}

	public final void setAlarmEmail(Boolean alarmEmail) {
		this.alarmEmail = alarmEmail;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}
	
	public final String getEmail1() {
		return email1;
	}

	public final void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	public Boolean getAlarmSms() {
		return alarmSms;
	}

	public void setAlarmSms(Boolean alarmSms) {
		this.alarmSms = alarmSms;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public final String getCelular1() {
		return celular1;
	}

	public final void setCelular1(String celular1) {
		this.celular1 = celular1;
	}
	
	public final Boolean getAlarmAction() {
		return alarmAction;
	}

	public final void setAlarmAction(Boolean alarmAction) {
		this.alarmAction = alarmAction;
	}

	public final String getAction1() {
		return action1;
	}

	public final void setAction1(String action1) {
		this.action1 = action1;
	}

	public final String getAction2() {
		return action2;
	}

	public final void setAction2(String action2) {
		this.action2 = action2;
	}

	public final String getAction3() {
		return action3;
	}

	public final void setAction3(String action3) {
		this.action3 = action3;
	}

	public final String getAction4() {
		return action4;
	}

	public final void setAction4(String action4) {
		this.action4 = action4;
	}	
}
