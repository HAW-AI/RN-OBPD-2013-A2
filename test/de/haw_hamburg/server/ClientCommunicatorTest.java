package de.haw_hamburg.server;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.haw_hamburg.common.User;
import de.haw_hamburg.requests.Requests;
import de.haw_hamburg.responses.Responses;

public class ClientCommunicatorTest {

	static TestServerThread server;
	ClientCommunicator communicator;
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	@BeforeClass
	public static void setupClass() throws InterruptedException{
		server=new TestServerThread();
		server.start();
		Thread.sleep(1000);
	}
	
	@AfterClass
	public static void tearDownClass(){
		server.interrupt();
		while(!server.isInterrupted()){
			
		}
	}
	
	@Before
	public void setup() throws IOException, InterruptedException{
		socket=new Socket(InetAddress.getByName("localhost"), Starter.SERVER_PORT);
		Socket clientSocket=server.socket;
		while(clientSocket==null){
			clientSocket=server.socket;
		}
		server.socket=null;
		communicator=ClientCommunicator.create(clientSocket, 0, new MockupServer());
		communicator.start();
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
	}
	
	@After
	public void tearDown() throws IOException, InterruptedException{
		in.close();
		out.close();
		socket.close();
		communicator.interrupt();
	}
	
	@Test
	public void testGetChatState() throws IOException {
		assertEquals(ChatState.CONNECTED,communicator.getChatState());
		out.println(Requests.newUser("testuser"));
		out.flush();
		assertEquals(Responses.ok().toString(),in.readLine());
		assertEquals(ChatState.AUTHORIZED,communicator.getChatState());
		out.println(Requests.bye());
		out.flush();
		assertEquals(Responses.bye().toString(),in.readLine());
		assertEquals(ChatState.DISCONNECTED,communicator.getChatState());
	}
	
	@Test
	public void testListRequest() throws IOException{
		out.println(Requests.newUser("testuser"));
		out.flush();
		assertEquals(Responses.ok().toString(),in.readLine());
		out.println(Requests.info());
		out.flush();
		assertEquals(Responses.list(Arrays.asList(User.create("testuser", "localhost"))).toString(),in.readLine());
		out.println(Requests.bye());
		out.flush();
		assertEquals(Responses.bye().toString(),in.readLine());
	}
	
	/**
	 * Seperate thread needed to create sockets
	 *
	 */
	static class TestServerThread extends Thread{
		
		private Socket socket;
		ServerSocket serverSocket;
		
		@Override
		public void run() {
			
			try {
				serverSocket = new ServerSocket(Starter.SERVER_PORT);
				while(!isInterrupted()){
					socket=serverSocket.accept();					
				}
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void interrupt() {
			try {
				serverSocket.close();
			} catch (IOException e) {
			}
			super.interrupt();
		}
	
	}
	
	/**
	 * MockupServer to not mess up the code just for testing purposes
	 *
	 */
	class MockupServer implements Server{

		Map<String,InetAddress> map=new HashMap<String,InetAddress>();
		
		@Override
		public boolean contains(InetAddress address) {
			return map.containsValue(address);
		}

		@Override
		public boolean contains(String name) {
			return map.containsValue(name);
		}

		@Override
		public InetAddress add(String name, InetAddress address) {
			return map.put(name, address);
		}

		@Override
		public InetAddress remove(String clientName) {
			return map.remove(clientName);
		}

		@Override
		public Map<String,InetAddress> getActiveClients() {
			return map;
		}
		
	}

}
