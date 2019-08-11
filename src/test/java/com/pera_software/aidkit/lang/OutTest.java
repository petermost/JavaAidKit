// Copyright 2015 Peter Most, PERA Software Solutions GmbH
//
// This file is part of the JavaAidKit library.
//
// JavaAidKit is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// JavaAidKit is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * @author P. Most
 *
 */
public class OutTest {

	@Test
	public void testSet() {
		Out< Integer > integer = new Out<>();
		integer.set( 123 );
		assertEquals( 123, integer.get().intValue() );
	}
	
	@Test
	public void testSetNull() {
		assertThrows(NullPointerException.class, () -> {
			Out< Integer > integer = new Out<>();
			integer.set( null );
		});
	}
	
	@Test
	public void testGetNull() {
		assertThrows(NoSuchElementException.class, () -> {
			Out< Integer > integer = new Out<>();
			integer.get();
		});
	}
	
	@Test
	public void testEmptyToString() {
		Out< Integer > integer = new Out<>();
		assertEquals("Out<>: 'null'", integer.toString());
	}
	
	@Test
	public void testToString() {
		Out< Integer > integer = new Out<>();
		integer.set( 13 );
		assertEquals("Out<Integer>: '13'", integer.toString());
	}
	
	@Test
	public void testCall() {
		int value = 123;
		Out< Integer > integer = new Out<>();
		
		setOut( value, integer );
		assertEquals( value, integer.get().intValue() );
	}

	private static void setOut( int value, Out< Integer > integer ) {
		integer.set( value );
	}
}
