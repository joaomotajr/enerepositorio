package br.com.eneeyes.archetype.services;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.result.IdentityResult;

public interface IdentityService {
	public IdentityResult findByLogin(String login);

    public IdentityResult changePassword(String pass1, String pass2);

	public IdentityResult persist(User user);
	
	public IdentityResult save(User user);
	
	public IdentityResult remove(Long userId);
	
	public boolean deveTrocarSenha(User user) throws Exception;

}