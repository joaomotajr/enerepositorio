package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.model.AlarmMobile;

public class AlarmMobileDto implements Comparable<AlarmMobileDto> {
	private Long uid;	
	private String mobile;
	private AlarmDto alarmDto;
	
	public AlarmMobileDto() {		
	}
	
	public AlarmMobileDto(AlarmMobile entity) {		
		this.uid = entity.getUid();
		this.mobile = entity.getMobile();	
	}

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

	public AlarmDto getAlarmDto() {
		return alarmDto;
	}

	public void setAlarmDto(AlarmDto alarmDto) {
		this.alarmDto = alarmDto;
	}

	@Override
	public int compareTo(AlarmMobileDto alarmCelularDto) {
		return alarmCelularDto.getUid().compareTo(this.uid);		
	}
}