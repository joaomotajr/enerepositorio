package br.com.eneeyes.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.LogAuditoriaDto;
import br.com.eneeyes.main.model.enums.ActionType;

@Entity
@Table(name = "log_auditoria")
public class LogAuditoria {
   
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")	
	private Long uid;
	
	@Column(name = "DATE_TIME")
	private Date dateTime;
	
	@Column(name = "ENTITY")
	private String entity;
	
	@Lob
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "ACTION")
	private ActionType action;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "COMPANY_ID", nullable = true)
	private Long companyId;
	
	@Column(name = "IP", nullable = true)
	private String ip;
	
	public LogAuditoria() {
		
	}
	
	public LogAuditoria(LogAuditoriaDto dto) {
		
		this.uid = dto.getUid();
		this.dateTime = dto.getDateTime();
		this.entity = dto.getEntity();
		this.detail = dto.getDetail();
		this.action = dto.getAction();
		this.userId = dto.getUserId();
		this.companyId = dto.getCompanyId();
		this.ip = dto.getIp();		   
    	    	
	}
		
	@PostUpdate
    public void logUpdate() {
		
        if (getUid() != null) {
        	this.setUid(getUid());
        }
    }

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}	

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
