package br.com.eneeyes.main.repository.singleton;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.views.CompanyDeviceAlarmView;

@ApplicationScoped
public class AlarmSingletonRepository  {
	
	static List<CompanyDeviceAlarmView> alarms;
		
	public static boolean init() {
		return (alarms == null || alarms.isEmpty());			
	}
	
	public static void populate(List<CompanyDeviceAlarmView> alarms) {
				
		AlarmSingletonRepository.alarms = alarms;		
	}	
	
	public static AlarmDto findByCompanyDevice(long companyDeviceId) {	
		AlarmDto alarmDto = null;		
		
		if(alarms != null && !alarms.isEmpty()) {
			
			for (CompanyDeviceAlarmView companyDeviceAlarm   : alarms) {
				
				if(companyDeviceAlarm.getUid() == companyDeviceId) {
					
					alarmDto = new AlarmDto(companyDeviceAlarm.getAlarm());										
					break;
				}
			}
		}
		
		return alarmDto;
	}
	
}
