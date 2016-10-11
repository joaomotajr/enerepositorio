package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorAlarmDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDetectorAlarm;
import br.com.eneeyes.main.repository.CompanyDetectorAlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Named
public class CompanyDetectorAlarmService implements IService<CompanyDetectorAlarmDto> {

	@Inject
	private CompanyDetectorAlarmRepository companyDetectorAlarmRepository;

	@Override
	public BasicResult<?> save(CompanyDetectorAlarmDto dto) {
		
		Result<CompanyDetectorAlarmDto> result = new Result<CompanyDetectorAlarmDto>();
		CompanyDetectorAlarm companyDetectorAlarm = new CompanyDetectorAlarm(dto);
		companyDetectorAlarm = companyDetectorAlarmRepository.save(companyDetectorAlarm);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");
		
		return result;
	}

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
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

}
