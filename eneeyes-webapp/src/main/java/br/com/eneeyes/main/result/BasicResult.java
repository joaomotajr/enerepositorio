package br.com.eneeyes.main.result;

import java.util.Date;

public abstract class BasicResult<T> implements IResult {	
	
	private String message;
	private String systemMessage;	
	private Date serverDate = new Date();	
	private Boolean isError = false;	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public final String getSystemMessage() {
		return systemMessage;
	}
	
	public final void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}
	
	public final Date getServerDate() {
		return serverDate;
	}

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
}
