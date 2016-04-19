package br.com.eneeyes.controllers.api.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.TipoJustificativaDto;

@XmlRootElement
public class TipoJustificativaResult extends ResultBase<TipoJustificativaDto> {
    
	private List<TipoJustificativaDto> listValue;

    public TipoJustificativaResult() {
    }

    @XmlAttribute
    public List<TipoJustificativaDto> getListValue() {
        return listValue;
    }

    public void setListValue(List<TipoJustificativaDto> listValue) {
        this.listValue = listValue;
    }
}