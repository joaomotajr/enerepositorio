package br.com.eneeyes.main.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.CompanyService;

@RestController
public class CompanyController {
	
	@Inject
	CompanyService service;	
	
	@RequestMapping(value="/security/api/company/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody CompanyDto companyDto) {
		
		return service.save(companyDto);
	}
	
	@RequestMapping(value="/security/api/company/delete/{companyUid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/company/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Result<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/company/obtemPorId/{companyUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Result<?> listById(@PathVariable Long uid) {
		
		return service.listById(uid);
	}

}
