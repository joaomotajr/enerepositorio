package br.com.eneeyes.main.result;

import java.util.List;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.AreaDto;



public class AreaResult extends BasicResult<AreaDto> {
	
	private List<AreaDto> list;
	private AreaDto areaDto;
	
	public List<AreaDto> getList() {
		return list;
	}

	public void setList(List<AreaDto> list) {
		this.list = list;
	}

	public AreaDto getArea() {
		return areaDto;
	}

	public void setArea(AreaDto area) {
		this.areaDto = area;
	}
	
	
	@Override
	public String toString() {
		return "UserResult{" +
				"Unity=" + areaDto + "," +
				"resultType=" + getResultType() + "," +
				'}';
	}

	public ResultMessageType getResultType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setResultType(ResultMessageType resultType) {
		// TODO Auto-generated method stub
		
	}
	
}
