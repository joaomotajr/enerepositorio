package br.com.eneeyes.archetype.result;

import java.util.List;

import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.web.result.Result;
import br.com.eneeyes.archetype.web.result.ResultMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;

public class RoleResult extends Result<Role> {
	private static final long serialVersionUID = 3174687160439461082L;
	
	private Role role;

	public RoleResult() {
		super();
	}

	public RoleResult(ResultMessageType resultType, Role role) {
		setResultType(resultType);
		setRole(role);
	}
	
	public RoleResult(ResultMessageType resultType, List<ResultMessage> messages) {
		setResultType(resultType);
        setMessages(messages);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
