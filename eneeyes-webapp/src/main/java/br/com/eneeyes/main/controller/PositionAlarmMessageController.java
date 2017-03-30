package br.com.eneeyes.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.PositionAlarmMessageDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.PositionAlarmMessageService;

@RestController
public class PositionAlarmMessageController {

	@Autowired
	PositionAlarmMessageService service;

	@RequestMapping(value = "/security/api/positionAlarmMessage/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody PositionAlarmMessageDto dto) {
		 //User user =
		 //(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		 
		return service.save(dto);
	}

	@RequestMapping(value = "/security/api/positionAlarmMessage/delete/{uid}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {

		return service.delete(uid);
	}

	@RequestMapping(value = "/security/api/positionAlarmMessage/obtemPorPositionAlarmId/{uid}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listByPositionAlarm(@PathVariable Long uid) {

		return service.findByPositionAlarmId(uid);
	}

}
