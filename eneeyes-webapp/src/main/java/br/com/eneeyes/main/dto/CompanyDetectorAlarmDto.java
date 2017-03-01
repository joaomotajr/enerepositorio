package br.com.eneeyes.main.dto;

import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.CompanyDetectorAlarm;

public class CompanyDetectorAlarmDto {
	private AlarmDto alarmDto;
	private CompanyDetectorDto companyDetectorDto;
	private Long sensorId;	
	
	public CompanyDetectorAlarmDto() {
		
	}
	
	public CompanyDetectorAlarmDto(CompanyDetectorAlarm companyDetectorAlarm ) {
		
		if(companyDetectorAlarm.getCompanyDetector() != null)
			this.companyDetectorDto = new CompanyDetectorDto(companyDetectorAlarm.getCompanyDetector());
			
		this.alarmDto = new AlarmDto( companyDetectorAlarm.getAlarm());
		this.sensorId = companyDetectorAlarm.getId().getSensorId();
		
	}
	
	public CompanyDetectorAlarmDto(Alarm alarm, Long sensorId) {
							
		this.alarmDto =  new AlarmDto(alarm);
		this.sensorId = sensorId;
		
	}
	
	public CompanyDetectorAlarmDto(Alarm alarm, Long sensorId, CompanyDetectorDto companyDetectorDto) {
		
		this.alarmDto =  new AlarmDto(alarm);
		this.sensorId = sensorId;
		this.companyDetectorDto = companyDetectorDto;
		
	}
	
	public final AlarmDto getAlarmDto() {
		return alarmDto;
	}

	public final void setAlarmDto(AlarmDto alarmDto) {
		this.alarmDto = alarmDto;
	}

	public final CompanyDetectorDto getCompanyDetectorDto() {
		return companyDetectorDto;
	}

	public final void setCompanyDetectorDto(CompanyDetectorDto companyDetectorDto) {
		this.companyDetectorDto = companyDetectorDto;
	}
	
	public final Long getSensorId() {
		return sensorId;
	}
	public final void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
		
}
