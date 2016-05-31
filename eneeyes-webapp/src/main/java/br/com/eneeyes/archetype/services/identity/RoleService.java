package br.com.eneeyes.archetype.services.identity;

import br.com.eneeyes.archetype.result.RoleResult;

public interface RoleService {
	public RoleResult findByValue(String value);
}
