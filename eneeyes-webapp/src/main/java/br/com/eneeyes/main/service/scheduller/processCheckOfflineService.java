package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.service.PositionService;
import br.com.eneeyes.main.service.buss.ProcessAlarmService;

@PropertySource("classpath:parameters.properties")
@Component
public class processCheckOfflineService {
	
	@Autowired
	ProcessAlarmService processAlarmService;
	
	@Autowired
	PositionService positionService;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());
	
	@Value("${jobs.checkOffOn.enable}")
	private boolean checkOffEnable;
	
	@Value("${jobs.checkOffOn.offTime}")
	private Integer checkOffTime;
	
		
	@Scheduled(fixedDelayString = "${jobs.checkOffOn.interval}" )	
	public void schedule() {
		
		if(!checkOffEnable) return; 
		
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ":Check Offline Service - Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));	
		
		List<Position> offlineList = new ArrayList<Position>();
		offlineList = positionService.listOffline(checkOffTime);
		
		for (Position position  : offlineList) {			
			
			try {				
				position.setAlarmType(AlarmType.OFFLINE);				
				processAlarmService.ExecuteOfflineProcedures(position);
				 
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
}
