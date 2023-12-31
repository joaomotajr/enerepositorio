package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.repository.singleton.TimeSingletonService;

@RestController
public class TimeController {
			
	@Autowired
	TimeSingletonService service;
	
	@RequestMapping(value="/api/time/process/{id}/{value}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public String process(@PathVariable Long id, @PathVariable String value) {
		
		return (String) service.process(id, value);		 
	}	

}
