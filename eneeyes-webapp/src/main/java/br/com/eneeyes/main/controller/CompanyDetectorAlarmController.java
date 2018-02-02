//package br.com.eneeyes.main.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
//import br.com.eneeyes.main.result.BasicResult;
//import br.com.eneeyes.main.service.CompanyDetectorAlarmService;
//
//@RestController
//public class CompanyDetectorAlarmController {
//	
//	@Autowired
//	CompanyDetectorAlarmService service;	
//	
//	@RequestMapping(value="/security/api/companyDetectorAlarm/save", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> save(@RequestBody CompanyDetectorAlarmDto companyDetectorAlarmDto) {
//		
//		return service.save(companyDetectorAlarmDto);
//	}	
//	
//	@RequestMapping(value="/security/api/companyDetectorAlarm/delete", method=RequestMethod.POST, consumes = "application/json", produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> delete(@RequestBody CompanyDetectorAlarmDto companyDetectorAlarmDto) {
//		
//		return service.deletar(companyDetectorAlarmDto);
//	}
//	
//	@RequestMapping(value="/security/api/companyDetectorAlarm/obtemPorCompanyDetector/{uid}", method=RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> listByCompanyDevice(@PathVariable Long uid) {
//		
//		return service.findByCompanyDetector(uid);		
//	}
//	
//	@RequestMapping(value="/security/api/companyDetectorAlarm/obtemPorAreaId/{uid}", method=RequestMethod.GET, produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public BasicResult<?> findByAreaId(@PathVariable Long uid) {
//		
//		return service.findByAreaId(uid);		
//	}
//}
