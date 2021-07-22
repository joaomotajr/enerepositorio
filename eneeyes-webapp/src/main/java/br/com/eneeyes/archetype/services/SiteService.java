package br.com.eneeyes.archetype.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by JR on 24/04/17.
 */
@Service
public class SiteService {

    Log log = LogFactory.getLog(getClass());
    
    protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";

    @Autowired
    Session mailSession;
   
    public Boolean SendEmail(ArrayList<String> to, String subject, String body) {
 	   
	   final String from = "contato@enesens.com.br";            
	          
	   try {
		   
		   // Get system properties
			Properties props = System.getProperties();
			
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			// Get the Session object.// and pass 
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			
			    protected PasswordAuthentication getPasswordAuthentication() {
			
			        return new PasswordAuthentication(from, "enetec2015");
			
			    }
			
			});
		     
		   session.setDebug(true);
		   		   		   
		   MimeMessage message = new MimeMessage(session);  
		   message.setSender(new InternetAddress(from));  
		   message.setSubject(subject);  
		   message.setContent(body, "text/html; charset=utf-8");
		   
		   for (int i = 0; i < to.size(); i++) {			
			   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(i)));
		   }		   
		     
		   Transport.send(message); 
		   		   
		   return true;
	   }
	   catch (MessagingException e) {
			//e.printStackTrace();			
			log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Falha :: " + e.getMessage() + " " + new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
			return false;
		} 
   }
    
    
    public Boolean SendSms(String to, String text) {
    	
    	try {    		 

	    	Map<String, String> params = new HashMap<String, String>();
	
			params.put("to", "55" + to.replaceAll("\\D+",""));
			params.put("text", text);
					
			RestTemplate restTemplate = new RestTemplate();
			
			String url = "http://api.allcancesms.com.br/sms/1/text/query?username=System100&password=NQENsXdl&to={to}&text={text}";
			String result = restTemplate.getForObject(url, String.class, params);
			                                                 			
			log.info(result.toString());
			
			SmsResult smsResult = new ObjectMapper().readValue(result, SmsResult.class);			
			if (smsResult.getMessages().get(0).getStatus().getName().equalsIgnoreCase("REJECTED_NOT_ENOUGH_CREDITS")) {
				log.warn("Falta de Credito");
				throw new Exception("Falta de Credito");
			}
			
			return true;
    	}
    	catch (Exception e) {
						
			log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Falha :: " + e.getMessage() + " " + new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
			return false;
		}
    }

}
