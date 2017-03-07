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
import br.com.eneeyes.main.service.views.HistoricViewService;

@RestController
public class HistoricViewController {
	
	@Inject
	HistoricViewService historicViewService;	
	
	@RequestMapping(value = "/security/api/view/allHistoricView", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> allHistoricView() {
		return historicViewService.listAll();
	}
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndInterval/{companyDetectorId}/{sensorId}/{interval}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Integer interval) {		
		return historicViewService.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, interval);
	}
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorLastMonth/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
		return historicViewService.findByCompanyDetectorAndSensorLastMonth(companyDetectorId, sensorId);
	}	
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndIntervalDays/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Date dateIn, @PathVariable Date dateOut) {		
		return historicViewService.findByCompanyDetectorAndSensorAndIntervalDays(companyDetectorId, sensorId, dateIn, dateOut);
	}

}