package br.com.eneeyes.main.model.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_sumary_view")
public class CompanySumaryView {

    public CompanySumaryView() {
    	
    }     

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "COMPANY_NAME")	
	private String companyName;
	
	private Integer units;
	private Integer areas;
	private Integer devices;
	
	public final Long getUid() {
		return uid;
	}
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	public final String getCompanyName() {
		return companyName;
	}
	public final void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public final Integer getUnits() {
		return units;
	}
	public final void setUnits(Integer units) {
		this.units = units;
	}
	public final Integer getAreas() {
		return areas;
	}
	public final void setAreas(Integer areas) {
		this.areas = areas;
	}
	public final Integer getDevices() {
		return devices;
	}
	public final void setDevices(Integer devices) {
		this.devices = devices;
	}
	
	
}
