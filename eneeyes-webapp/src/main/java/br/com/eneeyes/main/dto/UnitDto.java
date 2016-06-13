package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.model.enums.UnitType;


public class UnitDto {
	
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
	private Double Latitude;		
	private Double Longitude;
	
	private List<AreaDto> areas;

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
	}
	public final Date getDate() {
		return date;
	}
	public final void setDate(Date date) {
		this.date = date;
	}
	public final Double getLatitude() {
		return Latitude;
	}
	public final void setLatitude(Double latitude) {
		Latitude = latitude;
	}
	public final Double getLongitude() {
		return Longitude;
	}
	public final void setLongitude(Double longitude) {
		Longitude = longitude;
	}
	
	public final List<AreaDto> getAreas() {
		return areas;
	}
	public final void setAreas(List<AreaDto> areas) {
		this.areas = areas;
	}

	public static UnitDto fromUnitToDto(Unit unit) {
		
		UnitDto dto = new UnitDto();		
	
		dto.setUid(unit.getUid());
		dto.setName(unit.getName());		
		dto.setEmail(unit.getEmail());
		dto.setUrl(unit.getUrl());
		dto.setPhone(unit.getPhone());
		dto.setMobile(unit.getMobile());
		dto.setAddress(unit.getAddress());		
		dto.setCity(unit.getCity());
		dto.setState(unit.getState());
		dto.setZip(unit.getZip());
		dto.setUnitType(unit.getUnitType());		
		dto.setDate(unit.getDate());
		dto.setLatitude(unit.getLatitude());
		dto.setLongitude(unit.getLongitude());
		
		if(unit.getAreas() != null){
			List<AreaDto> areas = AreaDto.fromAreaToListDto(unit.getAreas());
			dto.setAreas(areas);
		}
		
		return dto;
	}		
	
	public static List<UnitDto> fromUnitToListDto(List<Unit> list) {
		
		List<UnitDto> returnList = new ArrayList<UnitDto>();
		
		for (Unit unit : list) {
			
			UnitDto dto = new UnitDto();
								
			dto.setUid(unit.getUid());
			dto.setName(unit.getName());		
			dto.setEmail(unit.getEmail());
			dto.setUrl(unit.getUrl());
			dto.setPhone(unit.getPhone());
			dto.setMobile(unit.getMobile());
			dto.setAddress(unit.getAddress());		
			dto.setCity(unit.getCity());
			dto.setState(unit.getState());
			dto.setZip(unit.getZip());
			dto.setUnitType(unit.getUnitType());		
			dto.setDate(unit.getDate());
			dto.setLatitude(unit.getLatitude());
			dto.setLongitude(unit.getLongitude());			
			
			if(unit.getAreas() != null){
				List<AreaDto> areas = AreaDto.fromAreaToListDto(unit.getAreas());
				dto.setAreas(areas);
			}
		
		}
		
		return returnList;
	}

}
