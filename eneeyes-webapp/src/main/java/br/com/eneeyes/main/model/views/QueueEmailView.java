package br.com.eneeyes.main.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.eneeyes.main.model.enums.EmailStatus;


@Entity
@Table(name="queue_email_view")
public class QueueEmailView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public QueueEmailView() {		
	
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long uid;

	@Column(name = "email_status")
	private EmailStatus emailStatus;

	private String name;
	private String email;
	private Long company_detector_id;	
	private Long sensor_id;
		
	
	public Long getUid() {
		return uid;
	}
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public EmailStatus getEmailStatus() {
		return emailStatus;
	}
	
	public void setEmailStatus(EmailStatus emailStatus) {
		this.emailStatus = emailStatus;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getCompany_detector_id() {
		return company_detector_id;
	}
	
	public void setCompany_detector_id(Long company_detector_id) {
		this.company_detector_id = company_detector_id;
	}
	
	public Long getSensor_id() {
		return sensor_id;
	}
	
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
	
}