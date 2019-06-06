package br.com.eneeyes.main.model.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from company_sumary_view")
public class CompanySumaryView {

    public CompanySumaryView() {
    	
    }     

	@Id
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "COMPANY_NAME")	
	private String companyName;
	
	@Column(name = "COMPANY_IMAGE")	
	private String companyImage;
	
	private Integer units;
	private Integer areas;
	private Integer devices;
	
	public final Long getUid() {
		return uid;
	}
	
	public final String getCompanyName() {
		return companyName;
	}
	
	public final Integer getUnits() {
		return units;
	}
	
	public final Integer getAreas() {
		return areas;
	}
	
	public final Integer getDevices() {
		return devices;
	}
	
	public String getCompanyImage() {
		return companyImage;
	}	
}
