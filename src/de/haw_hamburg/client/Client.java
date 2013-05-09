package de.haw_hamburg.client;

import de.haw_hamburg.common.User;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static Client client;
    private ServerCommunicator serverCommunicator;
    private StartDialog startDialog;
    private GUI gui;
    private List<User> userList;
    private User currentUser;
    private String serverHostName;

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

    void setServerHostName(String serverHostName) {
        this.serverHostName = serverHostName;
    }

    void startDialogFinished() {
        this.startDialog = null;
    }
}
