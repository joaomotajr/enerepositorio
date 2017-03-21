package br.com.eneeyes.main.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.inject.Named;
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

import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.model.acl.UserRepository;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.archetype.web.result.ResultErrorMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.dto.PositionAlarmDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.CompanyDetectorAlarmSingletonRepository;
import br.com.eneeyes.main.repository.PositionAlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class PositionAlarmService implements IService<PositionAlarmDto> {
	
	@Autowired
	private PositionAlarmRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	CompanyDetectorAlarmService companyDetectorAlarmAlarmService;
	
	@Autowired
	private SiteService siteService;
	
	private Log log = LogFactory.getLog(getClass());

	@Override
	public BasicResult<?> save(PositionAlarmDto dto) {
		
		// TODO Auto-generated method stub
		return null;
	}
	
    /** Método checar se houve violação de Alarme
     *   @return sem retorno  						*/
	public AlarmType checkAlarmLimits(Position position) {		
		
		CompanyDetector companyDetector = new CompanyDetector(position.getCompanyDetector().getUid());
		Sensor sensor = new Sensor(position.getSensor().getUid());
		
		if(CompanyDetectorAlarmSingletonRepository.init()) {
			CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
		}
		
		CompanyDetectorAlarmDto alarm = CompanyDetectorAlarmSingletonRepository.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
		//CompanyDetectorAlarmDto alarm = companyDetectorAlarmAlarmService.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
			
		AlarmType alarmType = AlarmType.NORMAL ;
		
		if(alarm != null) {
			
			if( position.getLastValue().compareTo( new BigDecimal(alarm.getAlarmDto().getAlarm3())) > 0 ) {
				
				alarmType = AlarmType.EVACUACAO;
			}
			else if( position.getLastValue().compareTo( new BigDecimal(alarm.getAlarmDto().getAlarm2())) > 0 ) {
				
				alarmType = AlarmType.ALERTA;
			}
			else if( position.getLastValue().compareTo( new BigDecimal(alarm.getAlarmDto().getAlarm1())) > 0 ) {
				
				alarmType = AlarmType.DETECCAO;				
			}		
		}	
		
		return alarmType;
	}

	/**
	 * @param position
	 * @param companyDetector
	 * @param sensor
	 * @param alarmType
	 */
	public void saveOrUpdatePositionAlarm(Position position, CompanyDetector companyDetector, Sensor sensor, AlarmType alarmType) {
		
		PositionAlarm positionAlarm = repository.findByCompanyDetectorAndSensorAndAlarmType(companyDetector, sensor, alarmType);
		
		if(positionAlarm == null) {
			
			positionAlarm =  new PositionAlarm();
			
			positionAlarm.setCompanyDetector(position.getCompanyDetector());
			positionAlarm.setSensor(position.getSensor());
			positionAlarm.setFirstUpdate(new Date());
			positionAlarm.setLastValue(position.getLastValue());
			positionAlarm.setAlarmStatus(AlarmStatus.CREATED);
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setAlarmType(alarmType);
		}
		else {
			
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setLastValue(position.getLastValue());				
		}
		
		repository.save(positionAlarm);
		
		//TODO - Inserir log de Sistema e Tratamento de Erro
		//sendEmail("joaomotajunior@gmail.com");
		SendHTMLEmail();
		
	}	

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResult<?> findOne(Long uid) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		try {
			PositionAlarm item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new PositionAlarmDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
		
	private void sendEmail(String email1) {
		UserResult result = new UserResult();
		
		User user = userRepository.findByLogin(email1);
		if (user == null) {
            result.addMessage(new ResultErrorMessage("form.erro.site.forgetPassword.naoEncontrado"));
            return;
        }
		
		try 
		{
			
			String to = user.getLogin();
			String key = UUID.randomUUID().toString();
			String urlTemplate = this.getClass().getClassLoader().getResource("/templates/recover-password.html").toString().replace("file:", "");
			String message = FileUtils.readFileToString(new File(urlTemplate)).replace("{{KEY}}",key);
			String subject = "Eneneyes - Recuperação de senha";
			String from = "joaomotajr@hotmail.com.br";
						
			siteService.sendMessage(to, from, subject, message);
		}
		catch (Exception e) 
		{	
			log.error(e);
		}
	}
	



   public static void SendHTMLEmail() {
      // Recipient's email ID needs to be mentioned.
      String to = "joaomotajr@hotmail.com";

      // Sender's email ID needs to be mentioned
      String from = "joaomotajunior@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>This is actual message</h1>", "text/html");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }

	
	public BasicResult<?> findByCompanyDetector(Long uid) {
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		try {
			List<PositionAlarm> lista = repository.findByCompanyDetector(companyDetector);
			
			if (lista != null) {
				
				List<PositionAlarmDto> dto = new ArrayList<PositionAlarmDto>();
				
				for (PositionAlarm positionAlarm   : lista) {					
					dto.add(new PositionAlarmDto(positionAlarm) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma area.");
			}
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	
	public Result<?> listAll() {
		
		Result<PositionAlarmDto> result = new Result<PositionAlarmDto>(); 	
		
		try {
			List<PositionAlarm> lista = repository.findAll();

			if (lista != null) {
				
				List<PositionAlarmDto> dto = new ArrayList<PositionAlarmDto>();
				
				for (PositionAlarm positionAlarm   : lista) {					
					dto.add(new PositionAlarmDto(positionAlarm) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma area.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}	
}
