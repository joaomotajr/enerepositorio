package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.web.config.auth.signin.SigninUtils;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.DashCompaniesAlarmService;

@RestController
public class DashCompaniesAlarmController {
	
	@Autowired
	DashCompaniesAlarmService service;	
	
	@RequestMapping(value = "/security/api/view/allDashCompaniesAlarm", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompaniesAlarm() {
		User user = SigninUtils.principal();		
		if(user.getCompany()  == null)		
			return service.listAll();
		else
			return service.listByCompanyId(user.getCompany().getUid());
	}
	
	@RequestMapping(value = "/security/api/view/allDashCompaniesAlarmByAlarms", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDashCompaniesAlarmByAlarmStatus() {
		User user = SigninUtils.principal();		
		if(user.getCompany()  == null)		
			return service.listByAlarms();
		else
			return service.listByCompanyIdAndAlarms(user.getCompany().getUid());
	}

}
