package br.com.eneeyes.archetype.model.acl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.login = ?1")
	public User findByLogin(String login);
	
	public User findById(Long id);
	
//	@Query("select u from User u where u.cnpj = ?1")
//	public User findByCnpj(String cnpj) throws Exception;
	
//	public User findByLoginAndProviderId(String login, String providerId) throws Exception;
	
//	public List<User> findByProviderIdInAndProviderUserIdIn(Set<String> providerIds, Set<String> providerUsersIds) throws Exception;
	
//	public User findByLoginAndProviderIdAndProviderUserId(String login, String providerId, String providerUserId) throws Exception;
	
//	public User findByProviderIdAndProviderUserId(String providerId, String providerUserId) throws Exception;
	
//	public List<User> findByProviderIdAndProviderUserIdIn(String providerId, Set<String> providerUsersIds) throws Exception;

	public User findByRefreshToken(String refreshToken) throws Exception;
}