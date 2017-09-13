package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.GasDto;
import br.com.eneeyes.main.model.register.Gas;
import br.com.eneeyes.main.repository.register.GasRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;

@Service
public class GasService implements IService<GasDto> {

	@Autowired
	private GasRepository repository;
	
	public BasicResult<?> save(GasDto dto) {
		
		Result<GasDto> result = new Result<GasDto>(); 	
		
		Gas gas = new Gas(dto);		
		gas = repository.save(gas);
		
		dto.setUid(gas.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<GasDto> result = new Result<GasDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Gás Excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("Gás Não Pode Ser Excluído");
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<GasDto> result = new Result<GasDto>(); 	
		
		try {
			List<Gas> lista = repository.findAll();

			if (lista != null) {
				
				List<GasDto> dto = new ArrayList<GasDto>();
				
				for (Gas gas   : lista) {					
					dto.add(new GasDto(gas) );
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
		
		Result<GasDto> result = new Result<GasDto>();
		
		try {
			Gas item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new GasDto(item));
				
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
