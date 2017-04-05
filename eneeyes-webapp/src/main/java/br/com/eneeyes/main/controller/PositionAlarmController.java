package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.SoundStatus;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.PositionAlarmService;

@RestController
public class PositionAlarmController {
	
	@Autowired
	PositionAlarmService service;	
	
	@RequestMapping(value="/api/positionAlarm/{command}/", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> process(@PathVariable String command) {
		return null;        
	}
	
	@RequestMapping(value="/security/api/positionAlarm/updateAlarmStatus/{alarmStatus}/{uid}", method=RequestMethod.PUT, consumes = "application/json", produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> updateAlarmStatus(@PathVariable Long uid, @PathVariable AlarmStatus alarmStatus) {		
		return service.updateAlarmStatus(uid, alarmStatus);
	}
	
	@RequestMapping(value="/security/api/positionAlarm/updateSoundStatus/{soundStatus}/{uid}", method=RequestMethod.PUT, consumes = "application/json", produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> updateAlarmStatus(@PathVariable Long uid, @PathVariable SoundStatus soundStatus) {		
		return service.updateSoundStatus(uid, soundStatus);
	}

}
