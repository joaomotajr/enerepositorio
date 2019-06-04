package br.com.eneeyes.main.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.state.PerfilAlarmDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.state.PerfilAlarmService;

@RestController
public class PerfilAlarmController {
	
	@Autowired
	PerfilAlarmService service;	
	
	@RequestMapping(value = "/security/api/state/PerfilAlarm/allPerfilAlarm", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allPerfilAlarm() {
		return service.listAll();
	}

	@RequestMapping(value="/security/api/state/PerfilAlarm/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody PerfilAlarmDto perfilAlarmDto) {
		
		return service.save(perfilAlarmDto);
	}
	
	@RequestMapping(value="/security/api/state/PerfilAlarm/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
}
