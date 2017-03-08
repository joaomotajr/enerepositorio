package br.com.eneeyes.main.model.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_view")
public class CompanyView {

    public CompanyView() {
    	
    }     

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")	
	private Long uid;	
	private String name;
	private String description;
	
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
}
