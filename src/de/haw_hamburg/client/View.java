package de.haw_hamburg.client;

import java.util.List;

import de.haw_hamburg.common.Message;
import de.haw_hamburg.common.User;

public interface View {
	
	void addMessage(Message message);
	
	void setUsers(List<User> users);

}
