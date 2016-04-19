package br.com.eneeyes.archetype.web.config.auth.signin;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.eneeyes.archetype.model.acl.User;

public class SigninUtils {

    /**
     * Programmatically signs in the user with the given the user ID.
     */
    public static void signin(String userId) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
    }


    public static User principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
