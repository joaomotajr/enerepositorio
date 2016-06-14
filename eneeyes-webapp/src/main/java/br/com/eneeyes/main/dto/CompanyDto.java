package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.eneeyes.main.model.Company;

public class CompanyDto {
	
	private Long uid;
	private String name;
	private List<UnitDto> units;
	
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
	public List<UnitDto> getUnits() {
		return units;
	}
	public void setUnits(List<UnitDto> units) {
		this.units = units;
	}
	
	public static CompanyDto fromCompanyToDto(Company company) {
		
		CompanyDto dto = new CompanyDto();		
	
		dto.setUid(company.getUid());
		dto.setName(company.getName());	
		
		if(company.getUnits() != null){
			List<UnitDto> units = UnitDto.fromUnitToListDto(company.getUnits());
			dto.setUnits(units);
		}
		
		
		return dto;
	}		
	
	public static List<CompanyDto> fromCompanyToListDto(List<Company> list) {
		
		List<CompanyDto> returnList = new ArrayList<CompanyDto>();
		
		for (Company company : list) {
			
			CompanyDto dto = new CompanyDto();
								
			dto.setUid(company.getUid());
			dto.setName(company.getName());	
			
			if(company.getUnits() != null){
				List<UnitDto> units = UnitDto.fromUnitToListDto(company.getUnits());
				dto.setUnits(units);
			}
		
		}
		
		return returnList;
	}
}
