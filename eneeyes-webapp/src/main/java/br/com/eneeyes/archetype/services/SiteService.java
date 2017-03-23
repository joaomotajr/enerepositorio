package br.com.eneeyes.archetype.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.eneeyes.archetype.dto.Error;
import br.com.eneeyes.archetype.dto.Result;
import br.com.eneeyes.archetype.dto.Success;

/**
 * Created by asus on 24/09/14.
 */
@Named
public class SiteService {

    Log log = LogFactory.getLog(getClass());

    @Inject
    ObjectMapper objectMapper;

    @Inject
    Session mailSession;

    @Produce
    ProducerTemplate producer;

    @SuppressWarnings("rawtypes")
	public Future<Result> sendMessage(final String to, final String subject, final String message) throws Exception {
        
    	Map<String, String> sendmail = new HashMap<String, String>();
                
        sendmail.put("to", to);
        sendmail.put("subject", subject);
        sendmail.put("message", message);

        String json = objectMapper.writeValueAsString(sendmail);
        Future<Result> future = producer.asyncRequestBody("seda:site.sendmail", json, Result.class);
        return future;
    }

    @Consume(uri = "seda:site.sendmail")
    public Result<Boolean> sendMessage(String json) throws Exception {
        log.info(String.format("json: %s", json));

        Result<Boolean> result = new Success("SCCSTE0001","async.send.mail.successMessage");
        try {
            @SuppressWarnings("unchecked")

            Map<String, String> sendmail = objectMapper.readValue(json, Map.class);
            String from = "joao.junior@chipsat.com.br"; 
            String to = sendmail.get("to");
            
            String subject = sendmail.get("subject");
            String message = sendmail.get("message");

            MimeMessage mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(message, "text/html; charset=utf-8");
            
            Transport.send(mimeMessage);
        } catch (Exception e) {
            log.error(e);
            result = new Error("ERRSTE0001", e.getMessage());
        }
        return result;
    }
    
    public void SendEmail(String to) {
 	   
	   String subject = "Enviando email com JavaMail";
	   //String msg = "Enviei este email utilizando JavaMail com minha conta GMail!";
	   String from = "joao.junior@chipsat.com.br";            
       
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
    	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

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
