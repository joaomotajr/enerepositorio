package br.com.eneeyes.controllers.api.result;

import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.pagamentos.ResumoOperacaoCieloDto;
import br.com.eneeyes.controllers.api.dto.vendas.DetalheVendaDto;

@XmlRootElement
public class PagamentosResult extends ResultBase<DetalheVendaDto> {
    
	ResumoOperacaoCieloDto value;
	Set<ResumoOperacaoCieloDto> listValue;

    public PagamentosResult() {
    }

    public PagamentosResult(ResumoOperacaoCieloDto value) {
        this.value = value;
    }

    @XmlAttribute
    public ResumoOperacaoCieloDto getValue() {
        return value;
    }

    public void setValue(ResumoOperacaoCieloDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public Set<ResumoOperacaoCieloDto> getListValue() {
        return listValue;
    }

    public void setListValue(Set<ResumoOperacaoCieloDto> listValue) {
        this.listValue = listValue;
    }
}
