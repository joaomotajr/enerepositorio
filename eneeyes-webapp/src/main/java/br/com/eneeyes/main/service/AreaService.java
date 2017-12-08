package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AreaDto;
import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.repository.AreaRepository;
import br.com.eneeyes.main.repository.UnitRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class AreaService implements IService<AreaDto> {

	@Autowired
	private AreaRepository repository;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private LogAuditoriaService logAuditoriaService;
	
	public BasicResult<?> save(AreaDto dto) {
		LogResult<AreaDto> result = new LogResult<AreaDto>();		
		ActionType actionType = dto.getUid() == null || dto.getUid() == 0 ? ActionType.CREATE : ActionType.UPDATE;
		
		Unit unit = unitRepository.findByUid(dto.getUnitDto().getUid());
		
		Area area = new Area(dto);
		area.setUnit(unit);
		
		area = repository.save(area);
		dto.setUid(area.getUid());
				
		result.setEntity(dto);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Área Gravada com sucesso.");	
		
		logAuditoriaService.save(this.getClass().getSimpleName(), actionType, result.toString());
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<AreaDto> result = new LogResult<AreaDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Área Excluída.");
			
			logAuditoriaService.save(this.toString(), ActionType.DELETE, result.toString());
			
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
				result.setMessage("Nenhuma Área.");
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
				result.setMessage("Nenhuma Área.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
