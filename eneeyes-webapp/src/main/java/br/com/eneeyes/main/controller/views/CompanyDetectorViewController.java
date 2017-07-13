package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.CompanyDetectorViewService;

@RestController
public class CompanyDetectorViewController {
	
	@Autowired
	CompanyDetectorViewService service;	
	
	@RequestMapping(value = "/security/api/view/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}	                                            
	
	@RequestMapping(value="/security/api/view/obtemPorCompanyDetectorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetector(@PathVariable Long uid) {
		
		return service.findByCompanyDetector(uid);		
	}
	
	@RequestMapping(value="/security/api/view/existsSensorById/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> existsSensorById(@PathVariable Long uid) {
		
		return service.existsSensor(uid);		
	}	
	
	@RequestMapping(value="/security/api/view/existsSensorsByIds/{uid1}/{uid2}",  method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> existsSensors(@PathVariable Long uid1, @PathVariable Long uid2) {
		
		return service.existsSensor(uid1, uid2);
	}

}
