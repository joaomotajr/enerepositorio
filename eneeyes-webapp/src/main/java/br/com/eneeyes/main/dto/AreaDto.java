package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.CompanyDevice;


public class AreaDto implements Comparable<AreaDto> {

	private Long uid;
	private String name;
	private String description;		
	private String local;		
	private Double latitude;		
	private Double longitude;
	private Boolean classified;
	private Date date;
	private String image;
    private UnitDto unitDto;
    private List<CompanyDeviceDto> companyDevicesDto = new ArrayList<CompanyDeviceDto>();
    
    public AreaDto() {
		super();
	}
	
	public AreaDto(Area area) {
		super();
    	this.uid = area.getUid();		
    	this.name = area.getName();
    	this.description = area.getDescription();
    	this.local = area.getLocal();
    	this.latitude = area.getLatitude();
    	this.longitude = area.getLongitude();
    	this.classified= area.getClassified();		
    	this.date = area.getDate(); 
    	
    	if (area.getImage() != null) {
			byte[] image = area.getImage();
			this.image = new String(image);
		}
    	
    	if(area.getCompanyDevices() != null) {		
			this.companyDevicesDto = setCompanyDevicesDto(area.getCompanyDevices());		
		}
	}
	
	private List<CompanyDeviceDto> setCompanyDevicesDto(Set<CompanyDevice> companyDevices) {
		List<CompanyDeviceDto> lista = new ArrayList<CompanyDeviceDto>();
		
		if(companyDevices != null && !companyDevices.isEmpty()) {
		
			Iterator<CompanyDevice> itr = companyDevices.iterator();
			
			while (itr.hasNext()) {
				CompanyDeviceDto dto = new CompanyDeviceDto(itr.next());
				lista.add(dto);
			}
		}		
		
		Collections.sort(lista);		
		return lista;
	}
	
	public final List<CompanyDeviceDto> getCompanyDevicesDto() {
		return companyDevicesDto;
	}
    	
	public final Long getUid() {
		return uid;
	}
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}	
	public final String getLocal() {
		return local;
	}
	public final void setLocal(String local) {
		this.local = local;
	}
	public final Double getLatitude() {
		return latitude;
	}
	public final void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public final Double getLongitude() {
		return longitude;
	}
	public final void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getClassified() {
		return classified;
	}
	public void setClassified(Boolean classified) {
		this.classified = classified;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public final String getImage() {
		return image;
	}
	public final void setImage(String image) {
		this.image = image;
	}
	public final UnitDto getUnitDto() {
		return unitDto;
	}
	public final void setUnitDto(UnitDto unitDto) {
		this.unitDto = unitDto;
	}	
	@Override
	public int compareTo(AreaDto areaDto) {
		return areaDto.getUid().compareTo(this.uid);		
	}	
}
