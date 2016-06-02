package br.com.eneeyes.main.service;

import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.result.CompanyResult;

public interface ICompanyService extends IBaseService<Company> {

	public CompanyResult save(CompanyDto dto);
	
	public CompanyResult findAll(CompanyDto dto);
			
	public CompanyResult findByFilialId(CompanyDto dto);
	
	public CompanyResult update(CompanyDto dto);
			
	public CompanyResult delete(CompanyDto dto);

}
