package br.com.eneeyes.main.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.UnitDto;
import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.repository.UnitRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class UnitService implements IService<UnitDto> {
	
	@Inject
	private UnitRepository repository;
	
	public BasicResult<?> save(UnitDto dto) {
		Result<UnitDto> result = new Result<UnitDto>(); 	
		
		Unit unit = Unit.fromDtoToUnit(dto);
		
		unit = repository.save(unit);
		dto.setUid(unit.getUid());		
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<UnitDto> result = new Result<UnitDto>(); 	
		
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
		
		Result<UnitDto> result = new Result<UnitDto>(); 	
		
		try {
			List<Unit> lista = repository.findAll();

			if (lista != null) {
				result.setList(UnitDto.fromUnitToListDto(lista));
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
	
	public Result<UnitDto> findOne(Long uid) {
		
		Result<UnitDto> result = new Result<UnitDto>();
		
		try {
			Unit item = repository.findOne(uid);

			if (item != null) {
				
				
				
				result.setEntity(UnitDto.fromUnitToDto(item));				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma area.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}

	public int setParent(Long id, Long parentId) {
		// TODO Auto-generated method stub
		return 0;
	}

		
}
