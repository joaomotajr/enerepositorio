package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.PositionAlarmMessageDto;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.PositionAlarmMessage;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.repository.PositionAlarmMessageRepository;
import br.com.eneeyes.main.repository.PositionAlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Service
public class PositionAlarmMessageService implements IService<PositionAlarmMessageDto> {
	
	@Autowired
	private PositionAlarmMessageRepository repository;
	
	@Autowired
	private PositionAlarmRepository positionAlarmRepository;
	
	@Autowired
	PositionAlarmService positionAlarmService;
	
	public BasicResult<?> findByPositionAlarmId(Long positionAlarmId) {
		Result<PositionAlarmMessageDto> result = new Result<PositionAlarmMessageDto>();
		
		try {
			List<PositionAlarmMessage> lista = repository.findByPositionAlarmId(positionAlarmId);
			
			if (lista != null) {
				
				List<PositionAlarmMessageDto> dto = new ArrayList<PositionAlarmMessageDto>();
				
				for (PositionAlarmMessage positionAlarmMessage   : lista) {					
					dto.add(new PositionAlarmMessageDto(positionAlarmMessage) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Mensagem.");
			}
			
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResult<?> findOne(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public BasicResult<?> save(PositionAlarmMessageDto dto) {
		Result<PositionAlarmMessageDto> result = new Result<PositionAlarmMessageDto>();
		
		PositionAlarm positionAlarm = positionAlarmRepository.findByUid(dto.getPositionAlarmDto().getUid());
						
		PositionAlarmMessage positionAlarmMessage = new PositionAlarmMessage(dto);	
		positionAlarmMessage.setPositionAlarm(positionAlarm);
		
		positionAlarmMessage = repository.save(positionAlarmMessage);
		positionAlarmService.updateAlarmStatus(positionAlarm.getUid(), AlarmStatus.READED);
		
				
		dto.setUid(positionAlarmMessage.getUid());
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}
}
