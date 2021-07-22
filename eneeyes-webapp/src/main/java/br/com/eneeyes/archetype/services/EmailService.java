package br.com.eneeyes.archetype.services;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by asus on 24/09/14.
 */
@Service
public class EmailService extends Thread {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    Session mailSession;
    
    private String to;
    
    public final String getTo() {
		return to;
	}


	public final void setTo(String to) {
		this.to = to;
	}
	
	public EmailService() {
		
	}

	public EmailService(String to) {}
    
    @Override
    public void run() {
 	   
	   String subject = "Enviando email com JavaMail";
	   String from = "contato@enesens.com.br";            
       
       try {
  
    	   Transport transport = mailSession.getTransport();  
    	   InternetAddress addressFrom = new InternetAddress(from);  
    	   
    	   String key = UUID.randomUUID().toString();
    	   String urlTemplate = this.getClass().getClassLoader().getResource("/templates/recover-password.html").toString().replace("file:", "");
    	   String msg = FileUtils.readFileToString(new File(urlTemplate)).replace("{{KEY}}",key);

    	   MimeMessage message = new MimeMessage(mailSession);  
    	   message.setSender(addressFrom);  
    	   message.setSubject(subject);  
    	   message.setContent(msg, "text/html; charset=utf-8");    	   
    	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));  

    	   transport.connect();  
    	   Transport.send(message);  
    	   transport.close();
             
        } 
       catch (MessagingException e) 
       {
             throw new RuntimeException(e);
       } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
   }

}
