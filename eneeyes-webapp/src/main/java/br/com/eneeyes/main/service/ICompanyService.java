package br.com.eneeyes.main.service;

import br.com.eneeyes.archetype.dto.Result;
import br.com.eneeyes.main.dto.CompanyDto;

public interface ICompanyService {

	public Result<?> save(CompanyDto dto);
	
	public Result<?> findAll(CompanyDto dto);
	
	public Result<?> findOne(Long uid);	
		
	public Result<?> update(CompanyDto dto);
			
	public Result<?> delete(long uid);
	
	
}
