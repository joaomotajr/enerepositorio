package br.com.eneeyes.main.model;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.AreaDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro das Areas das Empresas
 */

@Entity
@Table(name = "area")
public class Area {
	
    public Area() {
    	
    }
    
    public Area(AreaDto dto) {
    	
    	this.uid = dto.getUid();		
    	this.name = dto.getName();
    	this.description = dto.getDescription();
    	this.local = dto.getLocal();
    	this.latitude = dto.getLatitude();
    	this.longitude = dto.getLongitude();
    	this.classified= dto.getClassified();		
    	this.date = dto.getDate();
    	
    	if (dto.getImage() != null) {
	    	try {
				byte[] image = null;
				image = dto.getImage().getBytes("US-ASCII");
				this.setImage(image);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}    	
    	}
    }	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "LOCAL", nullable = true)		
	private String local;
	
	@Column(name = "LATITUDE", nullable = true)		
	private Double latitude;
	
	@Column(name = "LONGITUDE", nullable = true)		
	private Double longitude;
	
	@Column(name = "CLASSIFIED", nullable = true)
	private Boolean classified;
    
    @Column(name = "DATE", nullable = true)
	private Date date;
    
    @Lob
	@Column(name = "IMAGE", nullable = true)
	byte[] image;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIT_ID", nullable=false)
    private Unit unit;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "area", cascade = CascadeType.ALL)
	private Set<CompanyDevice> companyDevices = new HashSet<CompanyDevice>();

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
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getClassified() {
		return classified;
	}

	public void setClassified(Boolean classified) {
		this.classified = classified;
	}
	
	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}
	
	public final byte[] getImage() {
		return image;
	}

	public final void setImage(byte[] image) {
		this.image = image;
	}

	public final Unit getUnit() {
		return unit;
	}

	public final void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public final Set<CompanyDevice> getCompanyDevices() {
		return companyDevices;
	}

	public final void setCompanyDevices(Set<CompanyDevice> companyDevices) {
		this.companyDevices = companyDevices;
	}

}
