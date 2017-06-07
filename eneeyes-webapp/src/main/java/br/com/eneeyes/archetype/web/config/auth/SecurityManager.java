package br.com.eneeyes.archetype.web.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.model.UserStatus;
import br.com.eneeyes.archetype.repository.UserRepository;
import br.com.eneeyes.archetype.utils.MessageDigester;

@Component
public class SecurityManager implements AuthenticationManager {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserRepository repository;
	
	private String SIGNIN_ERROR = "signin.error";
	private String SIGNIN_INACTIVE = "signin.inactive";
	private String SIGNIN_NEW = "signin.new";

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException  {
        return authenticate(authentication, false);
    }

	public Authentication authenticate(Authentication authentication, boolean noHashCheck)
			throws AuthenticationException {
		
		log.info(authentication.getName());
		log.info(authentication.getCredentials());
		
		// Valida existencia de login
		String login = authentication.getName();
		if(login == null || login.length() < 1) {
            log.info("Bad Credentials!");
			throw new BadCredentialsException(SIGNIN_ERROR);
		}
		
		// Valida usuario inativo
		final User user = repository.findByLogin(login);
        if(user != null && user.getStatus().equals(UserStatus.INACTIVE)) {
        	log.error("Tentativa de login invalido: " + login);
        	throw new BadCredentialsException(SIGNIN_INACTIVE);
        }
		
        // Valida senha
		try {
			String hash = MessageDigester.digestSha1(authentication.getCredentials().toString());

			if (!noHashCheck) {
            	// Senha errada
                if(user == null || !user.getHash().equals(hash)) {
                	log.error("Tentativa de login com senha errada");
                    throw new BadCredentialsException(SIGNIN_ERROR);
                }
                
	             // Senha Deve ser trocada
	            if(user != null && user.getReset() != null  && user.getReset() ) {
	            	log.error("Usuario Deve Trocar Senha: " + user.getLogin());
	            	throw new BadCredentialsException(SIGNIN_NEW+"#"+user.getId()+"#"+user.getDisplayName());
	            }
                
            }
		} catch (Throwable t) {
			throw new BadCredentialsException(t.getMessage());
		}
		
		return createAuth(user);
	}

    public Authentication createAuth(final User user) {
        List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();

        grants.add(new GrantedAuthority() {

            private static final long serialVersionUID = 4490745121463633506L;

            public String getAuthority() {
                return user.getStatus().toString();
            }
        });

        if(UserStatus.ACTIVE.equals(user.getStatus())) {	
            for(Role role : user.getRoles()) {
                final String roleName = role.getName();
                
                grants.add(new GrantedAuthority() {

                    private static final long serialVersionUID = 4490745121463633506L;

                    public String getAuthority() {
                        return roleName;
                    }
                });
            }
        }

        user.setDisplayName(user.getDisplayName().split(" ")[0]);

        return new RememberMeAuthenticationToken(user.getLogin(), user, grants);
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = (auth.getPrincipal() instanceof User);
        return isAuthenticated;
    }

    public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if((auth.getPrincipal() instanceof User) == false) {
            return new User();
        }

        return (User) auth.getPrincipal();
    }
}
