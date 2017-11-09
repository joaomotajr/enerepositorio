package br.com.eneeyes.main.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.HistoricResult;
import br.com.eneeyes.main.service.historic.HistoricViewService;

@RestController
public class HistoricViewController {
	
	@Autowired
	HistoricViewService service;	
	
//	@RequestMapping(value = "/security/api/historicView/allHistoricView", method = RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> allHistoricView() {
//		return service.listAll();
//	}
	
	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorAndInterval/{companyDetectorId}/{sensorId}/{interval}/{currentPage}/{lenPage}", 
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public HistoricResult<?> findByCompanyDetectorAndSensorAndInterval(
			@PathVariable Long companyDetectorId, 
			@PathVariable Long sensorId, 
			@PathVariable Integer interval, 
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, interval, currentPage, lenPage);
	}
	
	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorLastMonth/{companyDetectorId}/{sensorId}/{page}/{lenPage}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Integer currentPage, @PathVariable Integer lenPage) {		
		return service.findByCompanyDetectorAndSensorLastMonth(companyDetectorId, sensorId);
	}	

//	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalDays/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Date dateIn, @PathVariable Date dateOut) {		
//		return service.findByCompanyDetectorAndSensorAndIntervalDays(companyDetectorId, sensorId, dateIn, dateOut);
//	}

}
