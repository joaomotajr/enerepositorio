package br.com.eneeyes.main.result;

import java.util.List;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.UnitDto;

public class UnitResult extends BasicResult<UnitDto> {
			
	private List<UnitDto> list;
	private UnitDto unitDto;
	
	public List<UnitDto> getList() {
		return list;
	}

	public void setList(List<UnitDto> list) {
		this.list = list;
	}

	public UnitDto getUnit() {
		return unitDto;
	}

	public void setUnit(UnitDto unit) {
		this.unitDto = unit;
	}
	
	
	@Override
	public String toString() {
		return "UserResult{" +
				"UnitUnit=" + unitDto + "," +
				"resultType=" + getResultType() + "," +
				'}';
	}

	@Override
	public ResultMessageType getResultType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResultType(ResultMessageType resultType) {
		// TODO Auto-generated method stub
		
	}
	
}
