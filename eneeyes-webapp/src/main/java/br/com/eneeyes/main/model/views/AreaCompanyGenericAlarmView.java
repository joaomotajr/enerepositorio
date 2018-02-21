package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;

@Entity
@Subselect("select * from area_companygeneric_alarm_view")
public class AreaCompanyGenericAlarmView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AreaCompanyGenericAlarmView() {		
	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;
	
	@Column(name = "area_id")
	private Long areaId;	
	
	@Column(name = "company_device_id")
	private Long companyDeviceId;
	
	@Column(name = "company_generic_id")
	private Long companyGenericId;
	
	@Column(name = "company_generic_name")
	private String companyDetectorName;
	
	@Column(name = "company_generic_local", nullable = true)
	private String companyDetectorLocal;
	
	@Column(name = "generic_name")
	private String sensorName;
	
	@Column(name = "range_min")
	private Double rangeMin;
	
	@Column(name = "range_max")
	private Double rangeMax;
	
	@Column(name = "UNIT_METER_GASES", columnDefinition = "int default 0")
	private UnitMeterGases unitMeterGases;

	@Enumerated(EnumType.ORDINAL) 
	private UnitMeterGases UnitMeterGases() { 
	    return unitMeterGases; 
	}
	
	@Column(name = "last_value", nullable = true)
	private BigDecimal lastValue;
	
	@Column(name = "last_update", nullable = true)
	private Date lastUpdate;
	
	@Column(name = "alarm_type", nullable = true)
	private AlarmType alarmType;
	
	@Column(name = "alarm_id")
	private Long alarmId;
	
	@Column(name = "alarm_name", nullable = true)
	private String alarmName;
	
	@Column(name = "alarm_on", nullable = true)	
	private Boolean alarmOn;
	
	@Column(name = "alarm_1", nullable = true)	
	private Double alarm1;
	
	@Column(name = "alarm_2", nullable = true)	
	private Double alarm2;		
	
	@Column(name = "alarm_3", nullable = true)	
	private Double alarm3;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUid() {
		return uid;
	}

	public Long getAreaId() {
		return areaId;
	}

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}

	public Long getCompanyGenericId() {
		return companyGenericId;
	}

	public String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public String getCompanyDetectorLocal() {
		return companyDetectorLocal;
	}

	public String getSensorName() {
		return sensorName;
	}

	public Double getRangeMin() {
		return rangeMin;
	}

	public Double getRangeMax() {
		return rangeMax;
	}

	public UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}

	public BigDecimal getLastValue() {
		return lastValue;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public Long getAlarmId() {
		return alarmId;
	}

	public String getAlarmName() {
		return alarmName;
	}

	public Boolean getAlarmOn() {
		return alarmOn;
	}

	public Double getAlarm1() {
		return alarm1;
	}

	public Double getAlarm2() {
		return alarm2;
	}

	public Double getAlarm3() {
		return alarm3;
	}	
	
}