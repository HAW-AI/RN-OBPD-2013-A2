package de.haw_hamburg.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessagesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFromRawMessages() {
		assertNull(Messages.fromRawMessage(""));
		assertNull(Messages.fromRawMessage(null));
		assertNull(Messages.fromRawMessage("Hallo - Hallo"));
		assertEquals("Hallo",Messages.fromRawMessage("Hallo: content").getUserName());
		assertEquals("content: with colon", Messages.fromRawMessage("Hallo: content: with colon").getContent());
	}

}
