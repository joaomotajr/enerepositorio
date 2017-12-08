package br.com.eneeyes.main.controller;

import java.util.Date;

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
import br.com.eneeyes.main.dto.LogAuditoriaDto;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.LogAuditoriaService;


@RestController
public class LogAuditoriaController {
	
	@Autowired
	LogAuditoriaService service;	
	
	@RequestMapping(value="/security/api/logAuditoria/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody LogAuditoriaDto dto) {
		
		return service.save(dto);
	}	
	
	@RequestMapping(value="/security/api/logAuditoria/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/logAuditoria/preDef/{intervalType}/{currentPage}/{lenPage}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listpreDefView(
			@PathVariable IntervalType intervalType, 
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		User user = SigninUtils.principal();
				
		if(user.getCompany()  == null)		
			return service.listPreDefView(intervalType, currentPage, lenPage);
		else
			return service.listPreDefByCompanyIdView(user.getCompany().getUid(), intervalType, currentPage, lenPage);
				
	}
	
	@RequestMapping(value="/security/api/logAuditoria/interval/{dateIn}/{dateOut}/{currentPage}/{lenPage}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(
			@PathVariable Date dateIn,
			@PathVariable Date dateOut,
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		User user = SigninUtils.principal();
		
		if(user.getCompany()  == null)		
			return service.listIntervalView(dateIn, dateOut, currentPage, lenPage);
		else
			return service.listIntervalByCompanyIdView(user.getCompany().getUid(), dateIn, dateOut, currentPage, lenPage);				
	}
	
	@RequestMapping(value="/security/api/logAuditoria/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);		
	}

}
