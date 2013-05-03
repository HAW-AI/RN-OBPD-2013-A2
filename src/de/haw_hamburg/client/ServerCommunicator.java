package de.haw_hamburg.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.haw_hamburg.common.User;
import de.haw_hamburg.requests.Request;
import de.haw_hamburg.requests.Requests;
import de.haw_hamburg.responses.ListResponse;
import de.haw_hamburg.responses.Response;
import de.haw_hamburg.responses.Responses;

public class ServerCommunicator extends Thread {

	private final String SERVER_HOST = "localhost";
	private final Integer SERVER_PORT = 50000;
	private final Integer CLIENT_LIST_REFRESH_PERIOD = 2000;

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	private String userName;

	private ServerCommunicator(String name) {
		this.userName = name;
	}

	public Response refreshUserList() {
		Response response = sendRequestAndWaitForResponse(Requests.info());
		if (!response.isList()) {
			// in the event of an error disconnect and shutdown
			disconnect();
		}
		return response;
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static ServerCommunicator getServerCommunicator(String userName) {
		return new ServerCommunicator(userName);
	}

	private void disconnect() {
		try {
			interrupt();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Response sendRequestAndWaitForResponse(Request request) {
		Response response = Responses.unknown();
		try {
			out.println(request.toString());
			response = Responses.fromRawResponse(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
