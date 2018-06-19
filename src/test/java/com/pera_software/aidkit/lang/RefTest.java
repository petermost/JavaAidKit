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

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author P. Most
 *
 */
public class RefTest {

	@Test
	public void testRefCtor() {
		Ref< Integer > integer = new Ref<>( 123 );
		assertEquals( 123, integer.get().intValue() );
	}
	
	@SuppressWarnings( "unused" )
	@Test( expected = NullPointerException.class )
	public void TestRefCtorNull() {
		Ref< Integer > integer = new Ref<>( null );
	}
	
	@Test( expected = NullPointerException.class )
	public void testSetNull() {
		Ref< Integer > integer = new Ref<>( 123 );
		integer.set( null );
	}
	
	@Test
	public void testCall() {
		int value = 123;
		Ref< Integer > integer = new Ref<>( value );
		
		setRef( value, integer );
		assertEquals( value, integer.get().intValue() );
	}

	private static void setRef( int value, Out< Integer > integer ) {
		integer.set( value );
	}
}
