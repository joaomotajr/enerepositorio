package br.com.eneeyes.main.controller.register;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.register.TransmitterDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.register.TransmitterService;


@RestController
public class TransmitterController {
	
	@Inject
	TransmitterService service;	
	
	@RequestMapping(value="/security/api/transmitter/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody TransmitterDto transmitterDto) {
		
		return service.save(transmitterDto);
	}
	
	@RequestMapping(value="/security/api/transmitter/delete/{transmitterUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/transmitter/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/transmitter/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);		
	}

}
