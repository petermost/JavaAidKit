package com.pera_software.aidkit.lang;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IntegersTest {

	@Test
	void testIsOdd()
	{
		assertTrue(Integers.isOdd(3));
		assertFalse(Integers.isOdd(4));
	}

	@Test
	void testIsEven()
	{
		assertTrue(Integers.isEven(4));
		assertFalse(Integers.isEven(3));
	}

}
