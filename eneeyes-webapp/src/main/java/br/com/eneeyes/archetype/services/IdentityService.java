package br.com.eneeyes.archetype.services;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.result.UserResult;

public interface IdentityService {
	public UserResult findByLogin(String login);
	
	public UserResult findUserConnection(String providerId, String providerUserId) throws Exception;
	
//	public UserCollectionResult findUsersConnections(String providerId, Set<String> ids) throws Exception;

    public UserResult changePassword(String pass1, String pass2);

	public UserResult persist(User user);
	
	public UserResult save(User user);
	
	public UserResult remove(Long userId);
	
//	public UserCollectionResult pagination();
//	
//	public UserCollectionResult pagination(Criteria<User> criteria);
//	
//	public UserCollectionResult pagination(Integer page, Integer offset);
//	
//	public UserCollectionResult pagination(Integer page, Integer offset, Criteria<User> criteria);
	
	public boolean deveTrocarSenha(User user) throws Exception;

}