package br.com.eneeyes.main.service.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.state.UnitMeterDto;
import br.com.eneeyes.main.model.state.UnitMeter;
import br.com.eneeyes.main.repository.state.UnitMeterRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class UnitMeterService {
	
	@Autowired
	private UnitMeterRepository repository;
	
	public Result<?> listAll() {
		
		Result<UnitMeter> result = new Result<>();
		
		try {
			List<UnitMeter> lista = repository.findAll();

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
	
	public BasicResult<?> save(UnitMeterDto dto) {
		
		Result<UnitMeterDto> result = new Result<UnitMeterDto>();		
		UnitMeter unitMeter = new UnitMeter(dto);		
		unitMeter = repository.save(unitMeter);
		
		dto.setUid(unitMeter.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<UnitMeterDto> result = new Result<UnitMeterDto>();		
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