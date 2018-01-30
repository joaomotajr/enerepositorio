package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.dto.register.GasDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.enums.DeviceType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;
import br.com.eneeyes.main.model.views.CompanyView;

public class AlarmDto {
	
	private Long uid;	
	private String name;	
	private DeviceType deviceType;
	private UnitMeterGases unitMeterGases;
	private GasDto gasDto;			
	private Double alarm1;
	private Double alarm2;		
	private Double alarm3;		
	private Double alarm11;
	private Double alarm22;		
	private Double alarm33;
	private Boolean alarmOn;
	private Boolean alarm2On;
	private Boolean alarm3On;
	private Boolean alarmSigma;
	private Boolean alarmSound;
	private Boolean alarmEmail;	
	private String email;
	private String email1;	
	private Boolean alarmSms;
	private String celular;
	private String celular1;
	private Boolean alarmAction;
	private String action1;
	private String action2;
	private String action3;
	private String action4;
	
	private CompanyView companyDto;

	public AlarmDto() {
		
	}
	
	public AlarmDto(Alarm alarm) {
		
		this.uid = alarm.getUid();	
		this.name = alarm.getName();
		this.deviceType = alarm.getDeviceType();
		this.unitMeterGases = alarm.getUnitMeterGases();		
		this.gasDto = new GasDto(alarm.getGas());		
		this.alarm1 = alarm.getAlarm1();
		this.alarm2 = alarm.getAlarm2();
		this.alarm3 = alarm.getAlarm3();
		this.alarm11 = alarm.getAlarm11();
		this.alarm22 = alarm.getAlarm22();
		this.alarm33 = alarm.getAlarm33();
		this.alarmOn = alarm.getAlarmOn();
		this.alarm2On = alarm.getAlarm2On();
		this.alarm3On = alarm.getAlarm3On();
		this.alarmSigma = alarm.getAlarmSigma();
		this.alarmSound = alarm.getAlarmSound();
		this.alarmEmail = alarm.getAlarmEmail();
		this.email = alarm.getEmail();
		this.email1 = alarm.getEmail1();
		this.alarmSms = alarm.getAlarmSms();
		this.celular = alarm.getCelular();
		this.celular1 = alarm.getCelular1();
		this.alarmAction = alarm.getAlarmAction();
		this.action1 = alarm.getAction1();
		this.action2 = alarm.getAction2();
		this.action3 = alarm.getAction3();
		this.action4 = alarm.getAction4();
		this.companyDto = alarm.getCompany();		
	}
	
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
	
	public final DeviceType getDeviceType() {
		return deviceType;
	}
	public final void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
		
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

	public final GasDto getGasDto() {
		return gasDto;
	}

	public final void setGasDto(GasDto gasDto) {
		this.gasDto = gasDto;
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

	public final void setAlarm3(Double alarm3) {
		this.alarm3 = alarm3;
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
	
	public final Boolean getAlarmSound() {
		return alarmSound;
	}

	public final void setAlarmSound(Boolean alarmSound) {
		this.alarmSound = alarmSound;
	}
	
	public CompanyView getCompanyDto() {
		return companyDto;
	}
	
	public void setCompanyDto(CompanyView companyDto) {
		this.companyDto = companyDto;
	}
	
	public final Boolean getAlarmEmail() {
		return alarmEmail;
	}

	public final void setAlarmEmail(Boolean alarmEmail) {
		this.alarmEmail = alarmEmail;
	}
	
	public final String getEmail1() {
		return email1;
	}

	public final void setEmail1(String email1) {
		this.email1 = email1;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
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
