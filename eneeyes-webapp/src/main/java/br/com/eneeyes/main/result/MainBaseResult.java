package br.com.eneeyes.main.result;

import br.com.eneeyes.archetype.web.result.ResultMessageType;

public abstract class MainBaseResult<T> {
	
	private ResultMessageType resultType;
	
	public ResultMessageType getResultType() {
		return resultType;
	}
	
	public void setResultType(ResultMessageType resultType) {
		this.resultType = resultType;
	}
}
