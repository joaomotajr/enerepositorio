package br.com.eneeyes.main.dto;

import java.util.Date;

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
    private UnitDto unitDto;
    	
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
//	public UnitDto getUnitDto() {
//		return unitDto;
//	}
//	public void setUnitDto(UnitDto unitDto) {
//		this.unitDto = unitDto;
//	}
	
	public final UnitDto getUnitDto() {
		return unitDto;
	}
	public final void setUnitDto(UnitDto unitDto) {
		this.unitDto = unitDto;
	}
	
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
    	
	}
    
//	public static AreaDto fromAreaToDto(Area area) {
//		
//		AreaDto dto = new AreaDto();
//		
//		dto.setUid(area.getUid());
//		dto.setName(area.getName());
//		dto.setLocal(area.getLocal());
//		dto.setLatitude(area.getLatitude());
//		dto.setLongitude(area.getLongitude());
//		dto.setDescription(area.getDescription());
//		dto.setClassified(area.getClassified());
//		dto.setDate(area.getDate());
//		
//		if(area.getUnit() != null){
//			UnitDto unitDto = UnitDto.fromUnitToDto(area.getUnit());
//			dto.setUnitDto(unitDto);
//		}
//			
//		return dto;
//	}
//	
//	public static List<AreaDto> fromAreaToListDto(List<Area> list) {
//		
//		List<AreaDto> returnList = new ArrayList<AreaDto>(); 
//		
//		for (Area area   : list) {
//			AreaDto dto = new AreaDto();
//			
//			dto.setUid(area.getUid());
//			dto.setName(area.getName());
//			dto.setLocal(area.getLocal());
//			dto.setLatitude(area.getLatitude());
//			dto.setLongitude(area.getLongitude());
//			dto.setDescription(area.getDescription());
//			dto.setClassified(area.getClassified());
//			dto.setDate(area.getDate());
//			
//			if(area.getUnit() != null){
//				UnitDto unitDto = UnitDto.fromUnitToDto(area.getUnit());
//				dto.setUnitDto(unitDto);
//			}
//			
//			returnList.add(dto);
//		}				
//		
//		return returnList;
//	}
		
	
}
