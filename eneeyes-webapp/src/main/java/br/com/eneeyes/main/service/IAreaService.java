package br.com.eneeyes.main.service;

import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.dto.UnitDto;

public interface IAreaService {
	
	public Result<?> save(UnitDto dto);
	
	public Result<?> findAll(UnitDto dto);
	
	public Result<?> findOne(Long uid);	
		
	public Result<?> update(UnitDto dto);
			
	public Result<?> delete(long uid);

}
