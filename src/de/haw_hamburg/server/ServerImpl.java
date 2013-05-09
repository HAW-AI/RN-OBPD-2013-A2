package de.haw_hamburg.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ServerImpl extends Thread implements Server {

	private int clientCount = 0;
	private final int port;
	private final ExecutorService executor;
	private final Map<String,InetAddress> activeClients=new ConcurrentHashMap<String, InetAddress>();
	private Logger LOG = Logger.getLogger(ServerImpl.class.getName());

	private ServerImpl(int port, int maxClientNumber) {
		this.port = port;
		this.executor = Executors.newFixedThreadPool(maxClientNumber);
	}

	static ServerImpl create(int port, int maxClientNumber) {
		return new ServerImpl(port, maxClientNumber);
	}

	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			while (!isInterrupted()) {
				try {
					LOG.info("Waiting for connection");
					Socket socket = serverSocket.accept();
					LOG.info("Incoming connection");
					executor.execute(ClientCommunicator.create(socket,
							clientCount++, this));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			serverSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	@Override
	public boolean contains(String clientName){
		return activeClients.containsKey(clientName);
	}
	
	@Override
	public boolean contains(InetAddress address){
		return activeClients.containsValue(address);
	}
	
	@Override
	public InetAddress add(String clientName, InetAddress address){
		return activeClients.put(clientName, address);
	}
	
	@Override
	public InetAddress remove(String clientName){
		return activeClients.remove(clientName);
	}
	
	@Override
	public Map<String,InetAddress> getActiveClients(){
		return activeClients;
	}
	

}
