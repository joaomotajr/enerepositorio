package br.com.eneeyes.main.controller.register;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.register.DetectorDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.register.DetectorService;


@RestController
public class DetectorController {
	
	@Inject
	DetectorService service;	
	
	@RequestMapping(value="/security/api/detector/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody DetectorDto detectorDto) {
		
		return service.save(detectorDto);
	}
	
	@RequestMapping(value="/security/api/detector/delete/{detectorUid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
	
	@RequestMapping(value = "/security/api/detector/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/detector/obtemPorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listById(@PathVariable Long uid) {
		
		return service.findOne(uid);		
	}

}
