package br.com.eneeyes.main.result;

import java.util.Date;

import br.com.eneeyes.archetype.web.result.ResultMessageType;

public interface IResult {
	
	public Date getServerDate();
	
	public Boolean getIsError();
	public void setIsError(Boolean isError); 

	public String getMessage();
	public void setMessage(String message);	
	
	public ResultMessageType getResultType();	
	public void setResultType(ResultMessageType resultType);	
	
}
