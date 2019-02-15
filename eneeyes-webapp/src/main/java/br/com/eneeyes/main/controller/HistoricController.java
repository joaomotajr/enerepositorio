package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.web.result.ResultMessage;
import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.historic.HistoricService;

@RestController
public class HistoricController {
	
	@Autowired
	HistoricService service;
		
	@RequestMapping(value="/api/historic/SaveByPositionUid2/{uid}/{value}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Boolean saveByPositionUid2(@PathVariable Long uid, @PathVariable String value) {
		
		return service.saveByPositionUid(uid, value);
	}
	
	@RequestMapping(value="/api/historic/SaveByPositionIOT/{uid}/{value}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResultMessage saveByPositionIOT(@PathVariable Long uid, @PathVariable String value) {
		
		return service.saveByPositionIOT(uid, value);
	}
	
	@RequestMapping(value="/api/historic/SaveByPositionUid/{uid}/{value}", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Boolean saveByPositionUid(@PathVariable Long uid, @PathVariable String value) {
		
		return service.saveByPositionUid(uid, value);
	}
	
	@RequestMapping(value="/security/api/historic/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody HistoricDto historicDto) {
		
		return service.save(historicDto);
	}

}
