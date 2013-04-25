package de.haw_hamburg.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Logger;

import de.haw_hamburg.common.Message;


public class Receiver extends Thread {

	private static int CLIENT_UDP_PORT=50001;

	private static Logger LOG=Logger.getLogger(Receiver.class.getName());
	
	@Override
	public void run() {
		try {
			DatagramSocket serverSocket = new DatagramSocket(CLIENT_UDP_PORT);
			while(!isInterrupted()){
				byte[] buffer = new byte[65536]; 
				DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			    serverSocket.receive(incoming);
			    String rawMessage = new String(incoming.getData());
			    Message message=Message.fromRawMessage(rawMessage);
			    if(message==null){
			    	LOG.info("Received strange message: \""+rawMessage+"\"");
			    }
			    else{
			    	
			    }
			}
			serverSocket.close();
		} catch (SocketException e) {
			LOG.warning("Closing receiver because of SocketException: "+e.getMessage());
		} catch (IOException e) {
			LOG.warning("Closing receiver because of IOException: "+e.getMessage());
		}
	}

}
