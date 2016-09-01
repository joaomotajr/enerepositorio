package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.model.Position;

public class PositionDto {
	
	private Long uid;
	private Date lastUpdate;
	private Double lastValue;	
	private UnitDto unitDto;    
    private AreaDto areaDto;
    private CompanyDeviceDto companyDeviceDto;	
		
	public PositionDto() {
		
	}
	
	public PositionDto(Position position) {
		
		this.uid = position.getUid();
		this.lastUpdate = position.getLastUpdate();
    	this.lastValue = position.getLastValue();    	
    	this.unitDto = new UnitDto(position.getUnit());
    	this.areaDto = new AreaDto(position.getArea());
    	this.companyDeviceDto = new CompanyDeviceDto(position.getCompanyDevice());
    
	}
	
	public final Long getUid() {
		return uid;
	}
	
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Double getLastValue() {
		return lastValue;
	}

	public void setLastValue(Double lastValue) {
		this.lastValue = lastValue;
	}

	public UnitDto getUnitDto() {
		return unitDto;
	}

	public void setUnitDto(UnitDto unitDto) {
		this.unitDto = unitDto;
	}

	public AreaDto getAreaDto() {
		return areaDto;
	}

	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}
	
	public CompanyDeviceDto getCompanyDeviceDto() {
		return companyDeviceDto;
	}

	public void setCompanyDeviceDto(CompanyDeviceDto companyDeviceDto) {
		this.companyDeviceDto = companyDeviceDto;
	}




}
