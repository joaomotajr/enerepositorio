package br.com.eneeyes.main.repository.singleton;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.views.CompanyDetectorAlarmView;

@ApplicationScoped
public class AlarmSingletonRepository  {
	
	static List<CompanyDetectorAlarmView> alarms;
		
	public static boolean init() {
		return (alarms == null || alarms.isEmpty());			
	}
	
	public static void populate(List<CompanyDetectorAlarmView> alarms) {
				
		AlarmSingletonRepository.alarms = alarms;		
	}	
	
	public static AlarmDto findByCompanyDetector(long compnyDetectorId) {	
		AlarmDto alarmDto = null;		
		
		if(alarms != null && !alarms.isEmpty()) {
			
			for (CompanyDetectorAlarmView companyDetectorAlarm   : alarms) {
				
				if(companyDetectorAlarm.getUid() == compnyDetectorId) {
					
					alarmDto = new AlarmDto(companyDetectorAlarm.getAlarm());										
					break;
				}
			}
		}
		
		return alarmDto;
	}
	
}
