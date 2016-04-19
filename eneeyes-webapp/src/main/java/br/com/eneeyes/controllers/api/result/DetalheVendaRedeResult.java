package br.com.eneeyes.controllers.api.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;
import br.com.eneeyes.controllers.api.dto.vendas.VendaManualDto;

@XmlRootElement
public class DetalheVendaRedeResult extends ResultBase<DetalheVendaDto> {
    
	DetalheVendaDto value;
	VendaManualDto valueManual;
	List<DetalheVendaDto> listValue;

    public DetalheVendaRedeResult() {
    }

    public DetalheVendaRedeResult(DetalheVendaDto value) {
        this.value = value;
    }

    @XmlAttribute
    public DetalheVendaDto getValue() {
        return value;
    }

    public void setValue(DetalheVendaDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public List<DetalheVendaDto> getListValue() {
        return listValue;
    }

    public void setListValue(List<DetalheVendaDto> listValue) {
        this.listValue = listValue;
    }

    @XmlAttribute
	public VendaManualDto getValueManual() {
		return valueManual;
	}

	public void setValueManual(VendaManualDto valueManual) {
		this.valueManual = valueManual;
	}
}
