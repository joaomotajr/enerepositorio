package br.com.eneeyes.main.service.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.state.PerfilAlarmDto;
import br.com.eneeyes.main.model.state.PerfilAlarm;
import br.com.eneeyes.main.repository.state.PerfilAlarmRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class PerfilAlarmService {
	
	@Autowired
	private PerfilAlarmRepository repository;
	
	public Result<?> listAll() {
		
		Result<PerfilAlarm> result = new Result<>();
		
		try {
			List<PerfilAlarm> lista = repository.findAll();

			if (lista != null) {				
				
				result.setList(lista);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;		
	}
	
	public BasicResult<?> save(PerfilAlarmDto dto) {
		
		Result<PerfilAlarmDto> result = new Result<PerfilAlarmDto>();		
		PerfilAlarm perfilAlarm = new PerfilAlarm(dto);		
		perfilAlarm = repository.save(perfilAlarm);
		
		dto.setUid(perfilAlarm.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<PerfilAlarmDto> result = new Result<PerfilAlarmDto>();		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Empresa Excluída.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;		
	}
}

