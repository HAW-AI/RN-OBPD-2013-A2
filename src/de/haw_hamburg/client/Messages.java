package de.haw_hamburg.client;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Messages {

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
