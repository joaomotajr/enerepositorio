package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Singleton
public class checkDeviceCalibration {
	
	protected final String localhost = "127.0.0.1";
	protected final String usernameAdmin = "SKYNET";
	protected final String segundaASexta = "Mon-Fri";
	protected final String seteETrezeHoras = "*";
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	

	//Logger log = Logger.getLogger("Service");
	
	private Log log = LogFactory.getLog(getClass());

	@Schedule(dayOfWeek = segundaASexta, hour = seteETrezeHoras)
	public void schedule() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Start Automatico :: " + new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
	}
	
	@Timeout
	public void timeout(Timer timer) {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Ocorreu Timeout :: ");
	}

}
