
package br.com.eneeyes.main.service.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de receberEvento complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="receberEvento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="evento" type="{http://webServices.sigmaWebServices.segware.com.br/}eventoRecebido" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receberEvento", propOrder = {
    "evento"
})
public class ReceberEvento {

    protected EventoRecebido evento;

    /**
     * Obt�m o valor da propriedade evento.
     * 
     * @return
     *     possible object is
     *     {@link EventoRecebido }
     *     
     */
    public EventoRecebido getEvento() {
        return evento;
    }

    /**
     * Define o valor da propriedade evento.
     * 
     * @param value
     *     allowed object is
     *     {@link EventoRecebido }
     *     
     */
    public void setEvento(EventoRecebido value) {
        this.evento = value;
    }

}
