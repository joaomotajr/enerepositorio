package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.GenericDto;
import br.com.eneeyes.main.model.register.Generic;
import br.com.eneeyes.main.repository.register.GenericRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;

@Service
public class GenericService implements IService<GenericDto> {

	@Autowired
	private GenericRepository repository;
	
	public BasicResult<?> save(GenericDto dto) {
		
		Result<GenericDto> result = new Result<GenericDto>(); 	
		
		Generic generic = new Generic(dto);		
		generic = repository.save(generic);
		
		dto.setUid(generic.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<GenericDto> result = new Result<GenericDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Dispositivo Excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("Dispositivo Não Pode Ser Excluído.");
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<GenericDto> result = new Result<GenericDto>(); 	
		
		try {
			List<Generic> lista = repository.findAll();

			if (lista != null) {
				
				List<GenericDto> dto = new ArrayList<GenericDto>();
				
				for (Generic generic   : lista) {					
					dto.add(new GenericDto(generic) );
				}
				
				result.setList(dto);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Dispositivo.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}


	public Result<?> findOne(Long uid) {
		
		Result<GenericDto> result = new Result<GenericDto>();
		
		try {
			Generic item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new GenericDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Dispositivo.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
