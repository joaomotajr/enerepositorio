package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class checkDeviceCalibration {
		
	protected final String segundaASexta = "Mon-Fri";
	protected final String seteETrezeHoras = "*";
	protected final String cadaMinuto = "*";
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(cron = "0 15 10 * * ?")
	public void schedule() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Start Automatico :: " + new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
	}
	

}
