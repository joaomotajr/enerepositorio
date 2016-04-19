package br.com.eneeyes.controllers.api.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.conciliacao.ConciliacaoDto;
import br.com.eneeyes.controllers.api.dto.pagamentos.ComprovanteVendaCieloDto;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;

@XmlRootElement
public class ConciliacaoResult extends ResultBase<ConciliacaoDto> {
    
	private ConciliacaoDto value;
	private List<ConciliacaoDto> listValue;
	private List<DetalheVendaDto> vendas;
	private List<ComprovanteVendaCieloDto> adquirente;

    public ConciliacaoResult() {
    }

    public ConciliacaoResult(ConciliacaoDto value) {
        this.value = value;
    }

    @XmlAttribute
    public ConciliacaoDto getValue() {
        return value;
    }
    
    public List<DetalheVendaDto> getVendas() {
		return vendas;
	}

	public void setVendas(List<DetalheVendaDto> vendas) {
		this.vendas = vendas;
	}

	public List<ComprovanteVendaCieloDto> getAdquirente() {
		return adquirente;
	}

	public void setAdquirente(List<ComprovanteVendaCieloDto> adquirente) {
		this.adquirente = adquirente;
	}

	public void setValue(ConciliacaoDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public List<ConciliacaoDto> getListValue() {
        return listValue;
    }

    public void setListValue(List<ConciliacaoDto> listValue) {
        this.listValue = listValue;
    }
    
    
}
