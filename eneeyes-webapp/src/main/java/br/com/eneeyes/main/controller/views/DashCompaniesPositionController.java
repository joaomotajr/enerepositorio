package br.com.eneeyes.main.controller.views;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.DashCompaniesPositionService;

@RestController
public class DashCompaniesPositionController {
	
	@Inject
	DashCompaniesPositionService service;	
	
	@RequestMapping(value = "/security/api/view/allDashCompaniesPosition", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompaniesPosition() {
		return service.listAll();
	}

}
