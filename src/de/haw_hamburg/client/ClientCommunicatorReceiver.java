package de.haw_hamburg.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Logger;

public class ClientCommunicatorReceiver extends Thread {

	public static final int CLIENT_UDP_PORT = 50001;
	public static final int BUFFER_SIZE = 1024;
	private final DatagramSocket socket;
	private final GUIView view;
	private Logger LOG = Logger.getLogger(ClientCommunicatorReceiver.class
			.getName());

	private ClientCommunicatorReceiver(GUIView view, DatagramSocket socket) {
		this.socket = socket;
		this.view = view;
	}

	ClientCommunicatorReceiver create(GUIView view) throws SocketException {
		DatagramSocket socket = new DatagramSocket(CLIENT_UDP_PORT);
		return new ClientCommunicatorReceiver(view, socket);
	}

	@Override
	public void run() {
		LOG.info("Receiver started");
		byte[] receiveData = new byte[BUFFER_SIZE];
		try {
			while (!isInterrupted()) {
				DatagramPacket packet = new DatagramPacket(receiveData,
						receiveData.length);
				socket.receive(packet);
				String message = new String(packet.getData());
				view.addEntryToChatLogScrollPane(message);
			}
		} catch (IOException e) {
			if (!isInterrupted())
				LOG.warning("Error while receiving: " + e.getMessage());
		}
		LOG.info("Receiver stopped");
	}

	@Override
	public void interrupt() {
		socket.close();
		super.interrupt();
	}
}
