package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.ManufacturerDto;
import br.com.eneeyes.main.model.register.Manufacturer;
import br.com.eneeyes.main.repository.register.ManufacturerRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;


@Named
public class ManufacturerService implements IService<ManufacturerDto> {

	@Inject
	private ManufacturerRepository repository;
	
	public BasicResult<?> save(ManufacturerDto dto) {
		
		Result<ManufacturerDto> result = new Result<ManufacturerDto>(); 	
		
		Manufacturer manufacturer = new Manufacturer(dto);		
		manufacturer = repository.save(manufacturer);
		
		dto.setUid(manufacturer.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<ManufacturerDto> result = new Result<ManufacturerDto>(); 	
		
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
		
		Result<ManufacturerDto> result = new Result<ManufacturerDto>(); 	
		
		try {
			List<Manufacturer> lista = repository.findAll();

			if (lista != null) {
				
				List<ManufacturerDto> dto = new ArrayList<ManufacturerDto>();
				
				for (Manufacturer manufacturer   : lista) {					
					dto.add(new ManufacturerDto(manufacturer) );
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
		
		Result<ManufacturerDto> result = new Result<ManufacturerDto>();
		
		try {
			Manufacturer item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new ManufacturerDto(item));
				
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
