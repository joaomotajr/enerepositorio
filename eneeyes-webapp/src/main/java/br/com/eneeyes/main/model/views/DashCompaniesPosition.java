package br.com.eneeyes.main.model.views;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;

@Entity
@Subselect("select * from dash_companies_position")
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
	private Set<PositionHistoricView> positionHistoricView = new HashSet<PositionHistoricView>();

	public final Set<PositionHistoricView> getPositionHistoricView() {
		return positionHistoricView;
	}

	public final void setGroupPositionHistoricViews(Set<PositionHistoricView> positionHistoricView) {
		this.positionHistoricView = positionHistoricView;
	}

	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "UNIT_NAME")
	private String unitName;
	
	@Column(name = "AREA_NAME")
	private String areaName;
	
	@Column(name = "COMPANY_DEVICE_NAME")
	private String companyDeviceName;
	
	@Column(name="DEVICE_TYPE")
	private String deviceType;
	
	@Column(name="DEVICE_SYMBOL")
	private String deviceSymbol;
	
	@Column(name="DEVICE_DESCRIPTION")
	private String deviceDescription;
	
	@Column(name = "POSITION_ID")
	private Long positionId;	
			
	@Column(name = "LAST_VALUE", nullable = true)
	private Double lastValue;
	
	@Column(name = "ALARM_TYPE")
	private AlarmType alarmType;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;		
	
	@Column(name = "GAS_NAME")
	private String gasName;		
	
	@Column(name="UNIT_METER_DESCRIPTION")
	private String unitMeterDescription;	
	
	@Column(name="UNIT_METER_SYMBOL")
	private String unitMeterSymbol;

	public Long getUid() {
		return uid;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getUnitName() {
		return unitName;
	}

	public String getAreaName() {
		return areaName;
	}
	
	public String getCompanyDeviceName() {
		return companyDeviceName;
	}

	public Long getPositionId() {
		return positionId;
	}

	public Double getLastValue() {
		return lastValue;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public String getGasName() {
		return gasName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getUnitMeterDescription() {
		return unitMeterDescription;
	}

	public String getUnitMeterSymbol() {
		return unitMeterSymbol;
	}

	public String getDeviceSymbol() {
		return deviceSymbol;
	}

	public String getDeviceDescription() {
		return deviceDescription;
	}	
}