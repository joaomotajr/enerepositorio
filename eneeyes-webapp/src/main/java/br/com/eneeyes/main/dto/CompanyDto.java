package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Collections;
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
	private String description;	
	private List<UnitDto> unitsDto = new ArrayList<UnitDto>();
	private String image;
	
	public CompanyDto() {
		
	}
	
	public CompanyDto(Company company) {
		this.uid = company.getUid();
		this.name = company.getName();
		this.description = company.getDescription();
		
		if(company.getUnits() != null) {			
			this.unitsDto = setUnitsDto(company.getUnits());		
		}
		
		if (company.getImage() != null) {
			byte[] image = company.getImage();
			this.image = new String(image);
		}	
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
	
	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
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
		Collections.sort(lista);		
		return lista;
	}
	
	public final List<UnitDto> getUnitsDto() {
		return unitsDto;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
}
