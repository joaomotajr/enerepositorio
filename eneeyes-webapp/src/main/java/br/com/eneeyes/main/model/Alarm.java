package br.com.eneeyes.main.model;

import java.util.HashSet;
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

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.register.Gas;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.model.state.PerfilAlarm;
import br.com.eneeyes.main.model.state.UnitMeter;
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
		this.unitMeter = dto.getUnitMeter();
		
		if(dto.getGasDto() != null)
			this.gas = new Gas(dto.getGasDto());		
				
		this.alarm1 = dto.getAlarm1();
		this.alarm2 = dto.getAlarm2();
		this.alarm3 = dto.getAlarm3();
		this.alarm11 = dto.getAlarm11();
		this.alarm22 = dto.getAlarm22();
		this.alarm33 = dto.getAlarm33();
		this.alarmSound = dto.getAlarmSound();
		this.alarmSms1 = dto.getAlarmSms1();
		this.alarmSms2 = dto.getAlarmSms2();
		this.alarmSms3 = dto.getAlarmSms3();
		this.alarmEmail1 = dto.getAlarmEmail1();
		this.alarmEmail2 = dto.getAlarmEmail2();
		this.alarmEmail3 = dto.getAlarmEmail3();
		this.alarmEmailOffline = dto.getAlarmEmailOffline();
		this.alarmSmsOffline = dto.getAlarmSmsOffline();
		this.alarmOn = dto.getAlarmOn();
		this.alarmAutoClose = dto.getAlarmAutoClose();
		this.alarm2On = dto.getAlarm2On();
		this.alarm3On = dto.getAlarm3On();
		this.alarmOffLineOn = dto.getAlarmOffLineOn();
		this.alarmSigma = dto.getAlarmSigma();
		this.alarmAction = dto.getAlarmAction();
		this.alarmAction = dto.getAlarmAction();
		this.action1 = dto.getAction1();
		this.action2 = dto.getAction2();
		this.action3 = dto.getAction3();
		this.action4 = dto.getAction4();
		this.company = dto.getCompanyDto();
		this.alarmLatencia = dto.getAlarmLatencia();
		
		if(dto.getPerfilAlarmDto1() != null)
			this.perfilAlarm1 = new PerfilAlarm(dto.getPerfilAlarmDto1());
		
		if(dto.getPerfilAlarmDto2() != null)
			this.perfilAlarm2 = new PerfilAlarm(dto.getPerfilAlarmDto2());
		
		if(dto.getPerfilAlarmDto3() != null)
			this.perfilAlarm3 = new PerfilAlarm(dto.getPerfilAlarmDto3());		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;	
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="GAS_ID", nullable = true)
	private Gas gas;		
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID", nullable = false)
	private DeviceType deviceType;
	
	@Column(name = "ALARM_1")		
	private Double alarm1;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID1", nullable = false)
	private PerfilAlarm perfilAlarm1;	
	
	@Column(name = "ALARM_2")		
	private Double alarm2;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID2", nullable = true)
	private PerfilAlarm perfilAlarm2;
	
	@Column(name = "ALARM_3")		
	private Double alarm3;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID3", nullable = true)
	private PerfilAlarm perfilAlarm3;
	
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
	
	@Column(name = "ALARMOFFLINE_ON", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmOffLineOn;
	
	@Column(name = "ALARM_SIGMA", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSigma;
	
	@Column(name = "ALARM_SOUND", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSound;
	
	@Column(name = "ALARM_EMAIL1", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmEmail1;
	
	@Column(name = "ALARM_EMAIL2", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmEmail2;
	
	@Column(name = "ALARM_EMAIL3", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmEmail3;
	
	@Column(name = "ALARM_SMS1", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSms1;
	
	@Column(name = "ALARM_SMS2", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSms2;
	
	@Column(name = "ALARM_SMS3", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSms3;
	
	@Column(name = "ALARM_EMAIL_OFFLINE", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmEmailOffline;
	
	@Column(name = "ALARM_SMS_OFFLINE", nullable = true, columnDefinition = "Boolean default false")		
	private Boolean alarmSmsOffline;
	
	@Column(name = "ALARM_ACTION", nullable = true)		
	private Boolean alarmAction;
	
	@Column(name = "ALARM_AUTO_CLOSE", nullable = true, columnDefinition = "Boolean default false")
	private Boolean alarmAutoClose;
	
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alarm", cascade = CascadeType.ALL)
	private Set<AlarmEmail> alarmEmails = new HashSet<AlarmEmail>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "alarm", cascade = CascadeType.ALL)
	private Set<AlarmMobile> alarmMobiles = new HashSet<AlarmMobile>();
	
	@Column(name = "ALARM_LATENCIA", nullable = true, columnDefinition = "Integer default 0")
	private Integer alarmLatencia;
		
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
		this.deviceType = deviceType;		
	}

	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
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

	public Boolean getAlarmOffLineOn() {
		return alarmOffLineOn;
	}

	public void setAlarmOffLineOn(Boolean alarmOffLineOn) {
		this.alarmOffLineOn = alarmOffLineOn;
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
	
	public Boolean getAlarmEmail1() {
		return alarmEmail1;
	}

	public void setAlarmEmail1(Boolean alarmEmail1) {
		this.alarmEmail1 = alarmEmail1;
	}

	public Boolean getAlarmEmail2() {
		return alarmEmail2;
	}

	public void setAlarmEmail2(Boolean alarmEmail2) {
		this.alarmEmail2 = alarmEmail2;
	}

	public Boolean getAlarmEmail3() {
		return alarmEmail3;
	}

	public void setAlarmEmail3(Boolean alarmEmail3) {
		this.alarmEmail3 = alarmEmail3;
	}

	public Boolean getAlarmSms1() {
		return alarmSms1;
	}

	public void setAlarmSms1(Boolean alarmSms1) {
		this.alarmSms1 = alarmSms1;
	}

	public Boolean getAlarmSms2() {
		return alarmSms2;
	}

	public void setAlarmSms2(Boolean alarmSms2) {
		this.alarmSms2 = alarmSms2;
	}

	public Boolean getAlarmSms3() {
		return alarmSms3;
	}

	public void setAlarmSms3(Boolean alarmSms3) {
		this.alarmSms3 = alarmSms3;
	} 

	public Boolean getAlarmEmailOffline() {
		return alarmEmailOffline;
	}

	public void setAlarmEmailOffline(Boolean alarmEmailOffline) {
		this.alarmEmailOffline = alarmEmailOffline;
	} 

	public Boolean getAlarmSmsOffline() {
		return alarmSmsOffline;
	}

	public void setAlarmSmsOffline(Boolean alarmSmsOffline) {
		this.alarmSmsOffline = alarmSmsOffline;
	}

	public final Boolean getAlarmAction() {
		return alarmAction;
	}

	public final void setAlarmAction(Boolean alarmAction) {
		this.alarmAction = alarmAction;
	} 

	public Boolean getAlarmAutoClose() {
		return alarmAutoClose;
	}

	public void setAlarmAutoClose(Boolean alarmAutoClose) {
		this.alarmAutoClose = alarmAutoClose;
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

	public PerfilAlarm getPerfilAlarm1() {
		return perfilAlarm1;
	}

	public void setPerfilAlarm1(PerfilAlarm perfilAlarm1) {
		this.perfilAlarm1 = perfilAlarm1;
	}

	public PerfilAlarm getPerfilAlarm2() {
		return perfilAlarm2;
	}

	public void setPerfilAlarm2(PerfilAlarm perfilAlarm2) {
		this.perfilAlarm2 = perfilAlarm2;
	}

	public PerfilAlarm getPerfilAlarm3() {
		return perfilAlarm3;
	}

	public void setPerfilAlarm3(PerfilAlarm perfilAlarm3) {
		this.perfilAlarm3 = perfilAlarm3;
	}
	
	public Set<AlarmEmail> getAlarmEmails() {
		return alarmEmails;
	}
	
	public Set<AlarmMobile> getAlarmMobiles() {
		return alarmMobiles;
	}

	public Integer getAlarmLatencia() {
		return alarmLatencia;
	}

	public void setAlarmLatencia(Integer alarmLatencia) {
		this.alarmLatencia = alarmLatencia;
	} 
}