package br.com.eneeyes.main.controller.views;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.result.HistoricResult;
import br.com.eneeyes.main.service.historic.HistoricViewService;

@RestController
public class HistoricViewController {
	
	@Autowired
	HistoricViewService service;	
	
	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorAndInterval/{companyDetectorId}/{sensorId}/{intervalType}/{currentPage}/{lenPage}", 
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public HistoricResult<?> findByCompanyDetectorAndSensorAndInterval(
			@PathVariable Long companyDetectorId, 
			@PathVariable Long sensorId, 
			@PathVariable IntervalType intervalType, 
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, intervalType, currentPage, lenPage);
	}

	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalDays/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}/{currentPage}/{lenPage}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public HistoricResult<?> findByCompanyDetectorAndSensorAndIntervalDays(
			@PathVariable Long companyDetectorId, 
			@PathVariable Long sensorId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut,
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		return service.findByCompanyDetectorAndSensorAndIntervalDays(companyDetectorId, sensorId, dateIn, dateOut, currentPage, lenPage);
	}

}
