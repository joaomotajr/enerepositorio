package br.com.eneeyes.archetype.web.config.auth;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import br.com.eneeyes.archetype.model.acl.Permission;
import br.com.eneeyes.archetype.model.acl.Role;
import br.com.eneeyes.archetype.model.acl.User;

public class SecurityPermissionEvaluator implements PermissionEvaluator {

	private boolean hasPermission(Authentication authentication, Object permission) {
		boolean hasPermission = false;
		User user = ((User)authentication.getPrincipal());
		for(Role role : user.getRoles()) {
			for(Permission rolePermission : role.getPermissions()) {
				if(rolePermission.getValue().equals(permission.toString())) {
					hasPermission = true;
				}
			}
		}
		return hasPermission;
	}
	
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		return hasPermission(authentication, permission);
	}

	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		return hasPermission(authentication, permission);
	}

}
