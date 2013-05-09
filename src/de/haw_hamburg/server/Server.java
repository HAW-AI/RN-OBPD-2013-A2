package de.haw_hamburg.server;

import java.net.InetAddress;
import java.util.Map;

public interface Server {

	boolean contains(InetAddress address);

	boolean contains(String name);

	InetAddress add(String name, InetAddress address);

	InetAddress remove(String clientName);

	Map<String,InetAddress> getActiveClients();
	

}
