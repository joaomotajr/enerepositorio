package br.com.eneeyes.main.service.scheduller;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.views.QueueEmailViewService;

@Component
public class processSmsService {
	
	@Autowired
	QueueEmailViewService service;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	SiteService siteService;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());

	@Scheduled(fixedDelay = 10000)
	public void schedule() {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
		
		String userName = "GRUPO";
		String pass = "ALLCANCE";
		byte[] b = (userName + ":" + pass).getBytes();
		
		String enc2 = new String(java.util.Base64.getMimeEncoder().encode(b), StandardCharsets.UTF_8);
		
//		HttpResponse<String> response = Unirest.post("https://api.allcancesms.com/sms/1/text/single")
//				.header("authorization", "Basic R1JVUE86QUxMQ0FOQ0U=")
//				.header("content-type", "application/json")
//				.header("accept", "application/json")
//				.body("{\"from\":\"AllcanceSMS\",\"to\":\"5537999368807\",\"text\":\"Teste SMS.\"}")
//				.asString();
		
		System.out.println("enc1 = <" + enc2 + ">");
	}


}
