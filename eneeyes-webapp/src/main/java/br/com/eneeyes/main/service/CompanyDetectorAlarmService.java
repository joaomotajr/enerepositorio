package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.dto.CompanyDetectorDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDetectorAlarm;
import br.com.eneeyes.main.repository.CompanyDetectorAlarmRepository;
import br.com.eneeyes.main.repository.CompanyDetectorAlarmSingletonRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyDetectorAlarmService implements IService<CompanyDetectorAlarmDto> {

	@Autowired
	CompanyDetectorService companyDetectorService;
	
	@Autowired
	private CompanyDetectorAlarmRepository companyDetectorAlarmRepository;

	@Override
	public BasicResult<?> save(CompanyDetectorAlarmDto dto) {
		
		Result<CompanyDetectorAlarmDto> result = new Result<CompanyDetectorAlarmDto>();
		CompanyDetectorAlarm companyDetectorAlarm = new CompanyDetectorAlarm(dto);
		
		if (companyDetectorAlarmRepository.updateAlarm(companyDetectorAlarm.getAlarm(), companyDetectorAlarm.getCompanyDetector(), companyDetectorAlarm.getId().getSensorId()) <= 0)		
			companyDetectorAlarm = companyDetectorAlarmRepository.save(companyDetectorAlarm);
		
		CompanyDetectorAlarmSingletonRepository.populate(findAll());
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");
		
		return result;
	}
	
	public BasicResult<?> deletar(CompanyDetectorAlarmDto dto) {
		
		Result<CompanyDetectorAlarmDto> result = new Result<CompanyDetectorAlarmDto>();
		CompanyDetectorAlarm companyDetectorAlarm = new CompanyDetectorAlarm(dto);
		
		companyDetectorAlarmRepository.deleteAlarm( companyDetectorAlarm.getCompanyDetector(), companyDetectorAlarm.getId().getSensorId());		
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Excluído com sucesso.");
		
		return result;
	}
	
	public void deleteByCompanyDetectorId(long companyDetectorId) {
				
		companyDetectorAlarmRepository.deleteAlarm(companyDetectorId);		
	}
	
	@Override
	public BasicResult<?> findOne(Long uid) {
		return null;
		
	}
	
	public BasicResult<?> findByCompanyDetector(long uid) {
		
		Result<CompanyDetectorAlarmDto> result = new Result<CompanyDetectorAlarmDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		List<CompanyDetectorAlarm> lista = companyDetectorAlarmRepository.FindByCompanyDetector(companyDetector);
		
		if (lista != null) {
			
			List<CompanyDetectorAlarmDto> dto = new ArrayList<CompanyDetectorAlarmDto>();
			
			for (CompanyDetectorAlarm companyDetectorAlarm   : lista) {					
				dto.add(new CompanyDetectorAlarmDto(companyDetectorAlarm.getAlarm(), companyDetectorAlarm.getId().getSensorId()));
			}
							
			result.setList(dto);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");
		} else {
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Nenhum Alarm Cadastrado.");
		}
		
		return result;
	}
	
	public BasicResult<?> findByAreaId(Long uid) {
		Result<CompanyDetectorAlarmDto> result = new Result<CompanyDetectorAlarmDto>();
		
		List<CompanyDetector> companyDetectors = companyDetectorService.findByAreaId(uid);
		
		try {
			List<CompanyDetectorAlarm> lista = companyDetectorAlarmRepository.findByCompanyDetectorIn(companyDetectors);
			
			if (lista != null) {
				
				List<CompanyDetectorAlarmDto> dto = new ArrayList<CompanyDetectorAlarmDto>();
				
				for (CompanyDetectorAlarm companyDetectorAlarm   : lista) {					
					
					CompanyDetectorDto companyDetectorDto = new CompanyDetectorDto();
					companyDetectorDto.setUid(companyDetectorAlarm.getId().getCompanyDetectorId());
					
					dto.add(new CompanyDetectorAlarmDto(companyDetectorAlarm.getAlarm(), companyDetectorAlarm.getId().getSensorId(), companyDetectorDto));					
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma area.");
			}
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public CompanyDetectorAlarmDto findByCompanyDetectorAndSensor(long companyDetectorUid, long sensorUid) {
			
		CompanyDetectorAlarm companyDetectorAlarm = companyDetectorAlarmRepository.FindByCompanyDetectorIdAndSensorId(companyDetectorUid , sensorUid);
		
		return(companyDetectorAlarm == null ? null : new CompanyDetectorAlarmDto(companyDetectorAlarm.getAlarm(), companyDetectorAlarm.getId().getSensorId()));
		
	}
	
	public List<CompanyDetectorAlarm> findAll() {
		
		return companyDetectorAlarmRepository.findAll();		
	}

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

}
