package br.com.eneeyes.main.model;

import java.util.Date;

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
		this.message = dto.getMessage();
		this.lastUpdate = dto.getLastUpdate();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "MESSAGE", nullable = true, length=300)		
	private String message;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="POSITION_ALARM_ID", nullable=false)
    private PositionAlarm positionAlarm;
	
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
	
	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}


