package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.CompanyDetectorMaintenanceHistoricDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.CompanyDetectorMaintenanceHistoricService;

@RestController
public class CompanyDetectorMaintenanceHistoricController {
	
	@Autowired
	CompanyDetectorMaintenanceHistoricService service;	
	
	@RequestMapping(value="/security/api/companyDetectorMaintenanceHistoric/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody CompanyDetectorMaintenanceHistoricDto companyDetectorMaintenanceHistoricDto) {
		
		return service.save(companyDetectorMaintenanceHistoricDto);
	}
	
	@RequestMapping(value="/security/api/companyDetectorMaintenanceHistoric/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/companyDetectorMaintenanceHistoric/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/companyDetectorMaintenanceHistoric/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);
	}
	
	@RequestMapping(value="/security/api/companyDetectorMaintenanceHistoric/obtemPorCompanyDetector/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByCompanyDetectorId(@PathVariable Long uid) {		
		return service.findByCompanyDetectorId(uid);
	}
}
