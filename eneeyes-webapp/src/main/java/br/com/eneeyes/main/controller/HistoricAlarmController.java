package br.com.eneeyes.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.result.GroupResult;
import br.com.eneeyes.main.service.HistoricAlarmService;

@RestController
public class HistoricAlarmController {
	
	@Autowired
	HistoricAlarmService service;	
	
	@RequestMapping(value="/security/api/historicAlarm/findByCompanyDeviceAndInterval/{companyDeviceId}/{intervalType}/{currentPage}/{lenPage}", 
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public GroupResult<?> findByCompanyDeviceAndInterval(
			@PathVariable Long companyDeviceId, 
			@PathVariable IntervalType intervalType, 
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {		
		return service.findByCompanyDeviceAndInterval(companyDeviceId, intervalType, currentPage, lenPage);
	}

	@RequestMapping(value="/security/api/historicAlarm/findByCompanyDeviceAndIntervalDays/{companyDeviceId}/{dateIn}/{dateOut}/{currentPage}/{lenPage}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public GroupResult<?> findByCompanyDeviceAndSensorAndIntervalDays(
			@PathVariable Long companyDeviceId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut,
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		return service.findByCompanyDeviceAndIntervalDays(companyDeviceId, dateIn, dateOut, currentPage, lenPage);
	}

}
