package br.com.eneeyes.main.service;

import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.result.CompanyResult;

public interface ICompanyService {

	public CompanyResult save(CompanyDto dto);
	
	public CompanyResult findAll(CompanyDto dto);
	
	public CompanyResult findOne(Long uid);
			
	public CompanyResult findByFilialId(CompanyDto dto);
	
	public CompanyResult update(CompanyDto dto);
			
	public CompanyResult delete(long uid);

}
