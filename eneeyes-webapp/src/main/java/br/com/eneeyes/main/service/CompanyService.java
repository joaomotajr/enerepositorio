package br.com.eneeyes.main.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.repository.CompanyRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class CompanyService implements IService<CompanyDto> {
	
	@Inject
	private CompanyRepository repository;
	
	public BasicResult<?> save(CompanyDto dto) {
		Result<CompanyDto> result = new Result<CompanyDto>(); 	
		
		Company company = Company.fromDtoToCompany(dto);
		
		company = repository.save(company);
		dto.setUid(company.getUid());
		
		result.setEntity(CompanyDto.fromCompanyToDto(company));
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<CompanyDto> result = new Result<CompanyDto>(); 	
		
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
		
		Result<CompanyDto> result = new Result<CompanyDto>(); 	
		
		try {
			List<Company> lista = repository.findAll();

			if (lista != null) {
				result.setList(CompanyDto.fromCompanyToListDto(lista));
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
