package br.com.eneeyes.main.dto.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.model.state.UnitMeter;
import br.com.eneeyes.main.model.views.DashCompaniesPosition;
import br.com.eneeyes.main.model.views.PositionHistoricView;


public class DashCompaniesPositionDto {
	
	public DashCompaniesPositionDto() {	
	
	}	
	
	public DashCompaniesPositionDto(DashCompaniesPosition e) {
		
		this.uid = e.getUid();
		this.companyId = e.getCompanyId();
		this.companyName = e.getCompanyName();
		this.areaName = e.getAreaName();
		this.companyDeviceName = e.getCompanyDeviceName();	
		this.deviceType = e.getDeviceType();
		this.positionId = e.getPositionId();		
		this.lastValue = e.getLastValue();
		this.alarmType = e.getAlarmType();
		this.lastUpdate = e.getLastUpdate();		
		this.gasName = e.getGasName();
		this.unitMeter = e.getUnitMeter();		
		
		if(e.getPositionHistoricView() != null) {
			
			this.positionHistoricViewDto = setPositionHistoricViewsDto(e.getPositionHistoricView());			
		}
		
		Integer count = 0;
		for (PositionHistoricViewDto item   : this.positionHistoricViewDto) {
			if(item.getValue() != null) {
				this.arrayValues += item.getValue().toString() != null ? item.getValue().toString() + ", " : "";
				count++;
			
				if (count >= 15)
					break;	
			}
		}
	}	
	
	private List<PositionHistoricViewDto> positionHistoricViewDto = new ArrayList<PositionHistoricViewDto>();

	public final List<PositionHistoricViewDto> getPositionHistoricViewDto() {
		return positionHistoricViewDto;
	}

	public List<PositionHistoricViewDto> setPositionHistoricViewsDto(Set<PositionHistoricView> positionHistoricView) {
					
		List<PositionHistoricViewDto> lista = new ArrayList<PositionHistoricViewDto>();
				
		if(positionHistoricView != null && !positionHistoricView.isEmpty()) {
					
			Iterator<PositionHistoricView> itr = positionHistoricView.iterator();
									
			while (itr.hasNext()) {				
				PositionHistoricViewDto dto = new PositionHistoricViewDto(itr.next());
				lista.add(dto);				
			}
			
			Collections.sort(lista);
		}		
				
		return lista;	
	}

	private Long uid;	
	private Long companyId;	
	private String companyName;	
	private String unitName;	
	private String areaName;	
	private String companyDeviceName;
	private DeviceType deviceType;
	private Long positionId;	
	private Double lastValue;	
	private AlarmType alarmType;	
	private Date lastUpdate;
	private String arrayValues = "";
	private String gasName;
	private UnitMeter unitMeter;
		
	public final String getArrayValues() {
		return arrayValues;
	}

	public final void setArrayValues(String arrayValues) {
		this.arrayValues = arrayValues;
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
	
	public String getCompanyDeviceName() {
		return companyDeviceName;
	}

	public void setCompanyDeviceName(String companyDeviceName) {
		this.companyDeviceName = companyDeviceName;
	}

	public final Long getPositionId() {
		return positionId;
	}

	public final void setPositionId(Long positionId) {
		this.positionId = positionId;
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

	public UnitMeter getUnitMeter() {
		return unitMeter;
	}

	public void setUnitMeter(UnitMeter unitMeter) {
		this.unitMeter = unitMeter;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getGasName() {
		return gasName;
	}

	public void setGasName(String gasName) {
		this.gasName = gasName;
	}	
	
	
	
}