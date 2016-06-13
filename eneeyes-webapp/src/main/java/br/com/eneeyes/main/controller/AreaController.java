package br.com.eneeyes.main.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.AreaDto;
import br.com.eneeyes.main.result.AreaResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.AreaService;

@RestController
public class AreaController {
	
	@Inject
	AreaService service;	
	
	@RequestMapping(value="/security/api/area/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AreaResult save(@RequestBody AreaDto areaDto) {
		
		return (AreaResult) service.save(areaDto);
	}
	
	@RequestMapping(value="/security/api/area/delete/{unitUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AreaResult delete(@PathVariable Long uid) {
		
		return (AreaResult) service.delete(uid);
	}
	
//	@RequestMapping(value = "/security/api/area/all", method = RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public AreaResult listAll() {
//		return service.listAll();
//	}
	
	@RequestMapping(value = "/security/api/area/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Result<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/area/obtemPorId/{unitUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Result<?> listById(@PathVariable Long uid) {
		
		return service.listById(uid);
	}

}
