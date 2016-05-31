package br.com.eneeyes.archetype.result;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.archetype.web.result.ResultMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;


@XmlRootElement
public class TaxaResult extends ResultBase<User> {
	
	private static final long serialVersionUID = -5617762922526451645L;	


	public TaxaResult() {
	}
	
	public TaxaResult(ResultMessageType resultType, ResultMessage message) {
        setResultType(resultType);
		List<ResultMessage> messages = new ArrayList<ResultMessage>();
		messages.add(message);
		setMessages(messages);
	}

	
	@Override
	public String toString() {
		return "TaxaResult{" +
				"resultType=" + getResultType() + "," +
				'}';
	}
	
}
