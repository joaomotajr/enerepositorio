package br.com.eneeyes.archetype.service.identity;

import br.com.eneeyes.archetype.model.acl.result.RoleResult;

public interface RoleService {
	public RoleResult findByValue(String value);
}
