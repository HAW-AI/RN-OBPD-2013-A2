package de.haw_hamburg.client;

import java.util.Date;

public class Message {

	private String userName;
	private String content;
	private Date receptionDate;

	private Message(String userName, String content, Date receptionDate) {
		this.userName = userName;
		this.content = content;
		this.receptionDate = receptionDate;
	}

	static Message create(String userName, String content, Date receptionDate) {
		return new Message(userName, content, receptionDate);
	}

	public String getUserName() {
		return userName;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return userName + "[" + receptionDate + "]: " + content;
	}

	public Date getReceptionDate() {
		return receptionDate;
	}

}
