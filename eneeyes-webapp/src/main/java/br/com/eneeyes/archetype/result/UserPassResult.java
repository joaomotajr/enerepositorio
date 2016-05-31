package br.com.eneeyes.archetype.result;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.dto.user.UserPassDto;
import br.com.eneeyes.archetype.web.result.ResultBase;

@XmlRootElement
public class UserPassResult extends ResultBase<UserPassDto> {
	
	private static final long serialVersionUID = 1L;

	public UserPassResult() {
	}
	
}
