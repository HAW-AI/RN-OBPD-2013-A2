package de.haw_hamburg.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.haw_hamburg.requests.Requests;
import de.haw_hamburg.responses.Responses;

public class ServerTest {

	static Socket socket;
	static PrintWriter out;
	static BufferedReader in;
	static ServerImpl server;
	
	@BeforeClass
	public static void setup() throws IOException, InterruptedException{
		server=ServerImpl.create(Starter.SERVER_PORT, 10);
		server.start();
		Thread.sleep(1000);
		socket=new Socket(InetAddress.getByName("localhost"), Starter.SERVER_PORT);
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
		out.println(Requests.newUser("testuser").toString());
		out.flush();
		Thread.sleep(1000);
		assertEquals(Responses.ok().toString(),in.readLine());
	}
	
	@AfterClass
	public static void tearDown() throws IOException, InterruptedException{
		out.println(Responses.bye());
		out.flush();
		assertEquals(Responses.bye().toString(),in.readLine());
		in.close();
		out.close();
		socket.close();
		server.interrupt();
	}
	
	@Test
	public void testContains() throws IOException, InterruptedException {
		assertTrue(server.contains("testuser"));
		assertTrue(server.contains(socket.getInetAddress()));
	}

	@Test
	public void testGetActiveClients() throws IOException {
		Map<String,InetAddress> map=new HashMap<String,InetAddress>();
		map.put("testuser",socket.getInetAddress());
		assertEquals(map,server.getActiveClients());
	}

}
