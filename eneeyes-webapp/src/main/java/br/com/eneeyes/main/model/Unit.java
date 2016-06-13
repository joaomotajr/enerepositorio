package br.com.eneeyes.main.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.eneeyes.main.dto.UnitDto;
import br.com.eneeyes.main.model.enums.UnitType;
 
 /**
 * Created by Junior on 06/06/2016.
 * Cadastro das Empresas a serem monitoradas
 */


@Entity
@Table(name = "unit")
public class Unit {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = true)
	private String email;
	
	@Column(name = "URL", nullable = true)
	private String url;

	@Column(name = "PHONE", nullable = true)
	private String phone;
	
	@Column(name = "MOBILE", nullable = true)
	private String mobile;		
	
	@Column(name = "ADDRESS", nullable = true)
	private String address;
	
	@Column(name = "CITY", nullable = true)
	private String city;
	
	@Column(name = "STATE", nullable = true)
	private String state;
	
	@Column(name = "ZIP", nullable = true)
	private String zip;

	@Column(name = "UNIT_TYPE", columnDefinition = "int default 0")
	private UnitType unitType;	
	
	@Column(name = "LATITUDE", nullable = true)		
	private Double latitude;
	
	@Column(name = "LONGITUDE", nullable = true)		
	private Double longitude;

	@Enumerated(EnumType.ORDINAL) 
	private UnitType UnitType() { 
	    return unitType; 
	}
	
	@Column(name = "DATE", nullable = true)
	private Date date;
	
	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
		if (unitType == null ) {			
			this.unitType = UnitType.UNICA;
		}	
		else { 
			this.unitType = unitType;
		}
	}	
	
	@ManyToOne
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name="parent_id")
    private Unit parent;
	    
    @OneToMany(mappedBy="parent")
    private Set<Unit> childs = new HashSet<Unit>();       

    
    @OneToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "unit_areas", joinColumns = @JoinColumn(name = "UNIT_ID", referencedColumnName = "UID"), 
								inverseJoinColumns = @JoinColumn(name = "AREA_ID", referencedColumnName = "UID"))
	private List<Area> areas = new ArrayList<Area>();
    	
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

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
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

	public final Unit getParent() {
		return parent;
	}

	public final void setParent(Unit parent) {
		this.parent = parent;
	}

	public final Set<Unit> getChilds() {
		return childs;
	}

	public final void setChilds(Set<Unit> childs) {
		this.childs = childs;
	}

	public final List<Area> getAreas() {
		return areas;
	}

	public final void setAreas(List<Area> areas) {
		this.areas = areas;
	}	
	
	public static Unit fromDtoToUnit(UnitDto dto) {
		
		Unit unit = new Unit();
		
		unit.setUid(dto.getUid());
		unit.setName(dto.getName());		
		unit.setEmail(dto.getEmail());
		unit.setUrl(dto.getUrl());
		unit.setPhone(dto.getPhone());
		unit.setMobile(dto.getMobile());
		unit.setAddress(dto.getAddress());		
		unit.setCity(dto.getCity());
		unit.setState(dto.getState());
		unit.setZip(dto.getZip());
		unit.setUnitType(dto.getUnitType());		
		unit.setDate(dto.getDate());
		unit.setLatitude(dto.getLatitude());
		unit.setLongitude(dto.getLongitude());			
		
		if (dto.getParent() != null) {
			Unit parent = fromDtoToUnit(dto.getParent());
			unit.setParent(parent);
		}	

		return unit;
	}
	
}
