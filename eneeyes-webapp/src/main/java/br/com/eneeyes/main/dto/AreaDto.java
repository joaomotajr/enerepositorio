package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.eneeyes.main.model.Area;


public class AreaDto {

	private Long uid;
	private String name;
	private String description;		
	private String Local;		
	private Double Latitude;		
	private Double Longitude;
	private Boolean Classified;
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
		return Local;
	}
	public final void setLocal(String local) {
		Local = local;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getClassified() {
		return Classified;
	}
	public void setClassified(Boolean classified) {
		Classified = classified;
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
