package br.com.eneeyes.main.controller.views;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.DashCompanyService;

@RestController
public class DashCompanyController {
	
	@Inject
	DashCompanyService dashCompanyservice;	
	
	@RequestMapping(value = "/security/api/view/allDashCompany", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompany() {
		return dashCompanyservice.listAll();
	}

}
