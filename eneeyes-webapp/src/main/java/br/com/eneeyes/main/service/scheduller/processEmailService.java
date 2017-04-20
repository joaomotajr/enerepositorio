package br.com.eneeyes.main.service.scheduller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.views.QueueEmailView;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.views.QueueEmailViewService;

@Component
public class processEmailService {
	
	@Autowired
	QueueEmailViewService service;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	SiteService siteService;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(fixedDelay = 60000)
	public void schedule() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ":Email Service - Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		List<QueueEmailView> queueLista = new ArrayList<QueueEmailView>();
		queueLista = service.findAll();
		
		for (QueueEmailView item   : queueLista) {			
			
			String email = item.getEmail();
						
			String key ="Detector: " + item.getCompany_detector_name() + " / Tipo de Alarme: " + item.getAlarmType().toString() + "\n -  Data/Hora: " + item.getLast_Update();			
			String urlTemplate = this.getClass().getClassLoader().getResource("/templates/alarme.html").toString().replace("file:", "");
			String msg = "";
			
			try {
				
				 msg = FileUtils.readFileToString(new File(urlTemplate)).replace("{{ALERTA}}", key);
				 
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Boolean ok = siteService.SendEmail(email, "Alerta de ALARME Detectado", msg);
			
			if (ok)
				positionAlarmService.updateEmailStatus(item.getUid(), EmailStatus.SENDED);
			else {
				if(item.getEmailStatus() == EmailStatus.ERR_TRY_ONE)				
					positionAlarmService.updateEmailStatus(item.getUid(), EmailStatus.ERR_TRY);
				else
					positionAlarmService.updateEmailStatus(item.getUid(), EmailStatus.ERR_TRY_ONE);
			}
		}	
	}


}
