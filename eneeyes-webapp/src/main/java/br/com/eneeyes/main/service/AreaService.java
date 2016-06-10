package br.com.eneeyes.main.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AreaDto;
import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.repository.AreaRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.result.AreaResult;


@Named
public class AreaService implements IService<AreaDto> {

	@Inject
	private AreaRepository repository;
	
	public BasicResult<?> save(AreaDto dto) {
		AreaResult result = new AreaResult();
		
		Area area = Area.fromDtoToArea(dto);
		
		area = repository.save(area);
		dto.setUid(area.getUid());
				
		result.setArea(dto);
		
		result.setMessage("Executado com sucesso.");
		
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		AreaResult result = new AreaResult();
		
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
		
		Result<AreaDto> result = new Result<AreaDto>(); 	
		
		try {
			List<Area> lista = repository.findAll();

			if (lista != null) {
				result.setList(AreaDto.fromAreaToListDto(lista));
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

	public Result<AreaDto> listById(Long uid) {
		
		Result<AreaDto> result = new Result<AreaDto>();
		
		try {
			List<Area> lista = repository.findByCompanyID(uid);

			if (lista != null) {
				result.setList(AreaDto.fromAreaToListDto(lista));
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
}
