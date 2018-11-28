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
import br.com.eneeyes.main.model.enums.DeviceType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;

@Entity
@Subselect("select * from area_companydevice_alarm_view")
public class AreaCompanyDeviceAlarmView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AreaCompanyDeviceAlarmView() {		
	
	}	
	
	@Id
	@Column(name = "uid")
	private Long uid;
	
	@Column(name = "area_id")
	private Long areaId;	
	
	@Column(name = "company_device_id")
	private Long companyDeviceId;
	
	@Column(name = "DEVICE_TYPE", columnDefinition = "int default 0")
	private DeviceType deviceType;
	
	@Enumerated(EnumType.ORDINAL) 
	private DeviceType DeviceType() { 
	    return deviceType; 
	}
	
	@Column(name = "company_detector_id")
	private Long companyDetectorId;
	
	@Column(name = "company_detector_name")
	private String companyDetectorName;
	
	@Column(name = "company_detector_local", nullable = true)
	private String companyDetectorLocal;
	
	@Column(name = "sensor_name")
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
	
	@Column(name = "alarm_11", nullable = true)	
	private Double alarm11;
	
	@Column(name = "alarm_2", nullable = true)	
	private Double alarm2;		
	
	@Column(name = "alarm_3", nullable = true)	
	private Double alarm3;
	
	private String artefact;
	
	@Column(name = "gas_name")
	private String gasName;

	public final Long getUid() {
		return uid;
	}

	public final Long getAreaId() {
		return areaId;
	}

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public Long getCompanyDeviceId() {
		return companyDeviceId;
	}
	
	public DeviceType getdeviceType() {
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

	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public final String getCompanyDetectorLocal() {
		return companyDetectorLocal;
	}

	public final String getSensorName() {
		return sensorName;
	}

	public final Double getRangeMin() {
		return rangeMin;
	}

	public final Double getRangeMax() {
		return rangeMax;
	}

	public final void setRangeMax(Double rangeMax) {
		this.rangeMax = rangeMax;
	}

	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public BigDecimal getLastValue() {
		return lastValue;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public final Long getAlarmId() {
		return alarmId;
	}

	public final String getAlarmName() {
		return alarmName;
	}

	public final Boolean getAlarmOn() {
		return alarmOn;
	}

	public final Double getAlarm1() {
		return alarm1;
	}
	
	public final Double getAlarm11() {
		return alarm11;
	}

	public final Double getAlarm2() {
		return alarm2;
	}

	public final Double getAlarm3() {
		return alarm3;
	}

	public String getArtefact() {
		return artefact;
	}

	public String getGasName() {
		return gasName;
	}
		
}