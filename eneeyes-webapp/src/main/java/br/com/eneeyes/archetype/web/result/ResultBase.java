package br.com.eneeyes.archetype.web.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class ResultBase<T> implements Serializable {
	private static final long serialVersionUID = 9127575343123707281L;

	private ResultMessageType resultType;

	private List<ResultMessage> messages = new ArrayList<ResultMessage>();

	@XmlAttribute
	public ResultMessageType getResultType() {
		return resultType;
	}

	public void setResultType(ResultMessageType resultType) {
		this.resultType = resultType;
	}

    @XmlAttribute
	public List<ResultMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ResultMessage> messages) {
		this.messages = messages;
	}

    @XmlAttribute
    public boolean hasError() {
        return !getErrorMessages().isEmpty();
    }

    @XmlAttribute
    public boolean hasSuccess() {
        return !getSuccessMessages().isEmpty();
    }

    @XmlAttribute
	public List<ResultErrorMessage> getErrorMessages() {
		List<ResultErrorMessage> errorsMessages = new ArrayList<ResultErrorMessage>();
		for(ResultMessage message : getMessages()) {
			if(ResultMessageType.ERROR.equals(message.getType()))
				errorsMessages.add((ResultErrorMessage)message);
		}

        if (errorsMessages.size() < 1) {
            setErrorMessages(errorsMessages);
        }

		return errorsMessages;
	}
	
	public void setErrorMessages(List<ResultErrorMessage> messages) {
		for(ResultErrorMessage errorMessage : messages) {
			getMessages().add(errorMessage);
		}
	}

    @XmlAttribute
	public List<ResultSuccessMessage> getSuccessMessages() {
		List<ResultSuccessMessage> successMessages = new ArrayList<ResultSuccessMessage>();
		for(ResultMessage message : getMessages()) {
			if(ResultMessageType.SUCCESS.equals(message.getType()))
				successMessages.add((ResultSuccessMessage)message);
		}

		return successMessages;
	}
	
	public void setSuccessMessages(List<ResultSuccessMessage> messages) {
		for(ResultSuccessMessage successMessage : messages) {
			getMessages().add(successMessage);
		}
	}

    public void addMessage(ResultMessage message) {
        setResultType(message.getType());
		getMessages().add(message);
    }

    public void clearMessages() { setMessages(new ArrayList<ResultMessage>()); }
}
