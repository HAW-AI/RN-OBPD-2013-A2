package de.haw_hamburg.requests;

import static org.junit.Assert.*;
import static de.haw_hamburg.requests.Requests.*;
import org.junit.Test;

public class RequestsTest {

	@Test
	public void testFromRawRequest() {
		assertTrue(fromRawRequest("HALLO").isUnknown());
		assertTrue(fromRawRequest("NEW").isUnknown());
		assertTrue(fromRawRequest("NEW ").isUnknown());
		assertTrue(fromRawRequest("BYE").isBye());
		assertTrue(fromRawRequest("BYE ").isBye());
		assertTrue(fromRawRequest("NEW user").isNew());
		assertEquals("user",((NewRequest)fromRawRequest("NEW user")).getName());
		assertEquals("a",((NewRequest)fromRawRequest("NEW a")).getName());
		assertEquals("user",((NewRequest)fromRawRequest("NEW  user ")).getName());
		assertTrue(fromRawRequest("INFO").isInfo());
		assertTrue(fromRawRequest("NEWS user").isUnknown());
		
	}

}
