package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.PositionService;

@Component
public class processCheckOfflineService {
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	PositionService positionService;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(fixedDelay = 60000)
	public void schedule() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ":Check Offline Service - Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		List<Position> offlineList = new ArrayList<Position>();
		offlineList = positionService.listOffline();
		
		for (Position item  : offlineList) {			
			
			try {				
				 
				positionAlarmService.checkAndUpdateAlarmsAndActions(item, true);
				 
			} catch (Exception e) {			

				e.printStackTrace();
			}
			
			
		}	
	}


}
