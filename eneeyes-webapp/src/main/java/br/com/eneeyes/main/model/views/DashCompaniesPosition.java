package br.com.eneeyes.main.model.views;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.UnitMeterGases;


@Entity
@Table(name="dash_companies_position")
public class DashCompaniesPosition {
	
	public DashCompaniesPosition() {	
	
	}	
	
	@Id	
	@Column(name = "uid")
	private Long uid;
	
	@OneToMany(
			mappedBy="dashCompaniesPosition", 
			fetch = FetchType.EAGER, 
			targetEntity = PositionHistoricView.class )
	private Set<PositionHistoricView> positionHistoricViews = new HashSet<PositionHistoricView>();
		
//	@OneToMany(
//			mappedBy="dashCompaniesPosition", 
//			fetch = FetchType.EAGER, 
//			targetEntity = PositionHistoricView.class )
//	private Set<ProcDashboard> procDashboards = new HashSet<ProcDashboard>();		
//
//	public final Set<ProcDashboard> getProcDashboards() {
//		return procDashboards;
//	}
//
//	public final void setProcDashboards(Set<ProcDashboard> procDashboards) {
//		this.procDashboards = procDashboards;
//	}

	public final Set<PositionHistoricView> getPositionHistoricViews() {
		return positionHistoricViews;
	}

	public final void setPositionHistoricViews(Set<PositionHistoricView> positionHistoricViews) {
		this.positionHistoricViews = positionHistoricViews;
	}

	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "UNIT_NAME")
	private String unitName;
	
	@Column(name = "AREA_NAME")
	private String areaName;
	
	@Column(name = "COMPANY_DETECTOR_NAME")
	private String companyDetectorName;
	
	@Column(name = "SENSOR_NAME")
	private String sensorName;
	
	@Column(name = "SENSOR_ID")
	private Long sensorId;
	
	@Column(name = "POSITION_ID")
	private Long positionId;
	
	@Column(name = "GAS_NAME")
	private String gasName;	
		
	@Column(name = "LAST_VALUE", nullable = true)
	private Double lastValue;
	
	@Column(name = "ALARM_TYPE")
	private AlarmType alarmType;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;		
	
	@Column(name = "UNIT_METER_GASES")
	private UnitMeterGases unitMeterGases;
	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}

	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}

	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public final Long getCompanyId() {
		return companyId;
	}

	public final void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public final String getCompanyName() {
		return companyName;
	}

	public final void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public final String getUnitName() {
		return unitName;
	}

	public final void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public final String getAreaName() {
		return areaName;
	}

	public final void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public final String getCompanyDetectorName() {
		return companyDetectorName;
	}

	public final void setCompanyDetectorName(String companyDetectorName) {
		this.companyDetectorName = companyDetectorName;
	}

	public final String getSensorName() {
		return sensorName;
	}

	public final void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public final Long getSensorId() {
		return sensorId;
	}

	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public final Long getPositionId() {
		return positionId;
	}

	public final void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public final String getGasName() {
		return gasName;
	}

	public final void setGasName(String gasName) {
		this.gasName = gasName;
	}

	public final Double getLastValue() {
		return lastValue;
	}

	public final void setLastValue(Double lastValue) {
		this.lastValue = lastValue;
	}

	public final AlarmType getAlarmType() {
		return alarmType;
	}

	public final void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}