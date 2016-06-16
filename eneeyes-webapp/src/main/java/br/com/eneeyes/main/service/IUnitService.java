package br.com.eneeyes.main.service;

import br.com.eneeyes.archetype.dto.Result;
import br.com.eneeyes.main.dto.UnitDto;

public interface IUnitService {

	public Result<?> save(UnitDto dto);
	
	public Result<?> findAll(UnitDto dto);
	
	public Result<?> findOne(Long uid);	
		
	public Result<?> update(UnitDto dto);
			
	public Result<?> delete(long uid);

}
