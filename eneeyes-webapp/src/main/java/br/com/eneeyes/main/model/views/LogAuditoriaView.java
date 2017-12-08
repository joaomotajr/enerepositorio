package br.com.eneeyes.main.model.views;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "log_auditoria_view")
public class LogAuditoriaView {
   
	@Id	
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "ACTION_NAME")
	private String actionName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "DATE_TIME")
	private Date dateTime;
	
	@Lob
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "ENTITY")
	private String entity;
	
	@Column(name = "COMPANY_NAME", nullable = true)
	private String companyName;
	
	@Column(name = "COMPANY_ID", nullable = true)
	private Long companyId;	
	
	public LogAuditoriaView() {
		
	}

	public Long getUid() {
		return uid;
	}

	public String getActionName() {
		return actionName;
	}

	public String getUserName() {
		return userName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public String getDetail() {
		return detail;
	}

	public String getEntity() {
		return entity;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}
	
}
