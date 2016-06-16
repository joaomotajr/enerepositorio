package br.com.eneeyes.main.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.service.UnitService;
import br.com.eneeyes.main.dto.UnitDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@RestController
public class UnitController {
	
	@Inject
	UnitService service;	
	
	@RequestMapping(value="/security/api/unit/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody UnitDto unitDto) {
		
		return service.save(unitDto);
	}
	
	@RequestMapping(value="/security/api/unit/delete/{unitUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}	
	
	@RequestMapping(value = "/security/api/unit/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Result<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/unit/setparent/{id}/{parentId}" , method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public int setaParent(@PathVariable Long id, @PathVariable Long parentId) {
		
		return service.setParent(id, parentId);
	}
	

}
