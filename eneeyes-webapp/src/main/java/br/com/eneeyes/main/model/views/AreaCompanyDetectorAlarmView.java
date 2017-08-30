package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;


@Entity
@Table(name="area_companydetector_alarm_view")
public class AreaCompanyDetectorAlarmView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AreaCompanyDetectorAlarmView() {		
	
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long uid;
	
	@Column(name = "area_id")
	private Long areaId;	
	
	@Column(name = "company_detector_id")
	private Long companyDetectorId;
	
	@Column(name = "sensor_id")
	private Long sensorId;
	
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
	
	@Column(name = "UNIT_METER_GASES")
	private UnitMeterGases unitMeterGases;
	
	@Column(name = "last_update", nullable = true)
	private Date lastUpdate;

	@Column(name = "last_value", nullable = true)
	private BigDecimal lastValue;
	
	@Column(name = "alarm_type", nullable = true)
	private AlarmType alarmType;
	
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
	
	@Column(name = "gas_name", nullable = true)
	private String gasName;

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Long getAreaId() {
		return areaId;
	}

	public final void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public final Long getCompanyDetectorId() {
		return companyDetectorId;
	}

	public final void setCompanyDetectorId(Long companyDetectorId) {
		this.companyDetectorId = companyDetectorId;
	}

	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public final void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
	}

	public final String getCompanyDetectorLocal() {
		return companyDetectorLocal;
	}

	public final void setCompanyDetectorLocal(String companyDetectorLocal) {
		this.companyDetectorLocal = companyDetectorLocal;
	}

	public final String getSensorName() {
		return sensorName;
	}

	public final void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public final Double getRangeMin() {
		return rangeMin;
	}

	public final void setRangeMin(Double rangeMin) {
		this.rangeMin = rangeMin;
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

	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}	

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLastValue() {
		return lastValue;
	}

	public void setLastValue(BigDecimal lastValue) {
		this.lastValue = lastValue;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public final String getAlarmName() {
		return alarmName;
	}

	public final void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	public final Boolean getAlarmOn() {
		return alarmOn;
	}

	public final void setAlarmOn(Boolean alarmOn) {
		this.alarmOn = alarmOn;
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

	public final String getGasName() {
		return gasName;
	}

	public final void setGasName(String gasName) {
		this.gasName = gasName;
	}

	
	
}