package br.com.eneeyes.main.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.service.HistoricService;

@RestController
public class HistoricController {
	
	@Inject
	HistoricService service;	
	
	@RequestMapping(value="/security/api/historic/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> save(@RequestBody HistoricDto historicDto) {
		
		return service.save(historicDto);
	}
	
	@RequestMapping(value = "/security/api/historic/all", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> listAll() {
		return service.listAll();
	}
	
	@RequestMapping(value="/security/api/historic/obtemPorCompanyDetectorId/{uid}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetector(@PathVariable Long uid) {		
		return service.findByCompanyDetector(uid);		
	}
	
	@RequestMapping(value="/security/api/historic/findByCompanyDetectorAndSensorAndInterval/{companyDetectorId}/{sensorId}/{interval}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorAndInterval(@PathVariable Long companyDetectorId, @PathVariable Long sensorId, @PathVariable Integer interval) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, interval);
	}
	
	@RequestMapping(value="/security/api/historic/findByCompanyDetectorAndSensorUmaHora/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorUmaHora(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, 1);
	}
	
	@RequestMapping(value="/security/api/historic/findByCompanyDetectorAndSensorVinteQuatroHoras/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorVinteQuatroHoras(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, 6);
	}
	
	@RequestMapping(value="/security/api/historic/findByCompanyDetectorAndSensorSeisHoras/{companyDetectorId}/{sensorId}", method=RequestMethod.GET, produces = "application/json")			
	@ResponseStatus(HttpStatus.OK)
	public BasicResult<?> findByCompanyDetectorAndSensorSeisHoras(@PathVariable Long companyDetectorId, @PathVariable Long sensorId) {		
		return service.findByCompanyDetectorAndSensorAndInterval(companyDetectorId, sensorId, 6);
	}

}
