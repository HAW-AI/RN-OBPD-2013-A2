package de.haw_hamburg.client;

import de.haw_hamburg.common.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Client extends Thread {

    private static Client client;
    private ServerCommunicator serverCommunicator;
    private GUI gui;
    private List<User> userList;
    private User currentUser;
    private String serverHostName;
    private final Logger LOG;

    private Client() {
        this.LOG = Logger.getLogger(Client.class.toString());
    }

    // client singleton
    public static Client getClient() {
        if (Client.client == null) {
            Client.client = new Client();
            return client;
        } else {
            return client;
        }
    }

    public static Client createClient() {
        return getClient();
    }
    
    public void run() {
        launchGUI();
        while(!isInterrupted()) {
            
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

    public GUI getGUI() {
        return this.gui;
    }

    private void setGUI(GUI gui) {
        if (gui != null) {
            this.gui = gui;
        }
    }

    public void setUserList(List<User> userList) {
        synchronized (userList) {
            this.userList = userList;
        }
    }

    public List<User> getUserList() {
        // defensive copy
        return new ArrayList<User>(this.userList);
    }

    public List<User> getUserListWithoutUs() {
        // defensive copy
        List<User> list = getUserList();
        list.remove(getCurrentUser());
        return new ArrayList<User>(list);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        if (currentUser != null) {
            this.currentUser = currentUser;
        }
    }

    public void setUserName(String userName) {
        setCurrentUser(User.create(userName, "localhost"));
    }

    public void setServerHostname(String serverHostName) {
        this.serverHostName = serverHostName;
    }

    public void startDialogFinished() {
        launchServerCommunicator();
        launchGUI();
    }

    private void launchServerCommunicator() {
        try {
            setServerCommunicator(serverCommunicator.create(getServerHostname(), getCurrentUser().getName(), this));
        } catch (IOException ex) {
            LOG.severe("could not launch server communicator");
        }
    }

    public String getServerHostname() {
        return this.serverHostName;
    }

    private void launchGUI() {
        GUI gui = GUI.createGUI(client);
        client.setGUI(gui);
        gui.start();
    }
}
