package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.UnitDto;
import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.model.Unit;
import br.com.eneeyes.main.repository.CompanyRepository;
import br.com.eneeyes.main.repository.UnitRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class UnitService implements IService<UnitDto> {
	
	@Autowired
	private UnitRepository repository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public BasicResult<?> save(UnitDto dto) {
		Result<UnitDto> result = new Result<UnitDto>();
		
		Company company = companyRepository.findByUid(dto.getCompanyDto().getUid());
		
		Unit unit = new Unit(dto);
		unit.setCompany(company);		
		unit = repository.save(unit);
		
		dto.setUid(unit.getUid());		
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Unidade Gravada com Sucesso.");					
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<UnitDto> result = new Result<UnitDto>(); 	
		
		try {			
			repository.delete(uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Unidade Excluída.");
			
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
				
				List<UnitDto> dto = new ArrayList<UnitDto>();
				
				for (Unit unit   : lista) {					
					dto.add(new UnitDto(unit) );
				}
				
				result.setList(dto);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Unidade.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	
	public Result<?> listByCompany(Long companyId) {
		
		Result<UnitDto> result = new Result<UnitDto>(); 	
		
		try {
			List<Unit> lista = repository.findByCompanyID(companyId);

			if (lista != null) {				
				
				List<UnitDto> dto = new ArrayList<UnitDto>();
				
				for (Unit unit   : lista) {					
					dto.add(new UnitDto(unit) );
				}
				
				result.setList(dto);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Unidade.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public Result<?> findOne(Long uid) {
		
		Result<UnitDto> result = new Result<UnitDto>();
		
		try {
			Unit item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new UnitDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Unidade.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}

		
}
