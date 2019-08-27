package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.model.AlarmEmail;

public class AlarmEmailDto implements Comparable<AlarmEmailDto> {
	private Long uid;	
	private String email;
	private AlarmDto alarmDto;
	
	public AlarmEmailDto() {		
	}
	
	public AlarmEmailDto(AlarmEmail entity) {		
		this.uid = entity.getUid();
		this.email = entity.getEmail();	
	}

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

	public AlarmDto getAlarmDto() {
		return alarmDto;
	}

	public void setAlarmDto(AlarmDto alarmDto) {
		this.alarmDto = alarmDto;
	}

	@Override
	public int compareTo(AlarmEmailDto alarmEmailDto) {
		return alarmEmailDto.getUid().compareTo(this.uid);		
	}
}