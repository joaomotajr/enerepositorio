package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.TransmitterDto;
import br.com.eneeyes.main.model.register.Transmitter;
import br.com.eneeyes.main.repository.register.TransmitterRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;


@Named
public class TransmitterService implements IService<TransmitterDto> {

	@Inject
	private TransmitterRepository repository;
	
	public BasicResult<?> save(TransmitterDto dto) {
		
		Result<TransmitterDto> result = new Result<TransmitterDto>(); 	
		
		Transmitter transmitter = new Transmitter(dto);		
		transmitter = repository.save(transmitter);
		
		dto.setUid(transmitter.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<TransmitterDto> result = new Result<TransmitterDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Transmissor Excluída.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("Transmissor Não Pode Ser Excluído");
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<TransmitterDto> result = new Result<TransmitterDto>(); 	
		
		try {
			List<Transmitter> lista = repository.findAll();

			if (lista != null) {
				
				List<TransmitterDto> dto = new ArrayList<TransmitterDto>();
				
				for (Transmitter transmitter   : lista) {					
					dto.add(new TransmitterDto(transmitter) );
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
		
		Result<TransmitterDto> result = new Result<TransmitterDto>();
		
		try {
			Transmitter item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new TransmitterDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Transmissor.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
