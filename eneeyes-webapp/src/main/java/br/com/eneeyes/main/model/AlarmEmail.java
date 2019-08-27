package br.com.eneeyes.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eneeyes.main.dto.AlarmEmailDto;

/**
 * Created by Junior on 20/08/2019.
 * Cadastro de Emails de alarmes
 */

@Entity
@Table(name = "alarm_email")
public class AlarmEmail {
	
	public AlarmEmail() {
		
	}
	
	public AlarmEmail(AlarmEmailDto dto) {
		this.uid = dto.getUid();
		this.email = dto.getEmail();		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "EMAIL", nullable = false)
	String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ALARM_ID", nullable=false)
    private Alarm alarm;
		
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}	
}