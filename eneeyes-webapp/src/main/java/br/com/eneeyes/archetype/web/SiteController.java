package br.com.eneeyes.archetype.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*@Controller
@RequestMapping("/")*/
@Deprecated
public class SiteController {

	@RequestMapping(method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index(HttpServletRequest request, ModelAndView model) {
		return partial("index", request, model);
	}
	
	@RequestMapping(value="/reload")
	public String reload() {
		return "redirect:/";
	}
	
	@RequestMapping("partials/{viewName}.html")
	public ModelAndView partial(@PathVariable String viewName, HttpServletRequest request, ModelAndView model) {
		model.addObject("locale", LocaleContextHolder.getLocale());
		model.setViewName(viewName);
		return model;
	}
}