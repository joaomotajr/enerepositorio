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
	
	@Column(name = "DATE_TIME")
	private Date dateTime;
	
	@Column(name = "ENTITY")
	private String entity;
	
	@Lob
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "ACTION_NAME")
	private String actionName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	@Column(name = "IP", nullable = true)
	private String ip;
	
	public LogAuditoriaView() {
		
	}	
	
}
