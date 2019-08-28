package br.com.eneeyes.main.controller.historic;

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
import br.com.eneeyes.main.service.historic.HistoricDayViewerService;

//@PreAuthorize("hasRole('ADMINISTRATOR')")
@RestController
public class HistoricViewerDayController {
	
	@Autowired
	HistoricDayViewerService service;	

	@RequestMapping(value="/security/api/historicFastViewer/findByCompanyDevicePreDefinedGroupDays/{companyDeviceId}/{intervalType}",
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public BasicResult<?> findByCompanyDevicePreDefined(		
			@PathVariable Long companyDeviceId, 
			@PathVariable IntervalType intervalType) {		
		return service.findByCompanyDevicePreDefined(companyDeviceId, intervalType);
	}
	
	@RequestMapping(value="/security/api/historicFastViewer/findByCompanyDeviceAndIntervalGroupDays/{companyDeviceId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDeviceAndIntervalGroupDays(
			@PathVariable Long companyDeviceId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut) {
		
		return service.findByCompanyDeviceAndIntervalGroupDays(companyDeviceId, dateIn, dateOut);
	}
}