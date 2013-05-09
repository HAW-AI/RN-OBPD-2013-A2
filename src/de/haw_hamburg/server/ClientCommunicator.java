package de.haw_hamburg.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Logger;

import de.haw_hamburg.common.ChatComponent;
import de.haw_hamburg.requests.NewRequest;
import de.haw_hamburg.requests.Request;
import de.haw_hamburg.requests.Requests;
import de.haw_hamburg.responses.Responses;

public class ClientCommunicator extends ChatComponent {

	private final Socket socket;
	private final InetAddress address;
	private ChatState state = ChatState.CONNECTED;
	private final Server server;
	private final Logger LOG;
	private String clientName;

	private ClientCommunicator(Socket socket, int id, BufferedReader in,
			PrintWriter out, Server server, InetAddress address) {
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.server = server;
		this.address = address;
		this.LOG = Logger.getLogger(ClientCommunicator.class + " " + id);
	}

	static ClientCommunicator create(Socket socket, int id, Server server)
			throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		InetAddress address = socket.getInetAddress();
		return new ClientCommunicator(socket, id, in, out, server, address);
	}

	@Override
	public void run() {
		LOG.info("ClientCommunicator started");
		try {
			String rawRequest=readLine();
			while (!isInterrupted() && state != ChatState.DISCONNECTED && rawRequest!=null) {
				handleRequest(rawRequest);
				rawRequest=readLine();
			}
		} catch (IOException e) {
			LOG.warning("Could not get request: " + e.getMessage());
			if (server.contains(address)) {
				server.remove(clientName);
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			LOG.warning("Could not close socket: " + e.getMessage());
		}
		LOG.info("ClientCommunicator finished");
	}

	private void handleRequest(String rawRequest) throws IOException {
		Request request = Requests.fromRawRequest(rawRequest);
		if (request.isNew()) {
			NewRequest newRequest = (NewRequest) request;
			if (state != ChatState.CONNECTED) {
				LOG.warning("Received NEW while not in AUTHORIZATION state");
				println(Responses.error("NEW command while in wrong state"));
			} else if (server.contains(address)) {
				println(Responses.error("Socket already registered"));
			} else if (server.contains(newRequest.getName())) {
				println(Responses.error("Name already in use"));
			} else {
				this.clientName = newRequest.getName();
				server.add(newRequest.getName(), address);
				state = ChatState.AUTHORIZED;
				println(Responses.ok());
			}
		} else if (request.isBye()) {
			if (state != ChatState.AUTHORIZED) {
				LOG.warning("Received BYE while not in CONNECTED state");
				println(Responses.error("BYE command while in wrong state"));
			} else {
				server.remove(clientName);
				state = ChatState.DISCONNECTED;
				println(Responses.bye());
			}
		} else if (request.isInfo()) {
			if (state != ChatState.AUTHORIZED) {
				LOG.warning("Received INFO while not in CONNECTED state");
				println(Responses.error("INFO command while in wrong state"));
			} else {
				println(Responses.list(server.getActiveClients()));
			}
		} else if (request.isUnknown()) {
			println(Responses
					.error("Unknown request. Could it be wrongly formatted?"));
		} else {
			LOG.warning("THIS SHOULD NEVER HAPPEN: " + request);
		}
	}

	@Override
	protected Logger getLog() {
		return LOG;
	}
	
	ChatState getChatState(){
		return state;
	}

}
