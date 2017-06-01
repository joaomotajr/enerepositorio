package br.com.eneeyes.archetype.result;

import java.util.ArrayList;
import java.util.List;

import br.com.eneeyes.archetype.model.User;
import br.com.eneeyes.archetype.web.result.ResultCollection;
import br.com.eneeyes.archetype.web.result.ResultMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;

public class UserCollectionResult extends ResultCollection<User> {
	private static final long serialVersionUID = 882068545464383292L;

	public UserCollectionResult() {}
	
	public UserCollectionResult(ResultMessageType resultType,
			ResultMessage message) {
		setResultType(resultType);
		List<ResultMessage> messages = new ArrayList<ResultMessage>();
		messages.add(message);
		setMessages(messages);
	}

	public UserCollectionResult(ResultMessageType resultType,
			List<ResultMessage> messages, long page) {
		setResultType(resultType);
        setMessages(messages);
		setPage(page);
	}

	public UserCollectionResult(ResultMessageType resultType,
			List<ResultMessage> messages, long page, long offset) {
        setResultType(resultType);
        setMessages(messages);
		setPage(page);
		setOffset(offset);
	}

	public UserCollectionResult(ResultMessageType resultType,
			List<ResultMessage> messages, long page, long offset, long total) {
        setResultType(resultType);
        setMessages(messages);
		setPage(page);
		setOffset(offset);
		setTotal(total);
	}

	public UserCollectionResult(ResultMessageType resultType,
			List<ResultMessage> messages, long page, long offset, long total,
			User[] items) {
        setResultType(resultType);
        setMessages(messages);
		setPage(page);
		setOffset(offset);
		setTotal(total);
		setItems(items);
	}
}