package br.com.eneeyes.archetype.web.result;

public class ResultErrorMessage extends ResultMessage {

	private static final long serialVersionUID = 9190548557899701844L;

	public ResultErrorMessage() {
		super();
	}
	
	public ResultErrorMessage(String message) {
		super(ResultMessageType.ERROR, message);
	}

}
