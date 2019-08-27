package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.AlarmEmailDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.AlarmEmailService;


@RestController
public class AlarmEmailController {
	
	@Autowired
	AlarmEmailService service;	
	
	@RequestMapping(value="/security/api/alarmEmail/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody AlarmEmailDto alarmEmailDto) {		
		return service.save(alarmEmailDto);
	}
	
	@RequestMapping(value="/security/api/alarmEmail/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {		
		return service.delete(uid);
	}
	
	@RequestMapping(value="/security/api/alarmEmail/obtemPorAlarmId/{alarmId}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByAlarmId(@PathVariable Long alarmId) {		
		return service.listByAlarmId(alarmId);		
	}
}
