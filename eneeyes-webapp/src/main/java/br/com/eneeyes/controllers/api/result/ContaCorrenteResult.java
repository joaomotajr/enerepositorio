package br.com.eneeyes.controllers.api.result;

import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.contaCorrente.ContaCorrenteDto;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;

@XmlRootElement
public class ContaCorrenteResult extends ResultBase<DetalheVendaDto> {
    
	ContaCorrenteDto value;
	Set<ContaCorrenteDto> listValue;

    public ContaCorrenteResult() {
    }

    public ContaCorrenteResult(ContaCorrenteDto value) {
        this.value = value;
    }

    @XmlAttribute
    public ContaCorrenteDto getValue() {
        return value;
    }

    public void setValue(ContaCorrenteDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public Set<ContaCorrenteDto> getListValue() {
        return listValue;
    }

    public void setListValue(Set<ContaCorrenteDto> listValue) {
        this.listValue = listValue;
    }
}
