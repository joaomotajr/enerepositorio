package br.com.eneeyes.main.dto;

import java.util.Date;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.main.model.PositionAlarmMessage;

public class PositionAlarmMessageDto {
	
	private Long uid;
	private String message;
	private PositionAlarmDto positionAlarmDto;
	private UserDto userDto;
	private Date lastUpdate;	
	
	public PositionAlarmMessageDto() {
		
	}
	
	public PositionAlarmMessageDto(PositionAlarmMessage positionAlarmMessage) {
		
		this.uid = positionAlarmMessage.getUid();
		this.message = positionAlarmMessage.getMessage();
		this.userDto = new UserDto(positionAlarmMessage.getUser());
		this.lastUpdate = positionAlarmMessage.getLastUpdate();
	}

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
	
	public final PositionAlarmDto getPositionAlarmDto() {
		return positionAlarmDto;
	}

	public final void setPositionAlarmDto(PositionAlarmDto positionAlarmDto) {
		this.positionAlarmDto = positionAlarmDto;
	}

	public final UserDto getUserDto() {
		return userDto;
	}

	public final void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public final Date getLastUpdate() {
		return lastUpdate;
	}

	public final void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
