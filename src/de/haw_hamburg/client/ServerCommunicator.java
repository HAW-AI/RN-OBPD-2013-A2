package de.haw_hamburg.client;

import de.haw_hamburg.common.ChatComponent;
import de.haw_hamburg.common.User;
import de.haw_hamburg.requests.Request;
import de.haw_hamburg.requests.Requests;
import de.haw_hamburg.responses.ListResponse;
import de.haw_hamburg.responses.Response;
import de.haw_hamburg.responses.Responses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

public class ServerCommunicator extends ChatComponent {

    private static final Integer SERVER_PORT = 50000;
    private static final Integer CLIENT_LIST_REFRESH_PERIOD = 2000;
    private Socket socket;
    private String userName;
    private final Client client;
    private final Logger LOG;

    private ServerCommunicator(Socket socket, BufferedReader in,
			PrintWriter out, String userName, Client client) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.userName = userName;
        this.client = client;
        this.LOG = Logger.getLogger(ClientCommunicator.class.toString());
    }
    
    public static ServerCommunicator create(String serverHostname, String userName, Client client)
            throws IOException {
        InetAddress serverAddress = InetAddress.getByName(serverHostname);

        Socket socket = new Socket(serverAddress, SERVER_PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        return new ServerCommunicator(socket, in, out, userName, client);
    }

    public void refreshUserList() {
        LOG.info("refreshing userlist");
        Response response = sendRequestAndWaitForResponse(Requests.info());
        if (response.isList()) {
            List<User> newUserList = ((ListResponse) response).getList();
            getClient().setUserList(newUserList);
            getGUI().setUserList(newUserList);
            
        } else {
            // in the event of an error disconnect and shutdown
            LOG.severe("There was an error trying to refresh the user list");
            LOG.severe(response.toString());
            disconnect();
        }
        
    }

    public void run() {
        try {
            LOG.info("ServerCommunicator started");

            LOG.info("sending new user request");
            Response nameResponse = sendRequestAndWaitForResponse(Requests
                    .newUser(this.userName));

            if (nameResponse.isError()) {
                LOG.severe("new user request had an error reply");
                LOG.severe("Name Response: " + nameResponse.toString());
                disconnect();
            } else {
                while (!isInterrupted()) {
                    refreshUserList();
                    sleep(CLIENT_LIST_REFRESH_PERIOD);
                }
            }
        } catch (InterruptedException e) {
        }
    }

    private void disconnect() {
        LOG.info("closing the Servercommunicator");
        try {
            interrupt();
            socket.close();
        } catch (IOException e) {
        }
    }

    private Response sendRequestAndWaitForResponse(Request request) {
        Response response = Responses.unknown();
        try {
            LOG.info("sending request " + request.toString());
            println(request);
            response = Responses.fromRawResponse(readLine());
        } catch (IOException e) {
        }
        return response;
    }

    private Client getClient() {
        return this.client;
    }
    
    private GUI getGUI() {
        return getClient().getGUI();
    }

    @Override
    protected Logger getLog() {
        return LOG;
    }
}
