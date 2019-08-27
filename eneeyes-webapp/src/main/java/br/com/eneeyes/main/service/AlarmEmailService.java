package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmEmailDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.AlarmEmail;
import br.com.eneeyes.main.repository.AlarmEmailRepository;
import br.com.eneeyes.main.repository.AlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class AlarmEmailService {

	@Autowired
	private AlarmEmailRepository repository;	
	
	@Autowired
	private AlarmRepository alarmRepository;

	
	public BasicResult<?> save(AlarmEmailDto dto) {
		
		LogResult<AlarmEmailDto> result = new LogResult<AlarmEmailDto>();
		
		Alarm alarm = alarmRepository.findOne(dto.getAlarmDto().getUid());		
		AlarmEmail alarmEmail = new AlarmEmail(dto);
		alarmEmail.setAlarm(alarm);
		alarmEmail = repository.save(alarmEmail);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setEntity(new AlarmEmailDto(alarmEmail));
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<AlarmEmailDto> result = new LogResult<AlarmEmailDto>();		
		try {
			repository.delete(uid);			
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
	
	public BasicResult<?> deleteByAlarmId(Long uid) {
		
		LogResult<AlarmEmailDto> result = new LogResult<AlarmEmailDto>();		
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
		
		Result<AlarmEmailDto> result = new Result<AlarmEmailDto>();		
		try {
			List<AlarmEmail> lista = repository.findByAlarmId(alarmId);
			if (lista != null) {				
				List<AlarmEmailDto> dto = new ArrayList<AlarmEmailDto>();				
				for (AlarmEmail alarmEmail : lista) {					
					dto.add(new AlarmEmailDto(alarmEmail) );
				}				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum AlarmEmaile Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}	
}
