package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.eneeyes.main.model.Area;


public class AreaDto {

	private Long uid;
	private String name;
	private String description;		
	private String local;		
	private Double latitude;		
	private Double longitude;
	private Boolean classified;
	private Date date;
    private CompanyDto companyDto;
    
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
	public final CompanyDto getCompany() {
		return companyDto;
	}
	public final void setCompany(CompanyDto company) {
		this.companyDto = company;
	}	
    
	public static AreaDto fromAreaToDto(Area area) {
		
		AreaDto dto = new AreaDto();
		
		dto.setUid(area.getUid());
		dto.setName(area.getName());
		dto.setLocal(area.getLocal());
		dto.setLatitude(area.getLatitude());
		dto.setLongitude(area.getLongitude());
		dto.setDescription(area.getDescription());
		dto.setClassified(area.getClassified());
			
		return dto;
	}
	
	public static List<AreaDto> fromAreaToListDto(List<Area> list) {
		
		List<AreaDto> returnList = new ArrayList<AreaDto>();
		
		for (Area area   : list) {
			AreaDto dto = new AreaDto();
			
			dto.setUid(area.getUid());
			dto.setName(area.getName());
			dto.setLocal(area.getLocal());
			dto.setLatitude(area.getLatitude());
			dto.setLongitude(area.getLongitude());
			dto.setDescription(area.getDescription());
			dto.setClassified(area.getClassified());
			
			returnList.add(dto);
		}
		
		return returnList;
	}	
	
}
