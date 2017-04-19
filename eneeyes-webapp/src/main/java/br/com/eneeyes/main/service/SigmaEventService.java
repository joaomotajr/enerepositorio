package br.com.eneeyes.main.service;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import br.com.eneeyes.main.service.ws.EventoRecebido;
import br.com.eneeyes.main.service.ws.ReceptorEventosWebService;
import br.com.eneeyes.main.service.ws.ReceptorEventosWebService_Service;


@Service
public class SigmaEventService {
	
	public String auxiliar;
    public Integer cdCliente;
    public String codigo;
    public XMLGregorianCalendar data;
    public String descricaoReceptora;
    public XMLGregorianCalendar dtUltimaLocalizacao;
    public Long empresa;
    public String idCentral;
    public String identificadorCliente;
    public String nmFraseEvento;
    public String nuLatitude;
    public String nuLongitude;
    public String particao;
    public byte protocolo;
    public byte tipoIntegracao;
    
    private ReceptorEventosWebService_Service service; 
    ReceptorEventosWebService port;
    
    public SigmaEventService() {
    	
    }

    public String EventReceive() throws DatatypeConfigurationException {
    	
    	service = new ReceptorEventosWebService_Service(); 
    	EventoRecebido eventoRecebido = new EventoRecebido();

//        GregorianCalendar c = new GregorianCalendar();
//        c.setTime(new Date());
//        XMLGregorianCalendar eventDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        
        eventoRecebido.setAuxiliar(this.getAuxiliar());
        eventoRecebido.setCodigo(this.getCodigo());
        eventoRecebido.setData(this.getData());
        eventoRecebido.setDescricaoReceptora(this.getDescricaoReceptora());
        eventoRecebido.setEmpresa(this.getEmpresa() );
        eventoRecebido.setIdCentral(this.getIdCentral());
        eventoRecebido.setParticao("001");
        eventoRecebido.setProtocolo((byte) 2);
        eventoRecebido.setTipoIntegracao((byte) 1);
                    
        ReceptorEventosWebService port1 = service.getReceptorEventosWebServicePort();
                
        return port1.receberEvento(eventoRecebido);
    }
    

	public final String getAuxiliar() {
		return auxiliar;
	}
	public final void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}
	public final Integer getCdCliente() {
		return cdCliente;
	}
	public final void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}
	public final String getCodigo() {
		return codigo;
	}
	public final void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public final XMLGregorianCalendar getData() {
		return data;
	}
	public final void setData(XMLGregorianCalendar data) {
		this.data = data;
	}
	public final String getDescricaoReceptora() {
		return descricaoReceptora;
	}
	public final void setDescricaoReceptora(String descricaoReceptora) {
		this.descricaoReceptora = descricaoReceptora;
	}
	public final XMLGregorianCalendar getDtUltimaLocalizacao() {
		return dtUltimaLocalizacao;
	}
	public final void setDtUltimaLocalizacao(XMLGregorianCalendar dtUltimaLocalizacao) {
		this.dtUltimaLocalizacao = dtUltimaLocalizacao;
	}
	public final Long getEmpresa() {
		return empresa;
	}
	public final void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	public final String getIdCentral() {
		return idCentral;
	}
	public final void setIdCentral(String idCentral) {
		this.idCentral = idCentral;
	}
	public final String getIdentificadorCliente() {
		return identificadorCliente;
	}
	public final void setIdentificadorCliente(String identificadorCliente) {
		this.identificadorCliente = identificadorCliente;
	}
	public final String getNmFraseEvento() {
		return nmFraseEvento;
	}
	public final void setNmFraseEvento(String nmFraseEvento) {
		this.nmFraseEvento = nmFraseEvento;
	}
	public final String getNuLatitude() {
		return nuLatitude;
	}
	public final void setNuLatitude(String nuLatitude) {
		this.nuLatitude = nuLatitude;
	}
	public final String getNuLongitude() {
		return nuLongitude;
	}
	public final void setNuLongitude(String nuLongitude) {
		this.nuLongitude = nuLongitude;
	}
	public final String getParticao() {
		return particao;
	}
	public final void setParticao(String particao) {
		this.particao = particao;
	}
	public final byte getProtocolo() {
		return protocolo;
	}
	public final void setProtocolo(byte protocolo) {
		this.protocolo = protocolo;
	}
	public final byte getTipoIntegracao() {
		return tipoIntegracao;
	}
	public final void setTipoIntegracao(byte tipoIntegracao) {
		this.tipoIntegracao = tipoIntegracao;
	}
	
}
