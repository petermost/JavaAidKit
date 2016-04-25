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
	public void testRef() {
		Ref< String > firstName = new Ref<>( "peter" );
		Ref< String > lastName = new Ref<>( "most" );

		swapString( firstName, lastName );
		
		assertEquals( "most", firstName.get() );
		assertEquals( "peter", lastName.get() );
	}

	private static void swapString( Ref< String > s1, Ref< String > s2 ) {
		String temp = s1.get();
		s1.set( s2.get() );
		s2.set( temp );
	}
}
