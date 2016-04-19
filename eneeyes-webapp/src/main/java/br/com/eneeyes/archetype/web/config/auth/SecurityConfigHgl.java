package br.com.eneeyes.archetype.web.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/*@Configuration
@EnableWebSecurity
@EnvHomologation*/
public class SecurityConfigHgl extends WebSecurityConfigurerAdapter {

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
//	        	.antMatchers("/security*/**").access("hasRole('ACTIVE') or hasRole('TEMPORARY')")
	        	.antMatchers("/**").authenticated()
	        	.expressionHandler(expressionHandler)
	            .and()
            .httpBasic().and()
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
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public TextEncryptor textEncryptor() {
            return Encryptors.noOpText();
    }
}
