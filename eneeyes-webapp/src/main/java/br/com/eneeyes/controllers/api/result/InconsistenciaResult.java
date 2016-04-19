package br.com.eneeyes.controllers.api.result;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.inconsistencia.InconsistenciaDto;

@XmlRootElement
public class InconsistenciaResult extends ResultBase<InconsistenciaDto> {
    
	private InconsistenciaDto value;
	private List<InconsistenciaDto> listValue;

    public InconsistenciaResult() {
    }

    public InconsistenciaResult(InconsistenciaDto value) {
        this.value = value;
    }

    @XmlAttribute
    public InconsistenciaDto getValue() {
        return value;
    }
    
	public void setValue(InconsistenciaDto value) {
        this.value = value;
    }
    
    @XmlAttribute
    public List<InconsistenciaDto> getListValue() {
        return listValue;
    }

    public void setListValue(List<InconsistenciaDto> listValue) {
        this.listValue = listValue;
    }
}