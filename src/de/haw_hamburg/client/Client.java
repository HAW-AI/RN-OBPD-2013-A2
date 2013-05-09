package de.haw_hamburg.client;

import de.haw_hamburg.common.User;
import java.util.ArrayList;
import java.util.List;

public class Client {
	private static Client client;
	private ServerCommunicator serverCommunicator;
	private GUI gui;
        private List<User> userList;

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
        
        public GUI getGUI() {
            return this.gui;
        }

	private void setGUI(GUI gui) {
		if (gui != null) {
			this.gui = gui;
		}
	}
        
        public void setUserList(List<User> userList) {
            synchronized(userList) {
                this.userList = userList;
            }
        }
        
        public List<User> getUserList() {
            // defensive copy
            return new ArrayList<User>(this.userList); 
        }
}
