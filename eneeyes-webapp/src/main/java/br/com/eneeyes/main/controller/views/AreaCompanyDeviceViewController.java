package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.AreaCompanyDeviceViewService;

@RestController
public class AreaCompanyDeviceViewController {
	
	@Autowired
	AreaCompanyDeviceViewService service;	
	
	@RequestMapping(value="/security/api/view/findAreaCompanyDeviceViewByAreaId/{areaId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAreaCompanyDeviceViewByareaId(@PathVariable Long areaId) {		
		return service.listByAreaId(areaId);
	}
}
