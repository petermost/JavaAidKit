package com.pera_software.aidkit.net;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class URLsTest {

	@Test
	void testEncode() throws Exception
	{
		var inputUrl = "first second (third)";
		var url = URLs.encode(inputUrl);
		assertEquals("first+second+%28third%29", url);
	}

}
