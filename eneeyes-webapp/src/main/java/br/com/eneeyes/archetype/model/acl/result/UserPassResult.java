package br.com.eneeyes.archetype.model.acl.result;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.controllers.api.dto.user.UserPassDto;

@XmlRootElement
public class UserPassResult extends ResultBase<UserPassDto> {
	
	private static final long serialVersionUID = 1L;

	public UserPassResult() {
	}
	
}
