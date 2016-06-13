package br.com.eneeyes.main.service;

import br.com.eneeyes.main.dto.UnitDto;
import br.com.eneeyes.main.result.UnitResult;

public interface IUnitService {

	public UnitResult save(UnitDto dto);
	
	public UnitResult findAll(UnitDto dto);
	
	public UnitResult findOne(Long uid);

	public UnitResult findByFilialId(Long filialID);
		
	public UnitResult update(UnitDto dto);
			
	public UnitResult delete(long uid);

}
