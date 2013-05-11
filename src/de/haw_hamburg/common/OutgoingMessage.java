package de.haw_hamburg.common;

import java.nio.charset.Charset;

public class OutgoingMessage {
	private String userName;
	private String content;

	private OutgoingMessage(String userName, String content) {
		this.userName = userName;
		this.content = content;
	}

	public byte[] toMessageByteArray() {
		return (this.userName + ": " + this.content + "\n").getBytes(Charset
				.forName("UTF-8"));
	}

	public static OutgoingMessage createOutgoingMessage(String userName,
			String content) {
		if (userName != null && userName.length() <= 20 && content != null
				&& content.length() <= 100) {
			return new OutgoingMessage(userName, content);
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.userName + ": " + this.content;
	}
}
