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
import br.com.eneeyes.main.service.historic.HistoricViewDayService;

//@PreAuthorize("hasRole('ADMINISTRATOR')")
@RestController
public class HistoricViewDayController {
	
	@Autowired
	HistoricViewDayService service;	
	
//	@RequestMapping(value = "/security/api/view/allHistoricViewGroupDays", method = RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> allHistoricView() {
//		return historicViewDayService.listAll();
//	}
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndIntervalGroupDays/{companyDetectorId}/{sensorId}/{intervalType}/{currentPage}/{lenPage}",
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
	
	
//	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorLastMonthGroupDays/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> findByCompanyDetectorAndSensorLastMonth(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
//		return service.findByCompanyDetectorAndSensorLastMonth(companyDetectorId, sensorId);
//	}	
	
	@RequestMapping(value="/security/api/view/findByCompanyDetectorAndSensorAndIntervalDaysGroupDays/{companyDetectorId}/{sensorId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorAndIntervalDays(		
			@PathVariable Long companyDetectorId, 
			@PathVariable Long sensorId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut,
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		return service.findByCompanyDetectorAndSensorAndIntervalDays(companyDetectorId, sensorId, dateIn, dateOut, currentPage, lenPage);
	}

}
