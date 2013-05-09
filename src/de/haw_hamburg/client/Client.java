package de.haw_hamburg.client;

public class Client {
	private static Client client;
	private ServerCommunicator serverCommunicator;
	private GUI gui;

	private Client() {
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

	public ServerCommunicator getServerCommunicator() {
		return this.serverCommunicator;
	}

	public void setServerCommunicator(ServerCommunicator serverCommunicator) {
		if (serverCommunicator != null) {
			this.serverCommunicator = serverCommunicator;
		}
	}

	private static Client createClient() {
		Client client = new Client();

		// GUI thread
		GUI gui = GUI.createGUI(client);
		client.setGUI(gui);
		gui.start();

		return client;
	}

	private void setGUI(GUI gui) {
		if (gui != null) {
			this.gui = gui;
		}
	}
}
