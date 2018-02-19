package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.CompanyGenericDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.CompanyGenericService;

@RestController
public class CompanyGenericController {
	
	@Autowired
	CompanyGenericService service;	
	
	@RequestMapping(value="/security/api/companyGeneric/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody CompanyGenericDto companyGenericDto) {
		
		return service.save(companyGenericDto);
	}
	
	@RequestMapping(value="/security/api/companyGeneric/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/companyGeneric/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/companyGeneric/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);		
	}
	
	@RequestMapping(value="/security/api/companyGeneric/obtemPorCompanyDevice/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByCompanyDevice(@PathVariable Long uid) {
		
		return service.findByCompanyDevice(uid);		
	}
	
	@RequestMapping(value="/security/api/companyGeneric/obtemPorAreaId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByAreaId(@PathVariable Long uid) {
		
		return service.findByArea(uid);		
	}
	
	@RequestMapping(value="/security/api/companyGeneric/updateAlarm/{alarmId}/{uid}", method=RequestMethod.PUT, consumes = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> updateAlarm(@PathVariable Long alarmId, @PathVariable Long uid) {		
		return service.updateAlarm(alarmId, uid);
	}
	
	@RequestMapping(value="/security/api/companyGeneric/removeAlarm/{uid}", method=RequestMethod.PUT, consumes = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> removeAlarm(@PathVariable Long uid) {		
		return service.removeAlarm(uid);
	}
	
}
