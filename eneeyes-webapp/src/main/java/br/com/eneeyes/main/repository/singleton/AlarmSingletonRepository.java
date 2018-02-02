package br.com.eneeyes.main.repository.singleton;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.Alarm;

@ApplicationScoped
public class AlarmSingletonRepository  {
	
	static List<Alarm> lista;
		
	public static boolean init() {
		return (lista == null || lista.isEmpty());			
	}
	
	public static void populate(List<Alarm> alarm) {
				
		lista = alarm;		
	}	
	
	public static AlarmDto findByCompanyDetector(long alarmUid) {	
		AlarmDto alarmDto = null;
		
		
		if(lista != null && !lista.isEmpty()) {
			
			for (Alarm alarm   : lista) {
				
				if(alarm.getUid() == alarmUid) {
					
					alarmDto = new AlarmDto(alarm);
					break;
				}
			}
		}
		
		return alarmDto;
	}
	
}
