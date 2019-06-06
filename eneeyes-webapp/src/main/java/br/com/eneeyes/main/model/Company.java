package br.com.eneeyes.main.model;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
		this.description = dto.getDescription();	

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
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.ALL)
	private Set<Unit> units = new HashSet<Unit>();	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
	private Set<Alarm> alarms = new HashSet<Alarm>();
	
	@Lob
	@Column(name = "IMAGE", nullable = true)
	byte[] image;	

	
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
	
	public final Set<Unit> getUnits() {
		return units;
	}

	public final void setUnits(Set<Unit> units) {
		this.units = units;
	}
	
	public final byte[] getImage() {
		return image;
	}

	public final void setImage(byte[] image) {
		this.image = image;
	}
}
