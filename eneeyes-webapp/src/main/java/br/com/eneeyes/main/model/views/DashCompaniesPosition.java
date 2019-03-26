package br.com.eneeyes.main.model.views;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Subselect;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.model.state.UnitMeter;

@Entity
@Subselect("select * from dash_companies_position2")
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
			
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="DEVICE_TYPE_ID", nullable = false)
	private DeviceType deviceType;
	
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
		
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name="UNIT_METER_ID", nullable = false)
	private UnitMeter unitMeter;	

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

	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}	
}