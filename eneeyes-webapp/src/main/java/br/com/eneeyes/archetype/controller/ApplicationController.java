package br.com.eneeyes.archetype.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.services.identity.IdentityService;

@Controller
@RequestMapping("/")
public class ApplicationController {

    private Log log = LogFactory.getLog(getClass());

    @Inject
    private br.com.eneeyes.archetype.web.config.auth.SecurityManager securityManager;

    @Inject
    private IdentityService identityService;
    
    private String TIPO_USUARIO = "tipoUsuario";
    private String ID_USUARIO = "idUsuario";
    private String CNPJ_RAIZ_USUARIO = "cnpjRaizUsuario";
    
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(ModelAndView model, HttpSession session) {
        
    	String viewName = "index";
        
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = (auth.getPrincipal() instanceof User);

        if (isAuthenticated) {
        	boolean  userAdmin = false;
        	User user = (User) auth.getPrincipal();
        	
//    		for(Role role : user.getRoles()) {
//    			if(role.getId() != null) {
//	    			userAdmin = role.getId() == RoleType.ADMINISTRATOR.getId();
//	    			session.setAttribute(TIPO_USUARIO, RoleType.USER.getName(role.getId()));
//	    			break;
//    			}
//    		}
    		
    		session.setAttribute(ID_USUARIO, user.getId());
    		session.setAttribute(CNPJ_RAIZ_USUARIO, user.getCnpj());
        	if(userAdmin) {
        		viewName = "redirect:/security/index-user-admin.html";
        		log.info("Usuario administrador: Redirecionando para o modulo de gerenciamento da aplicacao...");
        	} else {
        		viewName = "redirect:/security/index-user.html";	
        	}        	
        } else {
        	session.setAttribute(TIPO_USUARIO, "");
        	session.setAttribute(ID_USUARIO, "");
        	session.setAttribute(CNPJ_RAIZ_USUARIO, "");
        }

        return view(viewName, model, session);
    }
    
    @RequestMapping(value="/inscricao.html")
    public ModelAndView signup(ModelAndView model, HttpSession session){
    	model.addObject("layoutContext", "default");
    	return view("cadastro", model, session);
    }

    @RequestMapping(value="/inscricao.html", method = RequestMethod.POST)
    public ModelAndView signup(ModelAndView model, @RequestParam String email, HttpSession session){
    	model.addObject("layoutContext", "default");
        model.addObject("inscricao_email", email);
    	return view("cadastro", model, session);
    }
    
    @RequestMapping(value="/forgot-password/{refreshToken}")
    public ModelAndView recuperarSenhaAlteracaoIndevida(ModelAndView model, HttpSession session, @PathVariable String refreshToken) {
        UserResult userResult;
		try {
			userResult = identityService.findByRefreshToken(refreshToken);
	        model.addObject("refreshToken", refreshToken);
	        model.addObject("hasError", userResult.hasError());

	        if (userResult.hasError()) {
	            model.addObject("errorMessage", userResult.getErrorMessages().get(0).getMessage());
	        }
		} catch (Exception e) {
			log.error(e);
		}
        return view("recuperar-senha-alteracao-indevida", model, session);
    }

    @RequestMapping(value = "/api/forgot-password/{refreshToken}", method = RequestMethod.POST)
    @ResponseBody
    public UserResult recuperarSenhaAlteracaoIndevia(@RequestBody Map<String,String> passwords, @PathVariable String refreshToken) {
        UserResult userResult;
		try {
			userResult = identityService.findByRefreshToken(refreshToken);
	        if (userResult.hasSuccess()) {
	            Authentication auth = securityManager.createAuth(userResult.getUser());
	            SecurityContextHolder.getContext().setAuthentication(auth);
	            userResult = identityService.changePassword(passwords.get("pass1"), passwords.get("pass2"));
	            if (userResult.hasError()) {
	                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	            }
	        }
		} catch (Exception e) {
			userResult = null;
			log.error(e);
		}
        return userResult;
    }

    @RequestMapping(value="/{viewName}.html")
    public ModelAndView view(@PathVariable String viewName, ModelAndView model, HttpSession session) {
        model.setViewName(viewName);
        return model;
    }
}