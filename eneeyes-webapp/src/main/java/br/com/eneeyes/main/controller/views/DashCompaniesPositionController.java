package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.web.config.auth.signin.SigninUtils;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.DashCompaniesPositionService;

@RestController
public class DashCompaniesPositionController {
	
	@Autowired
	DashCompaniesPositionService service;	
		
	@RequestMapping(value = "/security/api/view/allDashCompaniesPosition", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompaniesPosition() {
		User user = SigninUtils.principal();
		
		if(user.getCompany()  == null)		
			return service.listAll();
		else
			return service.listByCompanyId(user.getCompany().getUid());
	}
	
	@RequestMapping(value = "/security/api/view/allDashCompaniesPositionByCompanyId/{companyId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompaniesPositionByCompanyId(@PathVariable Long companyId) {
		
		return service.listByCompanyId(companyId );
	}
	
	@RequestMapping(value = "/security/api/view/offline/{periodo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listOffline(@PathVariable Integer periodo) {
		User user = SigninUtils.principal();
		
		if(user.getCompany()  == null)		
			return service.listOffline(periodo);
		else
			return service.listOfflineByCompanyId(periodo, user.getCompany().getUid());
	}
}
