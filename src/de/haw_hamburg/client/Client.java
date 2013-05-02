package de.haw_hamburg.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.haw_hamburg.common.User;
import de.haw_hamburg.responses.ListResponse;
import de.haw_hamburg.responses.Response;

public class Client {
	private static Client client;
	private List<User> users;
	private ServerCommunicator serverCommunicator;
	private GUI gui;

	private Client(GUI gui, ServerCommunicator serverCommunicator) {
		this.gui = gui;
		this.serverCommunicator = serverCommunicator;
	}

	// client singleton
	public static Client getClient() {
		if (Client.client == null) {
			Client.client = createClient();
			return client;
		} else {
			return client;
		}
	}

	private static Client createClient() {
		GUI gui = GUI.getGUI();
		gui.start();

		// Start Server Thread which keeps a TCP connection to the Server open
		ServerCommunicator serverCommunicator = ServerCommunicator.getServerCommunicator(GUI
				.getUsername());
		serverCommunicator.start();

		return new Client(gui, serverCommunicator);
	}

	public List<User> getUsers() {
		return serverCommunicator.getUsers();
	}
}
