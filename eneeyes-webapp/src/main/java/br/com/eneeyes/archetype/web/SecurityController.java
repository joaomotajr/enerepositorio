package br.com.eneeyes.archetype.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.model.User;

@Controller
@SessionAttributes("linkedinAuthorization")
public class SecurityController {

    Log log = LogFactory.getLog(getClass());

    private boolean hasRole(String roleName) {
		boolean hasRole = false;
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		for(Role role : user.getRoles()) {
			if(role.getName().equals(roleName)) {
				hasRole = true;
			}
		}
		return hasRole;
	}

	private ModelAndView view(String viewName, ModelAndView model, HttpSession session) {
		model.setViewName(viewName);
		return model;
	}

    @RequestMapping(value="/security/{module}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView partial(@PathVariable String module, HttpServletRequest request, ModelAndView model) {
		return partial(module, null, request, model);
	}
	
	@RequestMapping(value="/security/{module}/{viewName}.html", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView partial(@PathVariable String module, @PathVariable String viewName, HttpServletRequest request, ModelAndView model) {
		if(("user".equals(module) || "category".equals(module)) && !hasRole("admin")) {
			model.setViewName("redirect:/unsecurity/unauthorized");
			return model;
		}
		
		String viewPath = "/security/".concat(module);
		if(viewName == null)
			viewName = "index";
		viewPath = viewPath.concat("/").concat(viewName);
		
		if(request.getAttribute("shiroLoginFailure") != null) {
			request.getSession().setAttribute("loginFailure", true);
			model.setViewName("redirect:/#/security/login");
			return model;
		}
		
		if(request.getSession().getAttribute("loginFailure") != null) {
			request.getSession().setAttribute("loginFailure", null);
			request.setAttribute("shiroLoginFailure", true);
		}

		model.addObject("locale", LocaleContextHolder.getLocale());
		model.setViewName(viewPath);
		return model;
	}

	@RequestMapping(value="/security/{viewName}.html")
	public ModelAndView securityView(@PathVariable String viewName, ModelAndView model, HttpSession session) {
		session.setAttribute("inQuery", false);
		return view(viewName, model, session);
	}
}