package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.AreaCompanyGenericAlarmViewService;

@RestController
public class AreaCompanyGenericAlarmViewController {
	
	@Autowired
	AreaCompanyGenericAlarmViewService service;	
	
	@RequestMapping(value = "/security/api/view/allAreaCompanyGenericAlarmView", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allAlarmCompanyGenericSensorView() {
		return service.listAll();
	}

	@RequestMapping(value="/security/api/view/findAreaCompanyGenericAlarmViewByAreaId/{areaId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAreaCompanyGenericAlarmViewByAreaId(@PathVariable Long areaId) {		
		BasicResult<?> result = service.listByAreaId(areaId); 
		return result;
	}
		
	@RequestMapping(value="/security/api/view/findAreaCompanyGenericAlarmViewByCompanyDeviceId/{companyDeviceId}", 
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAreaCompanyGenericAlarmViewByCompanyDeviceId(@PathVariable Long companyDeviceId) {		
		BasicResult<?> result = service.listByCompanyDeviceId(companyDeviceId); 
		return result;
	}	
}

