package br.com.eneeyes.main.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.AreaDto;
import br.com.eneeyes.main.dto.CompanyDto;

@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "company_units", joinColumns = @JoinColumn(name = "COMPANY_ID", referencedColumnName = "UID"), 
								inverseJoinColumns = @JoinColumn(name = "UNIT_ID", referencedColumnName = "UID"))
	private List<Unit> units = new ArrayList<Unit>();
	
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
	
	public final List<Unit> getUnits() {
		return units;
	}

	public final void setUnits(List<Unit> units) {
		this.units = units;
	}

	public static Company fromDtoToCompany(CompanyDto dto) {
		
		Company company = new Company();
		
		company.setUid(dto.getUid());
		company.setName(dto.getName());		
		
		if(dto.getUnits() != null) {
			
			List<Area> areas = new ArrayList<Area>();
			
			for (AreaDto areaDto : dto.getAreas() ) {
				Area area = Area.fromDtoToArea(areaDto);
				areas.add(area);
			}
			
			unit.setAreas(areas);
		}

		return company;
	}


}
