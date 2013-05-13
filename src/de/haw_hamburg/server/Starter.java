package de.haw_hamburg.server;

public class Starter {
	
	public static final int SERVER_PORT=50000;
	public static final int MAX_CLIENT_NUMBER=10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerImpl.create(SERVER_PORT, MAX_CLIENT_NUMBER).start();
	}

}
