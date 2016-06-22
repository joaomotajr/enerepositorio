package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.ControllerDto;
import br.com.eneeyes.main.model.register.Controller;
import br.com.eneeyes.main.repository.register.ControllerRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;


@Named
public class ControllerService implements IService<ControllerDto> {

	@Inject
	private ControllerRepository repository;
	
	public BasicResult<?> save(ControllerDto dto) {
		
		Result<ControllerDto> result = new Result<ControllerDto>(); 	
		
		Controller controller = new Controller(dto);		
		controller = repository.save(controller);
		
		dto.setUid(controller.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<ControllerDto> result = new Result<ControllerDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Área Excluída.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<ControllerDto> result = new Result<ControllerDto>(); 	
		
		try {
			List<Controller> lista = repository.findAll();

			if (lista != null) {
				
				List<ControllerDto> dto = new ArrayList<ControllerDto>();
				
				for (Controller controller   : lista) {					
					dto.add(new ControllerDto(controller) );
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
		
		Result<ControllerDto> result = new Result<ControllerDto>();
		
		try {
			Controller item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new ControllerDto(item));
				
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
