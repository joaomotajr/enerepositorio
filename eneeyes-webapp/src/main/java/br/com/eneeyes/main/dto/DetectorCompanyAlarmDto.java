package br.com.eneeyes.main.dto;

import java.math.BigInteger;

import br.com.eneeyes.main.model.DetectorCompanyAlarm;

public class DetectorCompanyAlarmDto {
	private AlarmDto alarmDto;
	private CompanyDetectorDto companyDetectorDto;
	private BigInteger sensorId;	
	
	public DetectorCompanyAlarmDto() {
		
	}
	
	public DetectorCompanyAlarmDto(DetectorCompanyAlarm detectorCompanyAlarm ) {
		
		this.alarmDto = new AlarmDto( detectorCompanyAlarm.getAlarm());
		this.sensorId = detectorCompanyAlarm.getSensorId();
		
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
	
	public final BigInteger getSensorId() {
		return sensorId;
	}
	public final void setSensorId(BigInteger sensorId) {
		this.sensorId = sensorId;
	}
		
}
