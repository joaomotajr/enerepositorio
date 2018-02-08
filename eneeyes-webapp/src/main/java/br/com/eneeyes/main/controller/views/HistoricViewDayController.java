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

	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndIntervalGroupDays/{companyDetectorId}/{intervalType}/{currentPage}/{lenPage}",
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public BasicResult<?> findByCompanyDetectorAndInterval(		
			@PathVariable Long companyDetectorId, 
			@PathVariable IntervalType intervalType, 
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {		
		return service.findByCompanyDetectorAndInterval(companyDetectorId, intervalType, currentPage, lenPage);
	}
	
	@RequestMapping(value="/security/api/historicView/findByCompanyDetectorAndIntervalDaysGroupDays/{companyDetectorId}/{dateIn}/{dateOut}/{currentPage}/{lenPage}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndIntervalDays(		
			@PathVariable Long companyDetectorId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut,
			@PathVariable Integer currentPage, 
			@PathVariable Integer lenPage) {
		
		return service.findByCompanyDetectorAndIntervalDays(companyDetectorId, dateIn, dateOut, currentPage, lenPage);
	}

}
