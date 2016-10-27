package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.model.enums.UnitType;


public class UnitDto implements Comparable<UnitDto> {
	
	private Long uid;
	private String name;	
	private String email;	
	private String url;	
	private String phone;	
	private String mobile;	
	private String address;	
	private String city;	
	private String state;
	private String zip;
	private UnitType unitType;
	private Date date;
	private Double latitude;		
	private Double longitude;	
	private CompanyDto companyDto;	
	private List<AreaDto> areasDto = new ArrayList<AreaDto>();
	
	public UnitDto() {}
	
	public UnitDto(Unit unit) {
		
		this.uid = unit.getUid();
		this.name = unit.getName();		
		this.email = unit.getEmail();
		this.url = unit.getUrl();
		this.phone = unit.getPhone();
		this.mobile = unit.getMobile();
		this.address = unit.getAddress();		
		this.city = unit.getCity();
		this.state = unit.getState();
		this.zip = unit.getZip(); 
		this.unitType = unit.getUnitType();		
		this.date = unit.getDate();
		this.latitude = unit.getLatitude();
		this.longitude = unit.getLongitude();
		
		if(unit.getAreas() != null) {		
			this.areasDto = setAreasDto(unit.getAreas());		
		}
	}
	
	private List<AreaDto> setAreasDto(Set<Area> areas) {
		List<AreaDto> lista = new ArrayList<AreaDto>();
		
		if(areas != null && !areas.isEmpty()) {
		
			Iterator<Area> itr = areas.iterator();
			
			while (itr.hasNext()) {
				AreaDto dto = new AreaDto(itr.next());
				lista.add(dto);
			}
		}
		Collections.sort(lista);
		return lista;
	}

	public final List<AreaDto> getAreasDto() {
		return areasDto;
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
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final String getUrl() {
		return url;
	}
	public final void setUrl(String url) {
		this.url = url;
	}
	public final String getPhone() {
		return phone;
	}
	public final void setPhone(String phone) {
		this.phone = phone;
	}
	public final String getMobile() {
		return mobile;
	}
	public final void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public final String getAddress() {
		return address;
	}
	public final void setAddress(String address) {
		this.address = address;
	}
	public final String getCity() {
		return city;
	}
	public final void setCity(String city) {
		this.city = city;
	}
	public final String getState() {
		return state;
	}
	public final void setState(String state) {
		this.state = state;
	}
	public final String getZip() {
		return zip;
	}
	public final void setZip(String zip) {
		this.zip = zip;
	}
	public final UnitType getUnitType() {
		return unitType;
	}
	public final void setUnitType(UnitType unitType) {
		this.unitType = unitType;
		
		if (unitType == null ) {			
			this.unitType = UnitType.UNICA;
		}	
		else { 
			this.unitType = unitType;
		}		
	}
	public final Date getDate() {
		return date;
	}
	public final void setDate(Date date) {
		this.date = date;
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
	public CompanyDto getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}
	@Override
	public int compareTo(UnitDto unitDto) {
		return unitDto.getUid().compareTo(this.uid);		
	}
}
