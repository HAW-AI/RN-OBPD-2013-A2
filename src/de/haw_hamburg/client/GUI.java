/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.haw_hamburg.client;

import de.haw_hamburg.common.User;
import java.util.List;

/**
 * 
 * @author patrick
 */
public class GUI extends Thread {

	private static GUI gui;
	private GUIView guiView;
	private Client client;

	private GUI() {
		this.guiView = new GUIView(this);
	}

	public static GUI getGUI() {
		if (GUI.gui == null) {
			GUI.gui = new GUI();
		}
		return GUI.gui;
	}

	public static GUI createGUI(Client client) {
		GUI gui = getGUI();
		gui.setClient(client);
		return gui;
	}

	private void setClient(Client client) {
		if (client != null) {
			this.client = client;
		}
	}
        
        public Client getClient() {
            return this.client;
        }
        
        private GUIView getGUIView() {
            return this.guiView;
        }

    public void setUserList(List<User> newUserList) {
        getGUIView().setUserList(newUserList);
    }
}
