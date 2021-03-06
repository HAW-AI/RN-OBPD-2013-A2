/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.haw_hamburg.client;

import de.haw_hamburg.common.User;

import java.net.SocketException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class GUI {

    private static GUI gui;
    private GUIView guiView;
    private Client client;
    private StartDialog startDialog;
    
    private Logger LOG=Logger.getLogger(GUI.class.getName());

    private GUI() {
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
        gui.guiView = new GUIView(gui);
        gui.startDialog = StartDialog.create(gui.guiView, true, gui);
        gui.startDialog.setVisible(true);
        gui.guiView.setUserName(client.getCurrentUser().getName());
        try {
			ClientCommunicatorReceiver.create(gui.guiView).start();
		} catch (SocketException e) {
			gui.LOG.warning("Failed to start receiver: "+e.getMessage());
		}
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
