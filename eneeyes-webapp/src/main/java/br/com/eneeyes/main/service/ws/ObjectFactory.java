
package br.com.eneeyes.main.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.gov.caixa.sharepoint.service.scheduller.mrbb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReceberEvento_QNAME = new QName("http://webServices.sigmaWebServices.segware.com.br/", "receberEvento");
    private final static QName _ReceberEventoResponse_QNAME = new QName("http://webServices.sigmaWebServices.segware.com.br/", "receberEventoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.gov.caixa.sharepoint.service.scheduller.mrbb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReceberEvento }
     * 
     */
    public ReceberEvento createReceberEvento() {
        return new ReceberEvento();
    }

    /**
     * Create an instance of {@link ReceberEventoResponse }
     * 
     */
    public ReceberEventoResponse createReceberEventoResponse() {
        return new ReceberEventoResponse();
    }

    /**
     * Create an instance of {@link EventoRecebido }
     * 
     */
    public EventoRecebido createEventoRecebido() {
        return new EventoRecebido();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceberEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.sigmaWebServices.segware.com.br/", name = "receberEvento")
    public JAXBElement<ReceberEvento> createReceberEvento(ReceberEvento value) {
        return new JAXBElement<ReceberEvento>(_ReceberEvento_QNAME, ReceberEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceberEventoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices.sigmaWebServices.segware.com.br/", name = "receberEventoResponse")
    public JAXBElement<ReceberEventoResponse> createReceberEventoResponse(ReceberEventoResponse value) {
        return new JAXBElement<ReceberEventoResponse>(_ReceberEventoResponse_QNAME, ReceberEventoResponse.class, null, value);
    }

}
