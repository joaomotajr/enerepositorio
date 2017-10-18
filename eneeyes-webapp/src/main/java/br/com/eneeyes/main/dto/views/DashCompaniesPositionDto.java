package br.com.eneeyes.main.dto.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.views.DashCompaniesPosition;
import br.com.eneeyes.main.model.views.PositionHistoricView;


public class DashCompaniesPositionDto {
	
	public DashCompaniesPositionDto() {	
	
	}	
	
	public DashCompaniesPositionDto(DashCompaniesPosition dashCompaniesPosition) {
		
		this.uid = dashCompaniesPosition.getUid();
		this.companyId = dashCompaniesPosition.getCompanyId();
		this.companyName = dashCompaniesPosition.getCompanyName();
		this.areaName = dashCompaniesPosition.getAreaName();
		this.companyDetectorName = dashCompaniesPosition.getCompanyDetectorName();
		this.sensorName = dashCompaniesPosition.getSensorName();		
		this.sensorId = dashCompaniesPosition.getSensorId();
		this.positionId = dashCompaniesPosition.getPositionId();
		this.gasName = dashCompaniesPosition.getGasName();
		this.lastValue = dashCompaniesPosition.getLastValue();
		this.alarmType = dashCompaniesPosition.getAlarmType();
		this.lastUpdate = dashCompaniesPosition.getLastUpdate();
		
		if(dashCompaniesPosition.getPositionHistoricView() != null) {
			
			this.positionHistoricViewDto = setPositionHistoricViewsDto(dashCompaniesPosition.getPositionHistoricView());			
		}
		
		Integer count = 0;
		for (PositionHistoricViewDto item   : this.positionHistoricViewDto) {					
			this.arrayValues += item.getValue().toString() != null ? item.getValue().toString() + ", " : "";
			count++;
			
			if (count >= 15)
				break;	
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
	private String companyDetectorName;	
	private String sensorName;	
	private Long sensorId;	
	private Long positionId;	
	private String gasName;	
	private Double lastValue;	
	private AlarmType alarmType;	
	private Date lastUpdate;
	private String arrayValues = "";
		
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