package br.com.eneeyes.main.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.CompanyDetectorDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.CompanyDetectorService;

@RestController
public class CompanyDetectorController {
	
	@Inject
	CompanyDetectorService service;	
	
	@RequestMapping(value="/security/api/companyDetector/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody CompanyDetectorDto companyDetectorDto) {
		
		return service.save(companyDetectorDto);
	}
	
	@RequestMapping(value="/security/api/companyDetector/saveList", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> saveList(@RequestBody List<CompanyDetectorDto> companyDetectorsDto) {
		
		return service.save(companyDetectorsDto);
	}
	
	@RequestMapping(value="/security/api/companyDetector/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/companyDetector/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/companyDetector/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);		
	}
	
	@RequestMapping(value="/security/api/companyDetector/obtemPorCompanyDevice/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByCompanyDevice(@PathVariable Long uid) {
		
		return service.findByCompanyDevice(uid);		
	}
	
	@RequestMapping(value="/security/api/companyDetector/obtemPorIdArea/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByIdArea(@PathVariable Long uid) {
		
		return service.findByArea(uid);		
	}

}
