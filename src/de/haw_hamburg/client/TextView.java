package de.haw_hamburg.client;

import java.util.List;

import de.haw_hamburg.common.Message;
import de.haw_hamburg.common.User;

public class TextView implements View {

	TextView() {
	}

	@Override
	public void addMessage(Message message) {
		GUIView.getGUIView().addEntryToChatLogScrollPane(message.toString());
	}

	@Override
	public void setUsers(List<User> users) {
		GUIView.getGUIView().setClientsList(users);
	}

}
