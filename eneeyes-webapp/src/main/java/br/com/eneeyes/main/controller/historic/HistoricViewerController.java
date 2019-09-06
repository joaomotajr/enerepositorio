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
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.historic.HistoricViewerService;

@RestController
public class HistoricViewerController {
	
	@Autowired
	HistoricViewerService service;	
	
	@RequestMapping(value="/security/api/historicFastViewer/findByCompanyDevicePreDefined/{companyDeviceId}/{intervalType}", 
			method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)                      
	public Result<?> findByCompanyDevicePreDefined(
			@PathVariable Long companyDeviceId, 
			@PathVariable IntervalType intervalType) {		
		return service.findByCompanyDevicePreDefined(companyDeviceId, intervalType);
	}

	@RequestMapping(value="/security/api/historicFastViewer/findByCompanyDeviceAndInterval/{companyDeviceId}/{dateIn}/{dateOut}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public Result<?> findByCompanyDeviceAndInterval(
			@PathVariable Long companyDeviceId, 
			@PathVariable Date dateIn, 
			@PathVariable Date dateOut) {		
		return service.findByCompanyDeviceAndInterval(companyDeviceId, dateIn, dateOut);
	}

}
