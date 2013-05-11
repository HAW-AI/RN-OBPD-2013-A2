package de.haw_hamburg.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Logger;

import de.haw_hamburg.common.OutgoingMessage;
import de.haw_hamburg.common.User;

public class ClientCommunicator extends Thread {

	public static final int CLIENT_UDP_PORT = 50002;

	private static Logger LOG = Logger.getLogger(Receiver.class.getName());

	private OutgoingMessage message;
	private List<User> receivers;

	private ClientCommunicator(List<User> receivers, OutgoingMessage message) {
		this.receivers = receivers;
		this.message = message;
	}

	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket(CLIENT_UDP_PORT);
			byte[] data = this.message.toMessageByteArray();
			for (User user : this.receivers) {
				DatagramPacket outgoing;
				outgoing = new DatagramPacket(data, data.length,
						InetAddress.getByName(user.getHostname().toString()),
						CLIENT_UDP_PORT);
				socket.send(outgoing);
			}
			socket.close();
		} catch (UnknownHostException e) {
			LOG.warning("Unknown Host Exception in Client Comunicator: "
					+ e.getMessage());
		} catch (IOException e) {
			LOG.warning("Closing Client Comunicator because of IOException: "
					+ e.getMessage());
		}
	}

	public static void sendMessage(List<User> receivers, OutgoingMessage message) {
		if (receivers != null && !receivers.isEmpty() && message != null) {
			ClientCommunicator communicator = new ClientCommunicator(receivers,
					message);
			communicator.run();
		}
	}
}
