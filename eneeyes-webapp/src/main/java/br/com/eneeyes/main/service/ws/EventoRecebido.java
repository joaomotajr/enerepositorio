
package br.com.eneeyes.main.service.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de eventoRecebido complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="eventoRecebido"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="auxiliar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cdCliente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descricaoReceptora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dtUltimaLocalizacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="empresa" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idCentral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificadorCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nmFraseEvento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nuLatitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nuLongitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="particao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="protocolo" type="{http://www.w3.org/2001/XMLSchema}byte"/&gt;
 *         &lt;element name="tipoIntegracao" type="{http://www.w3.org/2001/XMLSchema}byte"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventoRecebido", propOrder = {
    "auxiliar",
    "cdCliente",
    "codigo",
    "data",
    "descricaoReceptora",
    "dtUltimaLocalizacao",
    "empresa",
    "idCentral",
    "identificadorCliente",
    "nmFraseEvento",
    "nuLatitude",
    "nuLongitude",
    "particao",
    "protocolo",
    "tipoIntegracao"
})
public class EventoRecebido {

    protected String auxiliar;
    protected Integer cdCliente;
    protected String codigo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    protected String descricaoReceptora;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtUltimaLocalizacao;
    protected Long empresa;
    protected String idCentral;
    protected String identificadorCliente;
    protected String nmFraseEvento;
    protected String nuLatitude;
    protected String nuLongitude;
    protected String particao;
    protected byte protocolo;
    protected byte tipoIntegracao;

    /**
     * Obt�m o valor da propriedade auxiliar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuxiliar() {
        return auxiliar;
    }

    /**
     * Define o valor da propriedade auxiliar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuxiliar(String value) {
        this.auxiliar = value;
    }

    /**
     * Obt�m o valor da propriedade cdCliente.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCdCliente() {
        return cdCliente;
    }

    /**
     * Define o valor da propriedade cdCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCdCliente(Integer value) {
        this.cdCliente = value;
    }

    /**
     * Obt�m o valor da propriedade codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define o valor da propriedade codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obt�m o valor da propriedade data.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Define o valor da propriedade data.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Obt�m o valor da propriedade descricaoReceptora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoReceptora() {
        return descricaoReceptora;
    }

    /**
     * Define o valor da propriedade descricaoReceptora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoReceptora(String value) {
        this.descricaoReceptora = value;
    }

    /**
     * Obt�m o valor da propriedade dtUltimaLocalizacao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDtUltimaLocalizacao() {
        return dtUltimaLocalizacao;
    }

    /**
     * Define o valor da propriedade dtUltimaLocalizacao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDtUltimaLocalizacao(XMLGregorianCalendar value) {
        this.dtUltimaLocalizacao = value;
    }

    /**
     * Obt�m o valor da propriedade empresa.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEmpresa() {
        return empresa;
    }

    /**
     * Define o valor da propriedade empresa.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEmpresa(Long value) {
        this.empresa = value;
    }

    /**
     * Obt�m o valor da propriedade idCentral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCentral() {
        return idCentral;
    }

    /**
     * Define o valor da propriedade idCentral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCentral(String value) {
        this.idCentral = value;
    }

    /**
     * Obt�m o valor da propriedade identificadorCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorCliente() {
        return identificadorCliente;
    }

    /**
     * Define o valor da propriedade identificadorCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorCliente(String value) {
        this.identificadorCliente = value;
    }

    /**
     * Obt�m o valor da propriedade nmFraseEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmFraseEvento() {
        return nmFraseEvento;
    }

    /**
     * Define o valor da propriedade nmFraseEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmFraseEvento(String value) {
        this.nmFraseEvento = value;
    }

    /**
     * Obt�m o valor da propriedade nuLatitude.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuLatitude() {
        return nuLatitude;
    }

    /**
     * Define o valor da propriedade nuLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuLatitude(String value) {
        this.nuLatitude = value;
    }

    /**
     * Obt�m o valor da propriedade nuLongitude.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuLongitude() {
        return nuLongitude;
    }

    /**
     * Define o valor da propriedade nuLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuLongitude(String value) {
        this.nuLongitude = value;
    }

    /**
     * Obt�m o valor da propriedade particao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticao() {
        return particao;
    }

    /**
     * Define o valor da propriedade particao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticao(String value) {
        this.particao = value;
    }

    /**
     * Obt�m o valor da propriedade protocolo.
     * 
     */
    public byte getProtocolo() {
        return protocolo;
    }

    /**
     * Define o valor da propriedade protocolo.
     * 
     */
    public void setProtocolo(byte value) {
        this.protocolo = value;
    }

    /**
     * Obt�m o valor da propriedade tipoIntegracao.
     * 
     */
    public byte getTipoIntegracao() {
        return tipoIntegracao;
    }

    /**
     * Define o valor da propriedade tipoIntegracao.
     * 
     */
    public void setTipoIntegracao(byte value) {
        this.tipoIntegracao = value;
    }

}
