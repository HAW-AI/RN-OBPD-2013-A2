package de.haw_hamburg.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import de.haw_hamburg.responses.Response;
import de.haw_hamburg.requests.Request;

public abstract class ChatComponent extends Thread {
	
	protected PrintWriter out;
	protected BufferedReader in;
	protected abstract Logger getLog();


	protected String readLine() throws IOException {
		String result=in.readLine();
		getLog().info("Received: "+result);
		return result;
	}
	
	protected void println(Request request) throws IOException {
		getLog().info("Sending reply: "+request);
		out.println(request.toString());
	}

	protected void println(Response reply) throws IOException {
		getLog().info("Sending response: "+reply);
		out.println(reply.toString());
	}

}
