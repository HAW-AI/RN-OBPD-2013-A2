package de.haw_hamburg.client;

import java.util.TimerTask;
import java.util.logging.Logger;

import de.haw_hamburg.responses.ListResponse;
import de.haw_hamburg.responses.Response;

public class UserListUpdateTask extends TimerTask {

	private ServerCommunicator communicator;
	private View view;

	private static Logger LOG = Logger.getLogger(UserListUpdateTask.class
			.getName());

	private UserListUpdateTask(ServerCommunicator communicator, View view) {
		this.communicator = communicator;
		this.view = view;
	}

	static UserListUpdateTask create(ServerCommunicator communicator, View view) {
		return new UserListUpdateTask(communicator, view);
	}

	@Override
	public void run() {
		Response response = communicator.refreshUserList();
		if (response.isList()) {
			ListResponse listResponse = (ListResponse) response;
			view.setUsers(listResponse.getList());
		} else {
			LOG.warning("Could not get user list. Expected LIST response, got: "
					+ response);
		}

	}

}
