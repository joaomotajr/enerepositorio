package br.com.eneeyes.main.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.model.CompanyDetectorAlarm;

@ApplicationScoped
public class CompanyDetectorAlarmSingletonRepository  {
	
	static List<CompanyDetectorAlarm> lista;
		
	public static boolean init() {
		return (lista == null || lista.isEmpty());			
	}
	
	public static void populate(List<CompanyDetectorAlarm> companyDetectorAlarm) {
				
		lista = companyDetectorAlarm;		
	}
	
	public static CompanyDetectorAlarmDto findByCompanyDetectorAndSensor(long companyDetectorUid, long sensorUid) {
		
		CompanyDetectorAlarmDto companyDetectorAlarmDto = null;
		
		if(lista != null && !lista.isEmpty()) {
			
			for (CompanyDetectorAlarm companyDetectorAlarm   : lista) {
				
				if(companyDetectorAlarm.getId().getCompanyDetectorId() == companyDetectorUid && companyDetectorAlarm.getId().getSensorId() == sensorUid) {
					
					companyDetectorAlarmDto = new CompanyDetectorAlarmDto(companyDetectorAlarm.getAlarm(), companyDetectorAlarm.getId().getSensorId());
					break;
				}
			}
		}		
		
		return companyDetectorAlarmDto;
	}
	
}
