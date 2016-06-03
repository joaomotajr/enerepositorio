package br.com.eneeyes.main.result;

public abstract class BasicResult<T> implements IResult {	
	
	private String message;
	private Boolean isError = false;	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
}
