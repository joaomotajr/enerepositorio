package br.com.eneeyes.main.controller.register;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.register.GasDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.register.GasService;

@RestController
public class GasController {
	
	@Inject
	GasService service;	
	
	@RequestMapping(value="/security/api/gas/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody GasDto gasDto) {
		
		return service.save(gasDto);
	}
	
	@RequestMapping(value="/security/api/gas/delete/{gasUid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/gas/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/gas/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);
	}

}
