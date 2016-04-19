package br.com.eneeyes.archetype.web.result;

public class ResultSuccessMessage extends ResultMessage {

	private static final long serialVersionUID = -7467946779315368176L;

	public ResultSuccessMessage() {
		super();
	}

	public ResultSuccessMessage(String message) {
		super(ResultMessageType.SUCCESS, message);
	}

}
