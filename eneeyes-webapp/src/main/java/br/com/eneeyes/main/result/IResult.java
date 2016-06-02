package br.com.eneeyes.main.result;

import br.com.eneeyes.archetype.web.result.ResultMessageType;

public interface IResult {

	public String getMessage();
	public void setMessage(String message);	
	
	public ResultMessageType getResultType();	
	public void setResultType(ResultMessageType resultType);	
	
}
