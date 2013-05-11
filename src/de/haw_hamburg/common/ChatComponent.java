package de.haw_hamburg.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import de.haw_hamburg.responses.Response;
import de.haw_hamburg.requests.Request;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public abstract class ChatComponent extends Thread {
	
	protected PrintWriter out;
	protected InputStream in;
	protected abstract Logger getLog();


//	protected String readLine() throws IOException {
//		String result=in.readLine();
//		getLog().info("Received: "+result);
//		return result;
//	}
        
        protected String readLine() throws IOException {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while (true) {
                int b = in.read();
                if (b < 0) {
                    throw new IOException("Data truncated");
                }
                if (b == 0x0A) {
                    break;
                }
                buffer.write(b);
            }
            return new String(buffer.toByteArray(), Charset.forName("UTF-8"));
        }
	
	protected void println(Request request) throws IOException {
		getLog().info("Sending request: "+request);
		out.println(request.toString());
	}

	protected void println(Response reply) throws IOException {
		getLog().info("Sending repsonse: "+reply);
		out.println(reply.toString());
	}

}
