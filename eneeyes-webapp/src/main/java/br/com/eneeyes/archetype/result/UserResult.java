package br.com.eneeyes.archetype.result;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.eneeyes.archetype.dto.user.UserDto;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.web.result.ResultBase;
import br.com.eneeyes.archetype.web.result.ResultMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;

@XmlRootElement
public class UserResult extends ResultBase<User> {
	
	private static final long serialVersionUID = -5617762922526451645L;	
	private User user;
	private List<UserDto> listUser;

	public UserResult() {
	}
	
	public UserResult(ResultMessageType resultType, ResultMessage message) {
        setResultType(resultType);
		List<ResultMessage> messages = new ArrayList<ResultMessage>();
		messages.add(message);
		setMessages(messages);
	}
	
	public UserResult(ResultMessageType resultType, List<ResultMessage> messages, User user) {
        setResultType(resultType);
        setMessages(messages);
		this.user = user;
	}

	@XmlAttribute
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@XmlAttribute
	public List<UserDto> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserDto> listUser) {
		this.listUser = listUser;
	}
	
	@Override
	public String toString() {
		return "UserResult{" +
				"user=" + user + "," +
				"resultType=" + getResultType() + "," +
				'}';
	}
	
}
