package br.com.eneeyes.archetype.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by asus on 24/09/14.
 */
@Named
public class SiteService {

    Log log = LogFactory.getLog(getClass());
    
    protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";

    @Inject
    Session mailSession;
   
    public Boolean SendEmail(String to, String subject, String body) {
 	   
	   String from = "joao.junior@chipsat.com.br";            
       
	   try {
 
		   Transport transport = mailSession.getTransport();  
		   InternetAddress addressFrom = new InternetAddress(from);  
	
		   MimeMessage message = new MimeMessage(mailSession);  
		   message.setSender(addressFrom);  
		   message.setSubject(subject);  
		   message.setContent(body, "text/html; charset=utf-8");    	   
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  
	
		   transport.connect();  
		   Transport.send(message);  
		   transport.close();
		   
		   return true;
	   }
	   catch (MessagingException e) {
			//e.printStackTrace();			
			log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Falha :: " + e.getMessage() + " " + new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
			return false;
		}		
  
   }

}
