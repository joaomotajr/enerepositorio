package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.AlarmMobileDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.AlarmMobileService;


@RestController
public class AlarmMobileController {
	
	@Autowired
	AlarmMobileService service;	
	
	@RequestMapping(value="/security/api/alarmMobile/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody AlarmMobileDto alarmMobileDto) {		
		return service.save(alarmMobileDto);
	}
	
	@RequestMapping(value="/security/api/alarmMobile/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {		
		return service.delete(uid);
	}
	
	@RequestMapping(value="/security/api/alarmMobile/obtemPorAlarmId/{alarmId}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByAlarmId(@PathVariable Long alarmId) {		
		return service.listByAlarmId(alarmId);		
	}
}
