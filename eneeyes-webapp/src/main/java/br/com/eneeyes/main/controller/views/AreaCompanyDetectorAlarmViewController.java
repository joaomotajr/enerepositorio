package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.AreaCompanyDetectorAlarmViewService;

@RestController
public class AreaCompanyDetectorAlarmViewController {
	
	@Autowired
	AreaCompanyDetectorAlarmViewService service;	
	
	@RequestMapping(value = "/security/api/view/allAreaCompanyDetectorAlarmView", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allAlarmCompanyDetectorSensorView() {
		return service.listAll();
	}

	@RequestMapping(value="/security/api/view/findAreaCompanyDetectorAlarmViewByAreaId/{areaId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAreaCompanyDetectorAlarmViewByAreaId(@PathVariable Long areaId) {		
		BasicResult<?> result = service.listByAreaId(areaId); 
		return result;
	}
	
	@RequestMapping(value="/security/api/view/findAreaCompanyDetectorAlarmViewByCompanyDetectorId/{companyDetectorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAreaCompanyDetectorAlarmViewByCompanyDetectorId(@PathVariable Long companyDetectorId) {		
		BasicResult<?> result = service.listByCompanyDetectorId(companyDetectorId); 
		return result;
	}
	
	@RequestMapping(value="/security/api/view/findAreaCompanyDeviceAlarmViewByCompanyDetectorId/{companyDetectorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAreaCompanyDeviceAlarmViewByCompanyDetectorId(@PathVariable Long companyDeviceId) {		
		BasicResult<?> result = service.listByCompanyDeviceId(companyDeviceId); 
		return result;
	}
}
