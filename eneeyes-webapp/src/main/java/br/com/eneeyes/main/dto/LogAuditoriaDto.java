package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.main.model.LogAuditoria;
import br.com.eneeyes.main.model.enums.ActionType;

public class LogAuditoriaDto {	
	
	private Long uid;
	private Date dateTime;
	private String entity;
	private String detail;
	private ActionType action;;
	private Long userId;
	private Long companyId;
	private String ip;
	
	public LogAuditoriaDto() {
		
	}
	
	public LogAuditoriaDto(LogAuditoria logAuditoria) {
		
		this.uid = logAuditoria.getUid();
		this.dateTime = logAuditoria.getDateTime();
		this.entity = logAuditoria.getEntity();
		this.detail = logAuditoria.getDetail();
		this.action = logAuditoria.getAction();
		this.userId = logAuditoria.getUserId();
		this.companyId = logAuditoria.getCompanyId();
		this.ip = logAuditoria.getIp();		       	    	
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

	public void setAction( ActionType action) {
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
