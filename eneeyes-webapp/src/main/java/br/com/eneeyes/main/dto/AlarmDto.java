package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.dto.register.GasDto;
import br.com.eneeyes.main.dto.state.PerfilAlarmDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.AlarmMobile;
import br.com.eneeyes.main.model.AlarmEmail;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.model.state.UnitMeter;
import br.com.eneeyes.main.model.views.CompanyView;

public class AlarmDto {
	
	private Long uid;	
	private String name;	
	private DeviceType deviceType;
	private UnitMeter unitMeter;
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
	private Boolean alarmOffLineOn;
	private Boolean alarmSigma;
	private Boolean alarmSound;
	private Boolean alarmEmail;
	private Boolean alarmSms;
	private Boolean alarmAction;
	private String action1;
	private String action2;
	private String action3;
	private String action4;	
	private CompanyView companyDto;
	private PerfilAlarmDto perfilAlarmDto1;	
	private PerfilAlarmDto perfilAlarmDto2;	
	private PerfilAlarmDto perfilAlarmDto3;
	private List<AlarmEmailDto> alarmEmailsDto = new ArrayList<AlarmEmailDto>();
	private List<AlarmMobileDto> alarmMobilesDto = new ArrayList<AlarmMobileDto>();

	public AlarmDto() {
		
	}
	
	public AlarmDto(Alarm alarm) {
		
		this.uid = alarm.getUid();	
		this.name = alarm.getName();
		this.deviceType = alarm.getDeviceType();
		this.unitMeter = alarm.getUnitMeter();
		
		if(alarm.getGas() != null)
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
		this.alarmOffLineOn = alarm.getAlarmOffLineOn();
		this.alarmSigma = alarm.getAlarmSigma();
		this.alarmSound = alarm.getAlarmSound();
		this.alarmEmail = alarm.getAlarmEmail();
		this.alarmSms = alarm.getAlarmSms();		
		this.alarmAction = alarm.getAlarmAction();
		this.action1 = alarm.getAction1();
		this.action2 = alarm.getAction2();
		this.action3 = alarm.getAction3();
		this.action4 = alarm.getAction4();
		this.companyDto = alarm.getCompany();
		
		if(alarm.getPerfilAlarm1() != null)
			this.perfilAlarmDto1 = new PerfilAlarmDto(alarm.getPerfilAlarm1());
		
		if(alarm.getPerfilAlarm2() != null)
			this.perfilAlarmDto2 = new PerfilAlarmDto(alarm.getPerfilAlarm2());
		
		if(alarm.getPerfilAlarm3() != null)
			this.perfilAlarmDto3 = new PerfilAlarmDto(alarm.getPerfilAlarm3());
		
		if(alarm.getAlarmEmails() != null)
			this.alarmEmailsDto = parseAlarmEmailsDto(alarm.getAlarmEmails());		
		
		if(alarm.getAlarmMobiles() != null)
			this.alarmMobilesDto = parseAlarmMobilesDto(alarm.getAlarmMobiles());		
	}
	
	private List<AlarmEmailDto> parseAlarmEmailsDto(Set<AlarmEmail> alarmEmails) {
		List<AlarmEmailDto> lista = new ArrayList<AlarmEmailDto>();		
		if(alarmEmails != null && !alarmEmails.isEmpty()) {
			Iterator<AlarmEmail> itr = alarmEmails.iterator();			
			while (itr.hasNext()) {
				AlarmEmailDto dto = new AlarmEmailDto(itr.next());
				lista.add(dto);
			}
		}
		Collections.sort(lista);
		return lista;
	}
	
	public List<AlarmEmailDto> getAlarmEmailsDto() {
		return alarmEmailsDto;
	}
	
	private List<AlarmMobileDto> parseAlarmMobilesDto(Set<AlarmMobile> alarmMobiles) {
		List<AlarmMobileDto> lista = new ArrayList<AlarmMobileDto>();		
		if(alarmMobiles != null && !alarmMobiles.isEmpty()) {
			Iterator<AlarmMobile> itr = alarmMobiles.iterator();			
			while (itr.hasNext()) {
				AlarmMobileDto dto = new AlarmMobileDto(itr.next());
				lista.add(dto);
			}
		}
		Collections.sort(lista);
		return lista;
	}
	
	public List<AlarmMobileDto> getAlarmMobilesDto() {
		return alarmMobilesDto;
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
	
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	
	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
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
	
	public Boolean getAlarmSms() {
		return alarmSms;
	}

	public void setAlarmSms(Boolean alarmSms) {
		this.alarmSms = alarmSms;
	}	
	
	public Boolean getAlarmEmail() {
		return alarmEmail;
	}

	public void setAlarmEmail(Boolean alarmEmail) {
		this.alarmEmail = alarmEmail;
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

	public PerfilAlarmDto getPerfilAlarmDto1() {
		return perfilAlarmDto1;
	}

	public void setPerfilAlarmDto1(PerfilAlarmDto perfilAlarmDto1) {
		this.perfilAlarmDto1 = perfilAlarmDto1;
	}

	public PerfilAlarmDto getPerfilAlarmDto2() {
		return perfilAlarmDto2;
	}

	public void setPerfilAlarmDto2(PerfilAlarmDto perfilAlarmDto2) {
		this.perfilAlarmDto2 = perfilAlarmDto2;
	}

	public PerfilAlarmDto getPerfilAlarmDto3() {
		return perfilAlarmDto3;
	}

	public void setPerfilAlarmDto3(PerfilAlarmDto perfilAlarmDto3) {
		this.perfilAlarmDto3 = perfilAlarmDto3;
	}			
}
