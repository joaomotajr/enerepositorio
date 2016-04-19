package br.com.eneeyes.archetype.web.config.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.eneeyes.archetype.model.acl.User;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null){
			if((auth.getPrincipal() instanceof User) == true) {
	        	User usuario = (User) auth.getPrincipal();
	        	return usuario.getCnpj();
	        }
		}
		
		return "conciliador"; 
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}
}
