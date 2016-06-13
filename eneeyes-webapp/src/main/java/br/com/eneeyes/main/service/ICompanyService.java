package br.com.eneeyes.main.service;

import javax.inject.Named;

import br.com.eneeyes.archetype.dto.Result;
import br.com.eneeyes.main.dto.UnitDto;

@Named
public interface ICompanyService {

	public Result<?> save(UnitDto dto);
	
	public Result<?> findAll(UnitDto dto);
	
	public Result<?> findOne(Long uid);	
		
	public Result<?> update(UnitDto dto);
			
	public Result<?> delete(long uid);
}
