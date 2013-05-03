package de.haw_hamburg.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessagesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFromRawMessages() {
		assertNull(Message.fromRawMessage(""));
		assertNull(Message.fromRawMessage(null));
		assertNull(Message.fromRawMessage("Hallo - Hallo"));
		assertEquals("Hallo", Message.fromRawMessage("Hallo: content")
				.getUserName());
		assertEquals("content: with colon",
				Message.fromRawMessage("Hallo: content: with colon")
						.getContent());
	}

}
