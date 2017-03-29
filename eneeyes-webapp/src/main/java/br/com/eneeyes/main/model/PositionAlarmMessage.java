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

import br.com.eneeyes.main.dto.PositionAlarmMessageDto;

/**
 * Created by Junior on 06/06/2016.
 * Cadastro de Fabricantes
 */

@Entity
@Table(name = "position_alarm_message")
public class PositionAlarmMessage {
	
	public PositionAlarmMessage() {
		
	}
	
	public PositionAlarmMessage(PositionAlarmMessageDto dto) {
		this.uid = dto.getUid();
		//this.user = dto.getUser();		
		this.message = dto.getMessage();		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
//	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
//	@JoinColumn(name="USER_ID", nullable = true)
//	private User user;
	
	@Column(name = "MESSAGE", nullable = true, length=300)		
	private String message;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="POSITION_ALARM_ID", nullable=false)
    private PositionAlarm positionAlarm;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
//	public final User getUser() {
//		return user;
//	}
//
//	public final void setUser(User user) {
//		this.user = user;
//	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}

	public final PositionAlarm getPositionAlarm() {
		return positionAlarm;
	}

	public final void setPositionAlarm(PositionAlarm positionAlarm) {
		this.positionAlarm = positionAlarm;
	}
}


