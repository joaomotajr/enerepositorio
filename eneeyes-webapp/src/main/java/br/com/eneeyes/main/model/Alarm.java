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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.AlarmDto;
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
		this.unitMeterGases = dto.getUnitMeterGases();
		
		if(dto.getGasDto() != null)
			this.gas = new Gas(dto.getGasDto());		
				
		this.alarm1 = dto.getAlarm1();
		this.alarm2 = dto.getAlarm2();
		this.alarm3 = dto.getAlarm3();
		this.alarmOff = dto.getAlarmOff();
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
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="GAS_ID", nullable = false)
	private Gas gas;		
	
	@Column(name = "ALARM_1")		
	private Double alarm1;
	
	@Column(name = "ALARM_2")		
	private Double alarm2;
	
	@Column(name = "ALARM_3")		
	private Double alarm3;
	
	@Column(name = "ALARM_OFF", nullable = true)		
	private Boolean alarmOff;
		
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

	public final void setAlarm3(Double alarm3) {
		this.alarm3 = alarm3;
	}

	public final Boolean getAlarmOff() {
		return alarmOff;
	}

	public final void setAlarmOff(Boolean alarmOff) {
		this.alarmOff = alarmOff;
	}
	
	public CompanyView getCompany() {
		return company;
	}

	public void setCompany(CompanyView company) {
		this.company = company;
	}
}
