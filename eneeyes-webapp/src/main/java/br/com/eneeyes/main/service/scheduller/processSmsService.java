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

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.model.enums.SmsStatus;
import br.com.eneeyes.main.model.views.QueueSmsView;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.views.QueueSmsViewService;

@Component
@PropertySource("classpath:parameters.properties")
public class processSmsService {
	
	@Autowired
	QueueSmsViewService service;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	SiteService siteService;
	
	@Value("${jobs.smsOn.enable}")
	private boolean smsOn;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(fixedDelayString = "${jobs.smsOn.interval}")
	public void schedule()  {
		
		if(!smsOn) return; 
		
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ":Sms Service - Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		List<QueueSmsView> smsLista = new ArrayList<QueueSmsView>();
		smsLista = service.findAll();
		
		for (QueueSmsView item   : smsLista) {
			
			String areaLocal = (item.getArea_local() != null && !item.getArea_local().equals("")) ? " / " + item.getArea_local() : "";
			String detectorLocal = (item.getCompany_detector_local() != null && !item.getCompany_detector_local().equals("")) ? item.getCompany_detector_local() : "Não Informado";
			
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						
			String msg ="MONITORAMENTO ENESENS: "
			+ "\r\nALARME: " + item.getAlarmType().toString()
			+ "\r\n" + item.getCompany_name() + " | " + item.getUnit_name()  + " | " + areaLocal
			+ "\r\nDetector: " + item.getCompany_detector_name()
			+ "\r\nLocal: " + detectorLocal 
			+ "\r\nData/Hora: " + fmt.format(item.getLast_Update())
			+ "\r\nGas: " + item.getGas_name() + " - Medição: " + item.getLast_value() + " "  + item.getUnitMeterGases();
						Boolean ok = siteService.SendSms(item.getCelular(), msg) && item.getCelular1() == null ? true : siteService.SendSms(item.getCelular1(), msg) ;
			
			if (ok)
				positionAlarmService.updateSmsStatus(item.getUid(), SmsStatus.SENDED);
			else {
				if(item.getSmsStatus() == SmsStatus.ERR_TRY_ONE)				
					positionAlarmService.updateSmsStatus(item.getUid(), SmsStatus.ERR_TRY);
				else
					positionAlarmService.updateSmsStatus(item.getUid(), SmsStatus.ERR_TRY_ONE);
			}
		}		
	}
}
