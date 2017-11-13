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
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.historic.HistoricViewHourService;

@RestController
public class HistoricViewHourController {
	
	@Autowired
	HistoricViewHourService service;	
	
//	@RequestMapping(value = "/security/api/view/allHistoricViewGroupHours", method = RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> allHistoricView() {
//		return historicViewHourService.listAll();
//	}
	
	                           //api/historicView/findByCompanyDetectorAndSensorAndIntervalGroupHours/18/13/DOIS_DIAS/0/501?_csrf=bcd28c2a-6b0a-43bc-8b65-6af8fce437b9  
	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalGroupHours/{companyDetectorId}/{sensorId}/{intervalType}/{currentPage}/{lenPage}",
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(		
			@PathVariable Long companyDetectorId, 
			@PathVariable Long sensorId, 
			@PathVariable IntervalType intervalType, 
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, intervalType, currentPage, lenPage);
	}
	
//	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorLastMonthGroupHours/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
//		return historicViewHourService.findByCompanyDetectorAndSensorLastMonth(companyDetectorId, sensorId);
//	}	
	
//	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndIntervalDaysGroupHours/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Date dateIn, @PathVariable Date dateOut) {		
//		return historicViewHourService.findByCompanyDetectorAndSensorAndIntervalDays(companyDetectorId, sensorId, dateIn, dateOut);
//	}
	
	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalDaysGroupHours/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(		
			@PathVariable Long companyDetectorId, 
			@PathVariable Long sensorId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut,
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		return service.findByCompanyDetectorAndSensorAndIntervalHours(companyDetectorId, sensorId, dateIn, dateOut, currentPage, lenPage);
	}

}
