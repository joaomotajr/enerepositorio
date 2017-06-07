package br.com.eneeyes.archetype.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.model.User;

@Controller
@RequestMapping("/")
public class ApplicationController {
    
    private String TIPO_USUARIO = "tipoUsuario";
    private String ID_USUARIO = "idUsuario";
        
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(ModelAndView model, HttpSession session) {
        
    	String viewName = "index";
        
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = (auth.getPrincipal() instanceof User);

        if (isAuthenticated) {
    
        	User user = (User) auth.getPrincipal();
        	
    		for(Role role : user.getRoles()) {
    			if(role.getId() != null) {
	    			session.setAttribute(TIPO_USUARIO, role.getName());	    			
	    			break;
    			}
    		}
    		
    		session.setAttribute(ID_USUARIO, user.getId());   		

        	viewName = "redirect:/security/index-user.html";	
        	        	
        } else {
        	session.setAttribute(TIPO_USUARIO, "");
        	session.setAttribute(ID_USUARIO, "");        	
        }

        return view(viewName, model, session);
    } 

    @RequestMapping(value="/{viewName}.html")
    public ModelAndView view(@PathVariable String viewName, ModelAndView model, HttpSession session) {
        model.setViewName(viewName);
        return model;
    }
}