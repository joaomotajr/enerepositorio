package br.com.eneeyes.archetype.web.result;

import java.io.Serializable;

public class ResultMessage implements Serializable {
	private static final long serialVersionUID = -5388651292147355801L;

	private ResultMessageType type;
	
	private String message;

	public ResultMessage() {
		super();
	}

	public ResultMessage(ResultMessageType type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public ResultMessageType getType() {
		return type;
	}

	public void setType(ResultMessageType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultMessage [type=" + type + ", message=" + message + "]";
	}
}
