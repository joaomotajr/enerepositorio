package br.com.eneeyes.main.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.eneeyes.main.dto.register.DetectorDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.enums.UnitMeterGases;

public class CompanyDetectorDto {

	private Long uid;
	private String name;
	private String description;		
	private Date date;
	private String local;
	private Double latitude;		
	private Double longitude;
	private UnitMeterGases unitMeterGases;
	private Double RangeMax;	
	private Double RangeMin;			
	private Double RangeUnit;	
	private CompanyDeviceDto companyDeviceDto;	
	private DetectorDto detectorDto;
	//private AlarmDto alarmDto;
	private List<AlarmDto> alarmsDto = new ArrayList<AlarmDto>();

	public CompanyDetectorDto() {
		
	}
	
	public CompanyDetectorDto(CompanyDetector companyDetector) {
    	
		this.uid = companyDetector.getUid();		
    	this.name = companyDetector.getName();
    	this.description = companyDetector.getDescription();
       	this.date = companyDetector.getDate();
       	this.local = companyDetector.getLocal();
       	this.latitude = companyDetector.getLatitude();
       	this.longitude = companyDetector.getLongitude();
       	this.unitMeterGases = companyDetector.getUnitMeterGases();
       	this.RangeMax = companyDetector.getRangeMax();
       	this.RangeMin = companyDetector.getRangeMin();
       	this.RangeUnit = companyDetector.getRangeUnit();
       	       	       	
       	this.detectorDto = new DetectorDto(companyDetector.getDetector()) ;
       	
       	//if(companyDetector.getAlarm() != null)
       	//	this.alarmDto = new AlarmDto(companyDetector.getAlarm());
       	
       	if(companyDetector.getAlarms() != null)		
			this.alarmsDto = parseAlarmsDto(companyDetector.getAlarms());
	}
	
	private final List<AlarmDto> parseAlarmsDto(Set<Alarm> alarms) {
		List<AlarmDto> lista = new ArrayList<AlarmDto>();
		
		if(alarms != null && !alarms.isEmpty()) {
		
			Iterator<Alarm> itr = alarms.iterator();			
			while (itr.hasNext()) {
				AlarmDto dto = new AlarmDto(itr.next());
				lista.add(dto);
			}
		}
		
		return lista;
	}
		
	public final Long getUid() {
		return uid;
	}
	public final void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public final Double getLatitude() {
		return latitude;
	}
	
	public final void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public final Double getLongitude() {
		return longitude;
	}

	public final void setLongitude(Double longitude) {
		this.longitude = longitude;
	}	

	public final UnitMeterGases getUnitMeterGases() {
		return unitMeterGases;
	}
	
	public final void setUnitMeterGases(UnitMeterGases unitMeterGases) {
		this.unitMeterGases = unitMeterGases;
	}
	
	public final Double getRangeMax() {
		return RangeMax;
	}

	public final void setRangeMax(Double rangeMax) {
		RangeMax = rangeMax;
	}

	public final Double getRangeMin() {
		return RangeMin;
	}

	public final void setRangeMin(Double rangeMin) {
		RangeMin = rangeMin;
	}

	public final Double getRangeUnit() {
		return RangeUnit;
	}

	public final void setRangeUnit(Double rangeUnit) {
		RangeUnit = rangeUnit;
	}
	
	public DetectorDto getDetectorDto() {
		return detectorDto;
	}

	public void setDetectorDto(DetectorDto detectorDto) {
		this.detectorDto = detectorDto;
	}
	
	public CompanyDeviceDto getCompanyDeviceDto() {
		return companyDeviceDto;
	}

	public void setCompanyDeviceDto(CompanyDeviceDto companyDeviceDto) {
		this.companyDeviceDto = companyDeviceDto;
	}
	
//	public final AlarmDto getAlarmDto() {
//		return alarmDto;
//	}
//
//	public final void setAlarmDto(AlarmDto alarmDto) {
//		this.alarmDto = alarmDto;
//	}
	
	public final List<AlarmDto> getAlarmsDto() {
		return alarmsDto;
	}

	public final void setAlarmsDto(List<AlarmDto> alarmsDto) {
		this.alarmsDto = alarmsDto;
	}

}
