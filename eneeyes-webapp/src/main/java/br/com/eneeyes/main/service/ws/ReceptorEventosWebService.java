package br.com.eneeyes.main.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.4.redhat-1
 * 2017-04-19T13:51:21.572-03:00
 * Generated source version: 3.1.4.redhat-1
 * 
 */
@WebService(targetNamespace = "http://webServices.sigmaWebServices.segware.com.br/", name = "ReceptorEventosWebService")
@XmlSeeAlso({ObjectFactory.class})
public interface ReceptorEventosWebService {

    @WebMethod
    @RequestWrapper(localName = "receberEvento", targetNamespace = "http://webServices.sigmaWebServices.segware.com.br/", className = "br.com.eneeyes.main.service.ws.ReceberEvento")
    @ResponseWrapper(localName = "receberEventoResponse", targetNamespace = "http://webServices.sigmaWebServices.segware.com.br/", className = "br.com.eneeyes.main.service.ws.ReceberEventoResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String receberEvento(
        @WebParam(name = "evento", targetNamespace = "")
        br.com.eneeyes.main.service.ws.EventoRecebido evento
    );
}