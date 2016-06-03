package br.com.eneeyes.main.service;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.repository.CompanyRepository;
import br.com.eneeyes.main.result.CompanyResult;


@Named
public class CompanyService implements ICompanyService {
	
	@Inject
	private CompanyRepository repository;

	@Override
	public CompanyResult save(CompanyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyResult findAll(CompanyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyResult findByFilialId(Long dto) {
		return null;
	}

	@Override
	public CompanyResult update(CompanyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyResult delete(long uid) {
		CompanyResult result = new CompanyResult();
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Funcionário Excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result; 
	}

	@Override
	public CompanyResult findOne(Long uid) {
		
		Company company = (repository.findOne(uid));
		CompanyResult result = new CompanyResult();
		
		result.setCompany(CompanyDto.fromCompanyToDto(company));
		result.setMessage("Executado com sucesso.");
		
		return result;

	}
		
	
}
