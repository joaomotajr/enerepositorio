package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.model.UnitType;


public class CompanyDto {
	
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
	private CompanyDto parent;

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
	public final CompanyDto getParent() {
		return parent;
	}
	public final void setParent(CompanyDto parent) {
		this.parent = parent;
	}
	
	public static CompanyDto fromCompanyToDto(Company company) {
		
		CompanyDto dto = new CompanyDto();
		
		dto.setUid(company.getUid());
		
		dto.setUid(company.getUid());
		dto.setName(company.getName());		
		dto.setEmail(company.getEmail());
		dto.setUrl(company.getUrl());
		dto.setPhone(company.getPhone());
		dto.setMobile(company.getMobile());
		dto.setAddress(company.getAddress());		
		dto.setCity(company.getCity());
		dto.setState(company.getState());
		dto.setZip(company.getZip());
		dto.setUnitType(company.getUnitType());		
		dto.setDate(company.getDate());
		dto.setLatitude(company.getLatitude());
		dto.setLongitude(company.getLongitude());	
		
				
		if(company.getParent() != null){
			CompanyDto parent = fromCompanyToDto(company.getParent());
			dto.setParent(parent);
		}		
		
		return dto;
	}		
	
	public static List<CompanyDto> fromCompanyToDto(List<Company> list) {
		
		List<CompanyDto> returnList = new ArrayList<CompanyDto>();
		
		for (Company company : list) {
			
			CompanyDto dto = new CompanyDto();
			
			dto.setUid(company.getUid());		
			dto.setUid(company.getUid());
			dto.setName(company.getName());		
			dto.setEmail(company.getEmail());
			dto.setUrl(company.getUrl());
			dto.setPhone(company.getPhone());
			dto.setMobile(company.getMobile());
			dto.setAddress(company.getAddress());		
			dto.setCity(company.getCity());
			dto.setState(company.getState());
			dto.setZip(company.getZip());
			dto.setUnitType(company.getUnitType());		
			dto.setDate(company.getDate());
			dto.setLatitude(company.getLatitude());
			dto.setLongitude(company.getLongitude());			
					
			if(company.getParent() != null){
				CompanyDto parent = fromCompanyToDto(company.getParent());
				dto.setParent(parent);
			}		
		}
		
		return returnList;
	}

}
