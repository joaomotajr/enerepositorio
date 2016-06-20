package br.com.eneeyes.main.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.CompanyDto;

@Entity
@Table(name = "company")
public class Company {
	
	public Company() {
		
	}
	
	public Company(CompanyDto dto) {
		this.uid = dto.getUid();
		this.name = dto.getName();		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)
	private Set<Unit> units = new HashSet<Unit>();	
	
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
	
	public final Set<Unit> getUnits() {
		return units;
	}

	public final void setUnits(Set<Unit> units) {
		this.units = units;
	}
}
