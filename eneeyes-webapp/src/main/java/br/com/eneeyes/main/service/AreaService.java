package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AreaDto;
import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.repository.AreaRepository;
import br.com.eneeyes.main.repository.UnitRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class AreaService implements IService<AreaDto> {

	@Inject
	private AreaRepository repository;
	
	@Inject
	private UnitRepository unitRepository;
	
	public BasicResult<?> save(AreaDto dto) {
		Result<AreaDto> result = new Result<AreaDto>();		
		
		Unit unit = unitRepository.findByUid(dto.getUnitDto().getUid());
		
		Area area = new Area(dto);
		area.setUnit(unit);
		
		area = repository.save(area);
		dto.setUid(area.getUid());
				
		result.setEntity(dto);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<AreaDto> result = new Result<AreaDto>(); 	
		
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
				
				List<AreaDto> dto = new ArrayList<AreaDto>();
				
				for (Area area   : lista) {					
					dto.add(new AreaDto(area) );
				}
								
				result.setList(dto);
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

	public Result<?> findOne(Long uid) {
		
		Result<AreaDto> result = new Result<AreaDto>();
		
		try {
			Area item = repository.findOne(uid);

			if (item != null) {
				result.setEntity(new AreaDto(item));
				
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
