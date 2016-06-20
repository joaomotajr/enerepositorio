package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.model.Unit;

/**
* Created by Junior on 06/06/2016.
*/

public class CompanyDto {
	
	private Long uid;
	private String name;
//	private List<UnitDto> units;
	private List<UnitDto> unitsDto = new ArrayList<UnitDto>();	
	
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
//	public List<UnitDto> getUnits() {
//		return units;
//	}
//	public void setUnits(List<UnitDto> units) {
//		this.units = units;
//	}
	
	public CompanyDto() {
		
	}
	
	public CompanyDto(Company company) {
		this.uid = company.getUid();
		this.name = company.getName();
		
		if(company.getUnits() != null) {
			
			this.unitsDto = setUnitsDto(company.getUnits());
		
		}
	}
	
	private List<UnitDto> setUnitsDto(Set<Unit> units) {
		
		List<UnitDto> lista = new ArrayList<UnitDto>();
		
		if(units != null && !units.isEmpty()) {
		
			Iterator<Unit> itr = units.iterator();
			
			while (itr.hasNext()) {
				UnitDto dto = new UnitDto(itr.next());
				lista.add(dto);
			}
		}
		
		return lista;
	}
	
	public final List<UnitDto> getUnitsDto() {
		return unitsDto;
	}
	
	
//	public static CompanyDto fromCompanyToDto(Company company) {
//		
//		CompanyDto dto = new CompanyDto();		
//	
//		dto.setUid(company.getUid());
//		dto.setName(company.getName());	
//		
////		if(company.getUnits() != null){
////			List<UnitDto> units = UnitDto.fromUnitToListDto(company.getUnits());
////			dto.setUnits(units);
////		}
//		
//		
//		return dto;
//	}		
//	
//	public static List<CompanyDto> fromCompanyToListDto(List<Company> list) {
//		
//		List<CompanyDto> returnList = new ArrayList<CompanyDto>();
//		
//		for (Company company : list) {
//			
//			CompanyDto dto = new CompanyDto();
//								
//			dto.setUid(company.getUid());
//			dto.setName(company.getName());	
//			
////			if(company.getUnits() != null){
////				List<UnitDto> units = UnitDto.fromUnitToListDto(company.getUnits());
////				dto.setUnits(units);
////			}		
//			
//			returnList.add(dto);
//		}
//		
//		return returnList;
//	}
}
