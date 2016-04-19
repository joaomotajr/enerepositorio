package br.com.eneeyes.archetype.web.config.auth;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;

import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.service.identity.IdentityService;

@Configuration
@PropertySource("classpath:spring/social.properties")
public class SocialConfig {

	@Value("${facebook.clientId}")
	private String facebookClientId;

	@Value("${facebook.clientSecret}")
	private String facebookClientSecret;

	@Inject
	private DataSource dataSource;

	/**
	 * When a new provider is added to the app, register its
	 * {@link org.springframework.social.connect.ConnectionFactory} here.
	 *
	 * @see org.springframework.social.facebook.connect.FacebookConnectionFactory
	 */
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(
				facebookClientId, facebookClientSecret));
		return registry;
	}

	/**
	 * Singleton data access object providing access to connections across all
	 * users.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository(IdentityService ids) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
				dataSource, connectionFactoryLocator(), Encryptors.noOpText());
		repository.setConnectionSignUp(new SimpleConnectionSignUp(ids));
		return repository;
	}

	/**
	 * Request-scoped data access object providing access to the current user's
	 * connections.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(IdentityService ids) {
		User user = SecurityContext.getCurrentUser();
		return usersConnectionRepository(ids).createConnectionRepository(
				user.getId().toString());
	}

	/**
	 * A proxy to a request-scoped object representing the current user's
	 * primary Facebook account.
	 *
	 * @throws org.springframework.social.connect.NotConnectedException
	 *             if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook(IdentityService ids) {
		return connectionRepository(ids).getPrimaryConnection(Facebook.class)
				.getApi();
	}

	/**
	 * A proxy to a request-scoped object representing the current user's
	 * primary Facebook account.
	 *
	 * @throws org.springframework.social.connect.NotConnectedException
	 *             if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Twitter twitter(IdentityService ids) {
		return connectionRepository(ids).getPrimaryConnection(Twitter.class)
				.getApi();
	}

	@Bean
	public ProviderSignInController signInController(
			ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository usersConnectionRepository,
			IdentityService ids) {
		ProviderSignInController signInController = new ProviderSignInController(
				connectionFactoryLocator, usersConnectionRepository,
				new SimpleSignInAdapter(ids));
		signInController.setPostSignInUrl("/");
		return signInController;
	}
}