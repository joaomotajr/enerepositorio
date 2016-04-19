package br.com.eneeyes.archetype.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/unsecurity")
public class UnSecurity {
	@RequestMapping(value="/unauthorized")
	public ModelAndView unauthorized(ModelAndView model) {
		model.setViewName("/security/user/unauthorized");
		return model;
	}
}
