package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.repository.AlarmRepository;
import br.com.eneeyes.main.repository.CompanyDetectorAlarmSingletonRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class AlarmService implements IService<AlarmDto> {

	@Inject
	private AlarmRepository repository;
	
	@Autowired
	CompanyDetectorAlarmService companyDetectorAlarmAlarmService;
	
	public BasicResult<?> save(AlarmDto dto) {
		
		Result<AlarmDto> result = new Result<AlarmDto>(); 	
		
		Alarm alarm = new Alarm(dto);		
		alarm = repository.save(alarm);
		
		CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
		
		dto.setUid(alarm.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}
	
	public int onOff(AlarmDto dto) {
		
		Alarm alarm = new Alarm(dto);		
		return repository.onOff(!alarm.getAlarmOff(), alarm.getUid());
						
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<AlarmDto> result = new Result<AlarmDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Controladora/PLC Excluída.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("Controladora/PLC Não Pode Ser Excluída.");
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<AlarmDto> result = new Result<AlarmDto>(); 	
		
		try {
			List<Alarm> lista = repository.findAll();

			if (lista != null) {
				
				List<AlarmDto> dto = new ArrayList<AlarmDto>();
				
				for (Alarm alarm : lista) {					
					dto.add(new AlarmDto(alarm) );
				}
				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}


	public Result<?> findOne(Long uid) {
		
		Result<AlarmDto> result = new Result<AlarmDto>();
		
		try {
			Alarm item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new AlarmDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
