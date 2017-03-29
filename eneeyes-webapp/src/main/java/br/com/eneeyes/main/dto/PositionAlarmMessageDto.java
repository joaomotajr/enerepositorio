package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.model.PositionAlarmMessage;

public class PositionAlarmMessageDto {
	private Long uid;
	//private User user;
	private String message;
	private PositionAlarmDto positionAlarmDto;
		
	public PositionAlarmMessageDto() {
		
	}
	
	public PositionAlarmMessageDto(PositionAlarmMessage positionAlarmMessage) {
		
		this.uid = positionAlarmMessage.getUid();
		//this.user = positionAlarmMessage.getUser();
		this.message = positionAlarmMessage.getMessage();		
	}

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
	
	public final PositionAlarmDto getPositionAlarmDto() {
		return positionAlarmDto;
	}

	public final void setPositionAlarmDto(PositionAlarmDto positionAlarmDto) {
		this.positionAlarmDto = positionAlarmDto;
	}
	
}
