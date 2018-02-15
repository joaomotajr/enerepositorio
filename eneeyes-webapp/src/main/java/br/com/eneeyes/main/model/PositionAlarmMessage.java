package br.com.eneeyes.main.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.eneeyes.archetype.model.User;
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
		this.message = dto.getMessage();
		this.lastUpdate = dto.getLastUpdate();
		this.user = new User(dto.getUserDto());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "MESSAGE", nullable = true, length=300)		
	private String message;	
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="POSITION_ALARM_ID", nullable=false)
    private PositionAlarm positionAlarm;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID", nullable=false)
    private User user;
	
	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;
	
	public final Long getUid() {
		return uid;
	}

	public final void setUid(Long uid) {
		this.uid = uid;
	}

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

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}


