package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.web.config.auth.signin.SigninUtils;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.AlarmService;


@RestController
public class AlarmController {
	
	@Autowired
	AlarmService service;	
	
	@RequestMapping(value="/security/api/alarm/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody AlarmDto alarmDto) {
		
		return service.save(alarmDto);
	}
	
	@RequestMapping(value="/security/api/alarm/onOff", method=RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public int onOff(@RequestBody AlarmDto alarmDto) {
		
		return service.onOff(alarmDto);
	}
	
	@RequestMapping(value="/security/api/alarm/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/alarm/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		
		User user = SigninUtils.principal();
				
		if(user.getCompany()  == null)		
			return service.listAll();
		else
			return service.listByCompanyId(user.getCompany().getUid());
				
	}
	
	@RequestMapping(value="/security/api/alarm/obtemPorCompanyId/{companyId}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByCompanyId(@PathVariable Long companyId) {
		
		return service.listByCompanyId(companyId);		
	}
	
	@RequestMapping(value="/security/api/alarm/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);		
	}

	@RequestMapping(value="/security/api/alarm/updateAlarmFeedback/{email1}/{email2}/{email3}/{emailOffline}/{sms1}/{sms2}/{sms3}/{smsOffline}/{uid}", 
			method=RequestMethod.PUT, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> updateAlarmFeedback(
		@PathVariable String email1, 
		@PathVariable String email2,			
		@PathVariable String email3,
		@PathVariable String emailOffline,
		@PathVariable String sms1,
		@PathVariable String sms2,
		@PathVariable String sms3,
		@PathVariable String smsOffline,
		@PathVariable Long uid) {		
		return service.updateAlarmFeedback(
			Boolean.parseBoolean(email1), 
			Boolean.parseBoolean(email2), 
			Boolean.parseBoolean(email3),
			Boolean.parseBoolean(emailOffline),
			Boolean.parseBoolean(sms1),
			Boolean.parseBoolean(sms2),
			Boolean.parseBoolean(sms3),
			Boolean.parseBoolean(smsOffline),
			uid);
	}

}
