package br.com.eneeyes.main.controller.views;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.views.HistoricViewHourService;

@RestController
public class HistoricViewHourController {
	
	@Inject
	HistoricViewHourService historicViewHourService;	
	
	@RequestMapping(value = "/security/api/view/allHistoricViewGroupHours", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allHistoricView() {
		return historicViewHourService.listAll();
	}
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndIntervalGroupHours/{companyDetectorId}/{sensorId}/{interval}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Integer interval) {		
		return historicViewHourService.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, interval);
	}
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorLastMonthGroupHours/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
		return historicViewHourService.findByCompanyDetectorAndSensorLastMonth(companyDetectorId, sensorId);
	}	
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndIntervalDaysGroupHours/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Date dateIn, @PathVariable Date dateOut) {		
		return historicViewHourService.findByCompanyDetectorAndSensorAndIntervalDays(companyDetectorId, sensorId, dateIn, dateOut);
	}

}