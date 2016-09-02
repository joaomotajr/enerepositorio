package br.com.eneeyes.main.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.PositionDto;

@Entity
@Table(name = "position")
public class Position {

    public Position() {
    	
    }
    
    public Position(PositionDto dto) {
    	this.uid = dto.getUid();
    	this.lastUpdate = dto.getLastUpdate();
    	this.lastValue = dto.getLastValue();    	
    	this.unit = new Unit(dto.getUnitDto());
    	this.area = new Area(dto.getAreaDto());
    	this.companyDevice = new CompanyDevice(dto.getCompanyDeviceDto());
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "LAST_VALUE", nullable = true)
	private Double lastValue;
    	
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIT_ID", nullable=false)
    private Unit unit;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AREA_ID", nullable=false)
    private Area area;
    		
	@ManyToOne(cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_DEVICE_ID", nullable = false)
	private CompanyDevice companyDevice;
		
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public CompanyDevice getCompanyDevice() {
		return companyDevice;
	}

	public void setCompanyDevice(CompanyDevice companyDevice) {
		this.companyDevice = companyDevice;
	}


}
