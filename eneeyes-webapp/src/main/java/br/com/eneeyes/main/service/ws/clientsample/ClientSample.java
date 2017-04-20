package br.com.eneeyes.main.service.ws.clientsample;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import br.com.eneeyes.main.service.ws.EventoRecebido;
import br.com.eneeyes.main.service.ws.ReceptorEventosWebService;
import br.com.eneeyes.main.service.ws.ReceptorEventosWebService_Service;

public class ClientSample {

	public static void main(String[] args) throws DatatypeConfigurationException {
		
		try {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        ReceptorEventosWebService_Service service1 = new ReceptorEventosWebService_Service();
	        System.out.println("Create Web Service...");
	                
	        	        
	        EventoRecebido eventoRecebido = new EventoRecebido();
	        
	        eventoRecebido.setAuxiliar("DG-01");
	        eventoRecebido.setCodigo("DETECCAO");
	        eventoRecebido.setData(DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-04-19T14:45:00Z"));
	        eventoRecebido.setDescricaoReceptora("Teste da Enesens");
	        eventoRecebido.setEmpresa(new Long(10001));
	        eventoRecebido.setIdCentral("9261");
	        eventoRecebido.setParticao("001");
	        eventoRecebido.setProtocolo((byte) 2);
	        eventoRecebido.setTipoIntegracao((byte) 1);
	        
	        ReceptorEventosWebService port1 = service1.getReceptorEventosWebServicePort();
	        
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.receberEvento(eventoRecebido));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	        	        
	        System.out.println("Call Over!");
	}
}
