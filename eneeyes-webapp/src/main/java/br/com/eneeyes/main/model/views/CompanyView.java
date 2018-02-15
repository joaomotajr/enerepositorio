package br.com.eneeyes.main.model.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from company_view")
public class CompanyView {

    public CompanyView() {
    	
    }     

	@Id
	@Column(name = "UID")	
	private Long uid;	
	private String name;
	private String description;
	private Long company_Id;	
	
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
	public Long getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(Long company_Id) {
		this.company_Id = company_Id;
	}
}
