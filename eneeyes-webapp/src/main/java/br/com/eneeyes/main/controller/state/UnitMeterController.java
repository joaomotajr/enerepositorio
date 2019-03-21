package br.com.eneeyes.main.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.state.UnitMeterDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.state.UnitMeterService;

@RestController
public class UnitMeterController {
	
	@Autowired
	UnitMeterService service;	
	
	@RequestMapping(value = "/security/api/state/UnitMeter/allUnitMeter", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allUnitMeter() {
		return service.listAll();
	}

	@RequestMapping(value="/security/api/state/UnitMeter/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody UnitMeterDto unitMeterDto) {
		
		return service.save(unitMeterDto);
	}
	
	@RequestMapping(value="/security/api/state/UnitMeter/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
}
