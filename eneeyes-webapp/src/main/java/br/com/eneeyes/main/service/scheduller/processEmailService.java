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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.enums.EmailStatus;
import br.com.eneeyes.main.model.views.QueueEmailView;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.views.QueueEmailViewService;

@Component
@PropertySource("classpath:parameters.properties")
public class processEmailService {
	
	@Autowired
	QueueEmailViewService service;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	SiteService siteService;
	
	@Value("${jobs.emailOn.enable}")
	private boolean emailOn;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(fixedDelayString = "${jobs.emailOn.interval}")
	public void schedule() {
		
		if(!emailOn) return; 
		
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ":Email Service - Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		List<QueueEmailView> queueLista = new ArrayList<QueueEmailView>();
		queueLista = service.findAll();
		
		for (QueueEmailView item   : queueLista) {			
			
			String emails[] = {item.getEmail(), item.getEmail1()}  ;
			String areaLocal = (item.getArea_local() != null && !item.getArea_local().equals("")) ? " / " + item.getArea_local() : "/ Local não Informado";
			String detectorLocal = (item.getCompany_detector_local() != null && !item.getCompany_detector_local().equals("")) ? item.getCompany_detector_local() : "Não Informado";
			String medicao = (item.getAlarmType() == AlarmType.OFFLINE) ? " " : " - Medição: " + item.getLast_value() + " ";
						
			String key = "ALARME DE: <span style='color:red; font-size: 1.2em'>" + item.getAlarmType().toString() + "</span></b><br>" + 
					"<h3>" + item.getCompany_name() + "</h3>" +
					"Unidade: " + item.getUnit_name()  + "<br>" +
					"Área: " + item.getArea_name() + areaLocal +
					"<hr>" +
					"<h3><u>INFORMAÇÕES DO ALERTA</u></h3>" +					
					"<b>Detector:</b> " + item.getCompany_detector_name() + "<br>" +
					"<b>Local:</b> " + detectorLocal + "<br>" + 					 
					"<b>Data/Hora:</b> " + item.getLast_Update() +
					"<hr>" + 
					"<i><span style='font-size: 1.2em; font-weight: bold'>" + item.getGas_name() + medicao  + item.getUnitMeterGases() + "</span></i>";
			
			String urlTemplate = this.getClass().getClassLoader().getResource("/templates/alarme.html").toString().replace("file:", "");
			String msg = "";
			
			try {
				
				 msg = FileUtils.readFileToString(new File(urlTemplate)).replace("{{ALERTA}}", key);
				 
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Boolean ok = siteService.SendEmail(emails, "Alerta de ALARME Detectado", msg);
			
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
