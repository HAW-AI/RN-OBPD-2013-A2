package de.haw_hamburg.responses;

import static org.junit.Assert.*;

import org.junit.Test;
import static de.haw_hamburg.responses.Responses.*;

public class ResponsesTest {

	@Test
	public void testFromRawResponse() {
		assertTrue(fromRawResponse("BYE").isBye());
		assertTrue(fromRawResponse("BYE ").isBye());
		assertTrue(fromRawResponse("OK").isOk());
		assertTrue(fromRawResponse("LISTS").isUnknown());
		assertTrue(fromRawResponse("LIST").isUnknown());
		assertTrue(fromRawResponse("ERROR message").isError());
		assertEquals("message",
				((ErrorResponse) fromRawResponse("ERROR message")).getMessage());
		assertTrue(fromRawResponse("LIST 2 hostname1 user1 hostname2 user2")
				.isList());
		assertEquals(
				2,
				((ListResponse) fromRawResponse("LIST 2 hostname1 user1 hostname2 user2"))
						.getList().size());
		assertTrue(fromRawResponse("LIST 2 hostname1 user1 hostname2")
				.isUnknown());
		assertTrue(fromRawResponse("LIST 1 hostname1 user1 hostname2 user2")
				.isUnknown());
	}

}
