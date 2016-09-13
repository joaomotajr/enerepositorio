package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.dto.register.GasDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.enums.UnitMeterGases;

public class AlarmDto {
	
	private Long uid;	
	private String name;	
	private UnitMeterGases unitMeterGases;
	private GasDto gasDto;			
	private Double alarm1;
	private Double alarm2;		
	private Double alarm3;		
	private Boolean alarmOff;

	public AlarmDto() {
		
	}
	
	public AlarmDto(Alarm alarm) {
		
		this.uid = alarm.getUid();	
		this.name = alarm.getName();
		this.unitMeterGases = alarm.getUnitMeterGases();		
		this.gasDto = new GasDto(alarm.getGas());		
		this.alarm1 = alarm.getAlarm1();
		this.alarm2 = alarm.getAlarm2();
		this.alarm3 = alarm.getAlarm3();
		this.alarmOff = alarm.getAlarmOff();
		
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

	public final Boolean getAlarmOff() {
		return alarmOff;
	}

	public final void setAlarmOff(Boolean alarmOff) {
		this.alarmOff = alarmOff;
	}
}
