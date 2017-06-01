package br.com.eneeyes.archetype.web.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import br.com.eneeyes.archetype.utils.CsrfHeaderFilter;
import br.com.eneeyes.archetype.web.config.EnvDevelopment;
import br.com.eneeyes.archetype.web.config.EnvDevelopmentForward;
import br.com.eneeyes.archetype.web.config.EnvHomologation;
import br.com.eneeyes.archetype.web.config.EnvProduction;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnvDevelopment
@EnvDevelopmentForward
@EnvHomologation
@EnvProduction
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    public void registerGlobalAuthentication(
            AuthenticationManagerBuilder auth, AuthenticationManager securityManager) throws Exception {
        auth
        	.parentAuthenticationManager(securityManager);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler
				.setPermissionEvaluator(new SecurityPermissionEvaluator());
		
	    http
	        .authorizeRequests()
	        	.antMatchers("/security*/**").access("hasRole('ACTIVE') or hasRole('TEMPORARY')")
	        	.expressionHandler(expressionHandler)
	            .and()
	        .formLogin()
	        	.loginPage("/")
	        	.loginProcessingUrl("/login")
	            .failureUrl("/security/login/error")
	            .defaultSuccessUrl("/security/profile")
	        .and()
	        	.logout()
	        		.deleteCookies("JSESSIONID")
	        .and()
	        	.rememberMe()
            .and()
                .httpBasic();
	    
	    http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository());
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public TextEncryptor textEncryptor() {
            return Encryptors.noOpText();
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
	  repository.setHeaderName("X-XSRF-TOKEN");
	  return repository;
	}
}
