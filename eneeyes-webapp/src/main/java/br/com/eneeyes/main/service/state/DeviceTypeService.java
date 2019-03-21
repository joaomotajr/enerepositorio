package br.com.eneeyes.main.service.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.state.DeviceTypeDto;
import br.com.eneeyes.main.model.state.DeviceType;
import br.com.eneeyes.main.repository.state.DeviceTypeRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class DeviceTypeService {
	
	@Autowired
	private DeviceTypeRepository repository;
	
	public Result<?> listAll() {
		
		Result<DeviceType> result = new Result<>();
		
		try {
			List<DeviceType> lista = repository.findAll();

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
	
	public BasicResult<?> save(DeviceTypeDto dto) {
		
		Result<DeviceTypeDto> result = new Result<DeviceTypeDto>();		
		DeviceType deviceType = new DeviceType(dto);		
		deviceType = repository.save(deviceType);
		
		dto.setUid(deviceType.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<DeviceTypeDto> result = new Result<DeviceTypeDto>();		
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

