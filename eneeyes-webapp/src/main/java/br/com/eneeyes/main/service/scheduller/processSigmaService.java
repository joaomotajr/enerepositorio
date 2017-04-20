package br.com.eneeyes.main.service.scheduller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eneeyes.archetype.services.SiteService;
import br.com.eneeyes.main.model.enums.SigmaStatus;
import br.com.eneeyes.main.model.views.QueueSigmaView;
import br.com.eneeyes.main.service.PositionAlarmService;
import br.com.eneeyes.main.service.views.QueueSigmaViewService;
import br.com.eneeyes.main.service.ws.EventoRecebido;
import br.com.eneeyes.main.service.ws.ReceptorEventosWebService;
import br.com.eneeyes.main.service.ws.ReceptorEventosWebService_Service;

@Component
public class processSigmaService {
	
	@Autowired
	QueueSigmaViewService service;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	@Autowired
	SiteService siteService;
	
	protected final String timestampFormat = "dd/MM/yyyy às HH:mm:ss";
	
	private Log log = LogFactory.getLog(getClass());
	
	private ReceptorEventosWebService_Service service1;
	ReceptorEventosWebService port1;

	private List<QueueSigmaView> sigmaLista;

	@Scheduled(fixedDelay = 60000)
	public void schedule()  {
		log.info(this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2") + ": Sigma Service - Start Automatico :: " 
				+ new SimpleDateFormat(timestampFormat).format(Calendar.getInstance().getTime()));
							
		sigmaLista = new ArrayList<QueueSigmaView>();
		sigmaLista = service.findAll();
		
		if(sigmaLista == null ||sigmaLista.isEmpty()) return;
		
		service1 = new ReceptorEventosWebService_Service();
		port1 = service1.getReceptorEventosWebServicePort();
		
		for (QueueSigmaView item  : sigmaLista) {
			
			try {
				EventoRecebido eventoRecebido = new EventoRecebido();
				
		        GregorianCalendar c = new GregorianCalendar();
		        c.setTime(item.getLast_Update() );
		        XMLGregorianCalendar eventDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		        
		        eventoRecebido.setAuxiliar(item.getCompany_detector_name());
		        eventoRecebido.setCodigo(item.getAlarm_name());
		        eventoRecebido.setData(eventDate);
		        eventoRecebido.setDescricaoReceptora("Teste da Enesens [LOGA]");
		        eventoRecebido.setEmpresa(new Long(10001));
		        eventoRecebido.setIdCentral("9261");
		        eventoRecebido.setParticao("001");
		        eventoRecebido.setProtocolo((byte) 2);
		        eventoRecebido.setTipoIntegracao((byte) 1);
				
		        port1 = service1.getReceptorEventosWebServicePort();
		        
		        System.out.println("Server said: " + port1.receberEvento(eventoRecebido));
	
				positionAlarmService.updateSigmaStatus(item.getUid(), SigmaStatus.SENDED);
			}
			catch(Exception ex) { 
		
				System.out.println("Erro: " + ex.getMessage());
				positionAlarmService.updateSigmaStatus(item.getUid(), SigmaStatus.ERROR);

			}
		}						
		
	}


}
