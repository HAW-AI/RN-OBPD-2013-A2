package de.haw_hamburg.client;

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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class ServerCommunicator extends Thread {

    private final String SERVER_HOST = "localhost";
    private final Integer SERVER_PORT = 50000;
    private final Integer CLIENT_LIST_REFRESH_PERIOD = 2000;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String userName;
    private final Client client;

    private ServerCommunicator(String userName, Client client) {
        this.userName = userName;
        this.client = client;
    }

    public void refreshUserList() {
        Response response = sendRequestAndWaitForResponse(Requests.info());
        if (response.isList()) {
            List<User> newUserList = ((ListResponse) response).getList();
            getClient().setUserList(newUserList);
            getGUI().setUserList(newUserList);
            
        } else {
            // in the event of an error disconnect and shutdown
            disconnect();
        }
        
    }

    public void run() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            Response nameResponse = sendRequestAndWaitForResponse(Requests
                    .newUser(this.userName));

            if (nameResponse.isError()) {
                disconnect();
            } else {
                while (!isInterrupted()) {
                    refreshUserList();
                    sleep(CLIENT_LIST_REFRESH_PERIOD);
                }
            }
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
    }

    public static ServerCommunicator createServerCommunicator(String userName, Client client) {
        if (userName != null && client != null) {
            return new ServerCommunicator(userName, client);
        } else {
            return null;
        }
    }

    private void disconnect() {
        try {
            interrupt();
            socket.close();
        } catch (IOException e) {
        }
    }

    private Response sendRequestAndWaitForResponse(Request request) {
        Response response = Responses.unknown();
        try {
            out.println(request.toString());
            response = Responses.fromRawResponse(in.readLine());
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
}
