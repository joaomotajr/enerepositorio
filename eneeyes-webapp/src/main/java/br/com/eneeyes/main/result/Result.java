package br.com.eneeyes.main.result;

import java.util.List;

import br.com.eneeyes.archetype.web.result.ResultMessageType;

public class Result<T> extends BasicResult<T> {
	
	private List<T> list;
	private T dto;
	private ResultMessageType resultMessageType;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public T getT() {
		return dto;
	}

	public void setEntity(T dto) {
		this.dto = dto;
	}	
	
	@Override
	public String toString() {
		return "UserResult{" +
				"Company=" + dto + "," +
				"resultType=" + getResultType() + "," +
				'}';
	}

	public ResultMessageType getResultType() {
		return resultMessageType;
	}

	public void setResultType(ResultMessageType resultMessageType) {
		this.resultMessageType = resultMessageType;
	}
	
}
