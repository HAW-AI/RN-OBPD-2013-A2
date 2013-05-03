package de.haw_hamburg.client;

import java.util.List;

import de.haw_hamburg.common.Message;
import de.haw_hamburg.common.User;

public class TextView implements View {

	TextView() {
	}

	@Override
	public void addMessage(Message message) {
		GUI.getGUI().addEntryToChatLogScrollPane(message.toString());
	}

	@Override
	public void setUsers(List<User> users) {
		GUI.getGUI().setClientsList(users);
	}

}
