package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.model.views.QueueEmailView;
import br.com.eneeyes.main.service.views.QueueEmailViewService;

@Singleton
public class processEmailService {
	
	@Autowired
	QueueEmailViewService service;
	
	@Autowired
	SiteService siteService;
	
	protected final String localhost = "127.0.0.1";
	protected final String segundaASexta = "Mon-Fri";
	protected final String cadaHora = "*";
	protected final String cadaMinuto = "*";
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());
	
	@Schedule(dayOfWeek = segundaASexta, hour = cadaHora, minute = cadaMinuto)
	public void schedule() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		List<QueueEmailView> queueLista = new ArrayList<QueueEmailView>();
		queueLista = service.findAll();
		
		for (QueueEmailView item   : queueLista) {			
			
			String email = item.getEmail();
			
			siteService.SendEmail(email);
			
		}	
	}
	
	@Timeout
	public void timeout(Timer timer) {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Ocorreu Timeout :: ");
	}

}
