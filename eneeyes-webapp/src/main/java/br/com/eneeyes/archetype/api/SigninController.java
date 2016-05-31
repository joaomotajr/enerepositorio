package br.com.eneeyes.archetype.api;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.dto.SigninDto;
import br.com.eneeyes.archetype.result.SigninResult;
import br.com.eneeyes.archetype.web.config.auth.SecurityManager;
import br.com.eneeyes.archetype.web.result.ResultErrorMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;

/**
 * Created by Alan on 17/05/2014.
 */
@RestController
public class SigninController {

    Log log = LogFactory.getLog(getClass());

    @Inject
    SecurityManager securityManager;

    @RequestMapping(value="/api/signin", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public SigninResult signin(@RequestBody SigninDto signinDto) {
        SigninResult signinResult = new SigninResult(signinDto);
        try {
            Authentication auth = securityManager.authenticate(new UsernamePasswordAuthenticationToken(signinDto.getLogin(), signinDto.getCredential()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            signinResult.setResultType(ResultMessageType.SUCCESS);
        } catch (AuthenticationException e) {
            signinResult.setResultType(ResultMessageType.ERROR);
            signinResult.getMessages().add(new ResultErrorMessage(e.getMessage()));
        }
        return signinResult;
    }
    
    @RequestMapping(value="/security/signin/{login}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void signin() {
    }
    
}
