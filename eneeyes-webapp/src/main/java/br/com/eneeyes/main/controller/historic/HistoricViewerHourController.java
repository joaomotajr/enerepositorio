package br.com.eneeyes.main.controller.historic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.register.UidDto;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.historic.HistoricHourViewerService;

@RestController
public class HistoricViewerHourController {

	@Autowired
	HistoricHourViewerService service;	
	
	@RequestMapping(value = "/security/api/historicFastViewer/findByCompanyDeviceAndIntervalGroupHours/{companyDeviceId}/{intervalType}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDeviceAndInterval(@PathVariable Long companyDeviceId,
			@PathVariable IntervalType intervalType) {
		return service.findByCompanyDeviceAndInterval(companyDeviceId, intervalType);
	}

	@RequestMapping(value = "/security/api/historicFastViewer/findByCompanyDeviceAndIntervalDaysGroupHours/{companyDeviceId}/{dateIn}/{dateOut}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDeviceAndIntervalDays(@PathVariable Long companyDeviceId,
			@PathVariable Date dateIn, @PathVariable Date dateOut) {

		return service.findByCompanyDeviceAndIntervalhours(companyDeviceId, dateIn, dateOut);
	}

	@RequestMapping(value = "/security/api/historicFastViewer/findByCompanyDevicesInAndIntervalDaysGroupHours/{dateIn}/{dateOut}", 
				method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDevicesInAndIntervalDays(@RequestBody UidDto companyDevices, @PathVariable Date dateIn, @PathVariable Date dateOut,
			@PathVariable Integer currentPage, @PathVariable Integer lenPage) {		
		
		return service.findByCompanyDevicesInAndIntervalHours(companyDevices, dateIn, dateOut);

	}	
}
