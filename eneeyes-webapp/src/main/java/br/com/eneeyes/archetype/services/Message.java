package br.com.eneeyes.archetype.services;

public class Message {
	private String to;
	private String smsCount;
	private String messageId;
	private Status status;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSmsCount() {
		return smsCount;
	}
	public void setSmsCount(String smsCount) {
		this.smsCount = smsCount;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}	
}
