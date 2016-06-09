package br.com.eneeyes.main.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.model.enums.UnitType;
 
 /**
 * Created by Junior on 06/06/2016.
 * Cadastro das Empresas a serem monitoradas
 */


@Entity
@Table(name = "company")
public class Company {
		
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
    private Company parent;
	    
    @OneToMany(mappedBy="parent")
    private Set<Company> childs = new HashSet<Company>();	
        
    @OneToMany(mappedBy = "company", targetEntity = Area.class, fetch = FetchType.LAZY)
    @Cascade(value = CascadeType.ALL)
    private List<Area> areas;
    	
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

	public final Company getParent() {
		return parent;
	}

	public final void setParent(Company parent) {
		this.parent = parent;
	}

	public final Set<Company> getChilds() {
		return childs;
	}

	public final void setChilds(Set<Company> childs) {
		this.childs = childs;
	}

	public final List<Area> getAreas() {
		return areas;
	}

	public final void setAreas(List<Area> areas) {
		this.areas = areas;
	}	
	
	public static Company fromDtoToCompany(CompanyDto companyDto) {
		
		Company company = new Company();
		
		company.setUid(companyDto.getUid());
		company.setName(companyDto.getName());		
		company.setEmail(companyDto.getEmail());
		company.setUrl(companyDto.getUrl());
		company.setPhone(companyDto.getPhone());
		company.setMobile(companyDto.getMobile());
		company.setAddress(companyDto.getAddress());		
		company.setCity(companyDto.getCity());
		company.setState(companyDto.getState());
		company.setZip(companyDto.getZip());
		company.setUnitType(companyDto.getUnitType());		
		company.setDate(companyDto.getDate());
		company.setLatitude(companyDto.getLatitude());
		company.setLongitude(companyDto.getLongitude());			
		
		if (companyDto.getParent() != null) {
			Company parent = fromDtoToCompany(companyDto.getParent());
			company.setParent(parent);
		}	

		return company;
	}
	
}
