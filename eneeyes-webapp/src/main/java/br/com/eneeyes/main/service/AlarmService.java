package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.Alarm;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.repository.AlarmRepository;
import br.com.eneeyes.main.repository.singleton.AlarmSingletonRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.views.CompanyDeviceAlarmViewService;

@Service
public class AlarmService implements IService<AlarmDto> {

	@Autowired
	private AlarmRepository repository;
	
	@Autowired
	private AlarmEmailService alarmEmailService;
	
	@Autowired
	private AlarmMobileService alarmMobileService;
	
	@Autowired
	private CompanyDeviceAlarmViewService companyDetectorAlarmViewService;
	
	@Autowired
	private LogAuditoriaService logAuditoriaService;
	
	public BasicResult<?> save(AlarmDto dto) {
		
		LogResult<AlarmDto> result = new LogResult<AlarmDto>(); 	
		
		Alarm alarm = new Alarm(dto);		
		alarm = repository.save(alarm);
		
		AlarmSingletonRepository.populate(companyDetectorAlarmViewService.findAll());
				
		ActionType actionType = dto.getUid() == null || dto.getUid() == 0 ? ActionType.CREATE : ActionType.UPDATE;		
		
		dto.setUid(alarm.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");
		
		logAuditoriaService.save(this.getClass().getSimpleName(), actionType, result.toString());
		
		return result;
	}
	
	public int onOff(AlarmDto dto) {
		
		Alarm alarm = new Alarm(dto);		
		return repository.onOff(!alarm.getAlarmOn(), alarm.getUid());
						
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<AlarmDto> result = new LogResult<AlarmDto>(); 	
		
		try {			
			alarmEmailService.deleteByAlarmId(uid);
			alarmMobileService.deleteByAlarmId(uid);
			repository.delete(uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Alarme Excluído.");
			
			logAuditoriaService.save(this.toString(), ActionType.DELETE, result.toString());
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("Alarme Não Pode Ser Excluído, talvez haja dispositivos associados.");
		}		
		
		return result;		
	}

	public List<Alarm> findAll() {
		
		return repository.findAll();		
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
				result.setMessage("Nenhum Alarme Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public Result<?> listByCompanyId(Long companyId) {
		
		Result<AlarmDto> result = new Result<AlarmDto>(); 	
		
		try {
			List<Alarm> lista = repository.findByCompanyId(companyId);

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
				result.setMessage("Nenhum Alarme Localizado.");
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
				result.setMessage("Nenhum Alarme Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
