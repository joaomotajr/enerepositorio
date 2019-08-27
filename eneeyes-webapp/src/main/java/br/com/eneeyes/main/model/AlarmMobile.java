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

import br.com.eneeyes.main.dto.AlarmMobileDto;

/**
 * Created by Junior on 20/08/2019.
 * Cadastro de Emails de alarmes
 */

@Entity
@Table(name = "alarm_mobile")
public class AlarmMobile {
	
	public AlarmMobile() {
		
	}
	
	public AlarmMobile(AlarmMobileDto dto) {
		this.uid = dto.getUid();
		this.mobile = dto.getMobile();		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "MOBILE", nullable = false)
	String mobile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ALARM_ID", nullable=false)
    private Alarm alarm;
		
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}	
}