package br.com.eneeyes.main.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.AreaDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro das Areas das Empresas
 */

@Entity
@Table(name = "area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "LOCAL", nullable = true)		
	private String Local;
	
	@Column(name = "LATITUDE", nullable = true)		
	private Double Latitude;
	
	@Column(name = "LONGITUDE", nullable = true)		
	private Double Longitude;
	
	@Column(name = "CLASSIFIED", nullable = true)
	private Boolean classified;	
	
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="company_uid")    
//    private Company company;
	
	@ManyToOne	
	@JoinTable(name = "company_areas", 
	joinColumns = @JoinColumn(name = "AREA_ID", referencedColumnName = "UID") , 
	inverseJoinColumns = @JoinColumn(name = "COMPANY_ID", referencedColumnName = "UID"))
	private Company company;
    
    @Column(name = "DATE", nullable = true)
	private Date date;
    
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocal() {
		return Local;
	}

	public void setLocal(String local) {
		Local = local;
	}

	public Double getLatitude() {
		return Latitude;
	}

	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}

	public Double getLongitude() {
		return Longitude;
	}

	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

	public Boolean getClassified() {
		return classified;
	}

	public void setClassified(Boolean classified) {
		this.classified = classified;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public static Area fromDtoToArea(AreaDto dto) {
		
		Area area = new Area();
		
		area.setUid(dto.getUid());		
		area.setName(dto.getName());
		area.setDescription(dto.getDescription());
		area.setLocal(dto.getLocal());
		area.setLatitude(dto.getLatitude());
		area.setLongitude(dto.getLongitude());
		area.setClassified(dto.getClassified());	

		if (dto.getCompanyDto() != null) {
			
			Company company = Company.fromDtoToCompany(dto.getCompanyDto());
			area.setCompany(company);
		}
		
		return area;
	}
}
