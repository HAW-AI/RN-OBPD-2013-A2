package de.haw_hamburg.client;

public class Client {
	private static Client client;
	private ServerCommunicator serverCommunicator;
	private GUI gui;
	private View textview;

	private Client(GUI gui, View textview) {
		this.gui = gui;
		this.textview = textview;
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
		// GUI thread
		GUI gui = GUI.getGUI();
		gui.start();

		// bridges the GUI and the UserListUpdateTask
		View textview = new TextView();

		// Start Server Thread which keeps a TCP connection to the Server open
		// ServerCommunicator serverCommunicator = ServerCommunicator
		// .getServerCommunicator(GUI.getGUI().getUsername());
		// serverCommunicator.start();

		return new Client(gui, textview);
	}
}
