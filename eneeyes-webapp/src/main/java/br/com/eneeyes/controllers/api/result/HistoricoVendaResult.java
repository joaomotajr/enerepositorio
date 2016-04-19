package br.com.eneeyes.controllers.api.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaDto;

@XmlRootElement
public class HistoricoVendaResult extends ResultBase<HistoricoVendaDto> {
    
	HistoricoVendaDto value;
	List<HistoricoVendaDto> listValue;
	private List<String> listaDatasIntegracao;

    public HistoricoVendaResult() {
    }

    public HistoricoVendaResult(HistoricoVendaDto value) {
        this.value = value;
    }

    @XmlAttribute
    public HistoricoVendaDto getValue() {
        return value;
    }

    public void setValue(HistoricoVendaDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public List<HistoricoVendaDto> getListValue() {
        return listValue;
    }

    public void setListValue(List<HistoricoVendaDto> listValue) {
        this.listValue = listValue;
    }

    @XmlAttribute
	public List<String> getListaDatasIntegracao() {
		return listaDatasIntegracao;
	}

	public void setListaDatasIntegracao(List<String> listaDatasIntegracao) {
		this.listaDatasIntegracao = listaDatasIntegracao;
	}
}