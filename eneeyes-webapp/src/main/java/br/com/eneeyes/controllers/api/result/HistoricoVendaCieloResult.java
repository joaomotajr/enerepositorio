package br.com.eneeyes.controllers.api.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaCieloDto;
import br.com.eneeyes.controllers.api.dto.vendas.HistoricoVendaDto;

@XmlRootElement
public class HistoricoVendaCieloResult extends ResultBase<HistoricoVendaDto> {
    
	HistoricoVendaCieloDto value;
	List<HistoricoVendaCieloDto> listValue;

    public HistoricoVendaCieloResult() {
    }

    public HistoricoVendaCieloResult(HistoricoVendaCieloDto value) {
        this.value = value;
    }

    @XmlAttribute
    public HistoricoVendaCieloDto getValue() {
        return value;
    }

    public void setValue(HistoricoVendaCieloDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public List<HistoricoVendaCieloDto> getListValue() {
        return listValue;
    }

    public void setListValue(List<HistoricoVendaCieloDto> listValue) {
        this.listValue = listValue;
    }
}