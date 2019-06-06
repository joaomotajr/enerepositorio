package br.com.eneeyes.main.model.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.model.state.PerfilAlarm;
import br.com.eneeyes.main.model.state.UnitMeter;

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
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID", nullable = false)
	private DeviceType deviceType;
	
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
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;
			
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
	
	@Column(name = "gas_name")
	private String gasName;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID1", nullable = false)
	private PerfilAlarm perfilAlarm1;	
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID2", nullable = false)
	private PerfilAlarm perfilAlarm2;
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="PERFIL_ALARM_ID3", nullable = false)
	private PerfilAlarm perfilAlarm3;

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

	public final UnitMeter getUnitMeter() {
		return unitMeter;
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

	public String getGasName() {
		return gasName;
	}

	public PerfilAlarm getPerfilAlarm1() {
		return perfilAlarm1;
	}

	public PerfilAlarm getPerfilAlarm2() {
		return perfilAlarm2;
	}

	public PerfilAlarm getPerfilAlarm3() {
		return perfilAlarm3;
	}		
}