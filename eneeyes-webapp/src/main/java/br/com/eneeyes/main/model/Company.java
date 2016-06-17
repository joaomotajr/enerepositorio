package br.com.eneeyes.main.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.dto.UnitDto;



@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SUBSELECT)
//	@JoinTable(name = "company_units", joinColumns = @JoinColumn(name = "COMPANY_ID", referencedColumnName = "UID"), 
//								inverseJoinColumns = @JoinColumn(name = "UNIT_ID", referencedColumnName = "UID"))	
	//private Set<Unit> units = new HashSet<Unit>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)	
	@IndexColumn(name = "unit")
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
	
//	public final Set<Unit> getUnits() {
//		return units;
//	}
//
//	public final void setUnits(Set<Unit> units) {
//		this.units = units;
//	}

	public static Company fromDtoToCompany(CompanyDto dto) {
		
		Company company = new Company();
				
		company.setUid(dto.getUid());
		company.setName(dto.getName());		
		
		if(dto.getUnits() != null) {

			//Set<Unit> units = new HashSet<Unit>(); 
			List<Unit> units = new ArrayList<Unit>();
			
			for (UnitDto unitDto : dto.getUnits() ) {
				Unit unit = Unit.fromDtoToUnit(unitDto);
				units.add(unit);
			}
			
			company.setUnits(units);		

		}

		return company;
	}


}
