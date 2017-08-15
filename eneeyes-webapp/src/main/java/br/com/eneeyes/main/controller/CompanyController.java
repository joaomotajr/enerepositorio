package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.web.config.auth.signin.SigninUtils;
import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService service;	
	
	@RequestMapping(value="/security/api/company/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody CompanyDto companyDto) {
		
		return service.save(companyDto);
	}
	
	@RequestMapping(value="/security/api/company/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/company/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value = "/security/api/company/allView", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAllView() {
		
		User user = SigninUtils.principal();
		
		if(user.getCompany()  == null)		
			return service.listAllView();
		else
			return service.listOneView(user.getCompany().getUid());		
		
	}	
	
	@RequestMapping(value = "/security/api/company/allSumaryView", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAllSumaryView() {
		
		User user = SigninUtils.principal();
		
		if(user.getCompany()  == null)		
			return service.listAllSumaryView();
		else
			return service.listOneSumaryView(user.getCompany().getUid());		
		
	}
	
	@RequestMapping(value="/security/api/company/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);
	}

}
