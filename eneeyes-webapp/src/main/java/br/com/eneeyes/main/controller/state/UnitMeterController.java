package br.com.eneeyes.main.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.state.UnitMeterService;

@RestController
public class UnitMeterController {
	
	@Autowired
	UnitMeterService unitMeterService;	
	
	@RequestMapping(value = "/security/api/state/allUnitMeter", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allUnitMeter() {
		return unitMeterService.listAll();
	}

}
