package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmMobileDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.AlarmMobile;
import br.com.eneeyes.main.repository.AlarmMobileRepository;
import br.com.eneeyes.main.repository.AlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class AlarmMobileService {

	@Autowired
	private AlarmMobileRepository repository;	
	
	@Autowired
	private AlarmRepository alarmRepository;

	
	public BasicResult<?> save(AlarmMobileDto dto) {
		
		LogResult<AlarmMobileDto> result = new LogResult<AlarmMobileDto>();
		
		Alarm alarm = alarmRepository.findOne(dto.getAlarmDto().getUid());		
		AlarmMobile alarmMobile = new AlarmMobile(dto);
		alarmMobile.setAlarm(alarm);
		alarmMobile = repository.save(alarmMobile);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setEntity(new AlarmMobileDto(alarmMobile));
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<AlarmMobileDto> result = new LogResult<AlarmMobileDto>();		
		try {
			repository.delete(uid);			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Alarm Mobile Excluído.");
						
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("AlarmMobile Não Pode Ser Excluído, talvez haja dispositivos associados.");
		}		
		return result;		
	}
	
public BasicResult<?> deleteByAlarmId(Long uid) {
		
		LogResult<AlarmMobileDto> result = new LogResult<AlarmMobileDto>();		
		try {
			repository.deleteByAlarmId(uid);			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("AlarmEmaile Excluído.");
						
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("AlarmEmail Não Pode Ser Excluído, talvez haja dispositivos associados.");
		}		
		return result;		
	}
	
	public Result<?> listByAlarmId(Long alarmId) {
		
		Result<AlarmMobileDto> result = new Result<AlarmMobileDto>();		
		try {
			List<AlarmMobile> lista = repository.findByAlarmId(alarmId);
			if (lista != null) {				
				List<AlarmMobileDto> dto = new ArrayList<AlarmMobileDto>();				
				for (AlarmMobile alarmMobile : lista) {					
					dto.add(new AlarmMobileDto(alarmMobile) );
				}				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum AlarmMobilee Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}	
}
