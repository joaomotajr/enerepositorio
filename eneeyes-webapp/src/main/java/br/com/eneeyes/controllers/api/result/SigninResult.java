package br.com.eneeyes.controllers.api.result;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.SigninDto;

/**
 * Created by Alan on 17/05/2014.
 */
@XmlRootElement
public class SigninResult extends ResultBase<SigninDto> {

	private static final long serialVersionUID = 1L;
	
	SigninDto value;

    public SigninResult() {
    }
    
    public SigninResult(SigninDto value) {
        this.value = value;
    }

    @XmlAttribute
    public SigninDto getValue() {
        return value;
    }

    public void setValue(SigninDto value) {
        this.value = value;
    }
    
}
