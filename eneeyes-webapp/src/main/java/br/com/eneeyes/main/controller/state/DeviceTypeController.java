package br.com.eneeyes.main.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.state.DeviceTypeDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.state.DeviceTypeService;

@RestController
public class DeviceTypeController {
	
	@Autowired
	DeviceTypeService service;	
	
	@RequestMapping(value = "/security/api/state/allDeviceType", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allDeviceType() {
		return service.listAll();
	}

	@RequestMapping(value="/security/api/state/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody DeviceTypeDto deviceTypeDto) {
		
		return service.save(deviceTypeDto);
	}
	
	@RequestMapping(value="/security/api/state/delete/{uid}", method=RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> delete(@PathVariable Long uid) {
		
		return service.delete(uid);
	}
}
