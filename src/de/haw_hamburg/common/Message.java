package de.haw_hamburg.common;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/**
	 * A message object gets created. If the message is illegal, null is
	 * returned.
	 * 
	 * @param message
	 * @return message object
	 */
	public static Message fromRawMessage(String message) {
		if (message == null) {
			return null;
		}
		Pattern pattern = Pattern.compile("(\\w+):(.*)");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			return Message.create(matcher.group(1), matcher.group(2).trim(),
					new Date());
		} else {
			return null;
		}

	}

}
