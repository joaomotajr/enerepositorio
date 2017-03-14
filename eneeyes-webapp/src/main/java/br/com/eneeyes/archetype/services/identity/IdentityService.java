package br.com.eneeyes.archetype.services.identity;

import java.util.Set;

import br.com.eneeyes.archetype.model.Criteria;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.result.UserCollectionResult;
import br.com.eneeyes.archetype.result.UserResult;

public interface IdentityService {
	public UserResult findByLogin(String login);
	
//	public UserResult findByLoginAndProviderId(String login, String providerId) throws Exception;
//	
//	public UserResult findByLoginAndProviderIdAndProviderUserId(String login, String providerId, String providerUserId) throws Exception;
//
//	public UserCollectionResult findByProviderIdsAndProviderUsersIds(Set<String> providerIds, Set<String> providerUsersIds) throws Exception;
	
	public UserResult findUserConnection(String providerId, String providerUserId) throws Exception;
	
	public UserCollectionResult findUsersConnections(String providerId, Set<String> ids) throws Exception;

	public UserResult findByRefreshToken(String refreshToken) throws Exception;

	public UserResult recoverPassword(String email1, String email2);

    public UserResult changePassword(String pass1, String pass2);

	public UserResult persist(User user);
	
	public UserResult save(User user);
	
	public UserResult remove(Long userId);
	
	public UserCollectionResult pagination();
	
	public UserCollectionResult pagination(Criteria<User> criteria);
	
	public UserCollectionResult pagination(Integer page, Integer offset);
	
	public UserCollectionResult pagination(Integer page, Integer offset, Criteria<User> criteria);
	
//	public UserResult findByCnpj(String cnpj) throws Exception;
	
	public boolean senhaExpirada(User user) throws Exception;

}