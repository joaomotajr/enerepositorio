package br.com.eneeyes.main.controller.views;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.AlarmCompanyDetectorSensorViewService;

@RestController
public class AlarmCompanyDetectorSensorViewController {
	
	@Inject
	AlarmCompanyDetectorSensorViewService service;	
	
	@RequestMapping(value = "/security/api/view/allAlarmCompanyDetectorSensorView", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allAlarmCompanyDetectorSensorView() {
		return service.listAll();
	}

	@RequestMapping(value="/security/api/view/findAlarmCompanyDetectorSensorViewByAlarmId/{alarmId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findAlarmCompanyDetectorSensorViewByAlarmId(@PathVariable Long alarmId) {		
		return service.findAlarmCompanyDetectorSensorViewByAlarmId(alarmId);
	}
}