package de.haw_hamburg.client;

import java.util.List;

import de.haw_hamburg.common.Message;
import de.haw_hamburg.common.User;

public class TextView implements View {

	@Override
	public void addMessage(Message message) {
		System.out.println("Received message: "+message);
		
	}

	@Override
	public void setUsers(List<User> users) {
		System.out.println("New List of Users: "+users);
	}

}
