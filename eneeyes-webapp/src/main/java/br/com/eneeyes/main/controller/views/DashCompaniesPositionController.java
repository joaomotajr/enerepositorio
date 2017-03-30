package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.DashCompaniesPositionService;

@RestController
public class DashCompaniesPositionController {
	
	@Autowired
	DashCompaniesPositionService service;	
	
	@RequestMapping(value = "/security/api/view/allDashCompaniesPosition", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompaniesPosition() {
		return service.listAll();
	}
	
	@RequestMapping(value = "/security/api/view/offline/{periodo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listOffline(@PathVariable Integer periodo) {
		return service.listOffline(periodo);
	}

}
