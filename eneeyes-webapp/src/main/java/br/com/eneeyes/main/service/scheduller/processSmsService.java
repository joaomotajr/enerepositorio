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

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.views.QueueSmsView;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.views.QueueSmsViewService;

@Component
public class processSmsService {
	
	@Autowired
	QueueSmsViewService service;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	SiteService siteService;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(fixedDelay = 60000)
	public void schedule()  {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		List<QueueSmsView> smsLista = new ArrayList<QueueSmsView>();
		smsLista = service.findAll();
		
		for (QueueSmsView item   : smsLista) {
						
			String msg ="SISTEMA DE MONITORAMENTO ENESENS: Detector: " + item.getCompany_detector_name() + " / Tipo de Alarme: " + item.getAlarmType().toString() + " -  Data/Hora: " + item.getLast_Update();
			Boolean ok = siteService.SendSms(item.getCelular(), msg);
			
			if (ok)
				positionAlarmService.updateSmsStatus(item.getUid(), SmsStatus.SENDED);
			else {
				if(item.getSmsStatus() == SmsStatus.ERR_TRY_ONE)				
					positionAlarmService.updateSmsStatus(item.getUid(), SmsStatus.ERR_TRY);
				else
					positionAlarmService.updateSmsStatus(item.getUid(), SmsStatus.ERR_TRY_ONE);
			}
		}
		
		//byte[] b = (userName + ":" + pass).getBytes();
		//String userPass = new String(java.util.Base64.getMimeEncoder().encode(b), StandardCharsets.UTF_8);
						
		
	}


}
