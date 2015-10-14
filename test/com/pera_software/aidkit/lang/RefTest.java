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
import static com.pera_software.aidkit.nullable.NullStrings.*;

/**
 * @author P. Most
 *
 */
public class RefTest {

	@Test
	@SuppressWarnings( "static-method" )
	public void test() {
		String email = "pmost@pera-software.com";
		Ref< String > name = new Ref<>();
		Ref< String > domain = new Ref<>();
		
		assertTrue( splitString( email, '@', name, domain ));
		assertEquals( "pmost", name.get() );
		assertEquals( "pera-software.com", domain.get() );
		
	}

	private static boolean splitString( String s, char separator, Ref< String > before, Ref< String > after ) {
		int index = s.indexOf( separator );
		if ( index >= 0 ) {
			before.set( makeNonNull( s.substring( 0, index )));
			after.set( makeNonNull( s.substring( index + 1 )));
		}
		return index >= 0;
	}
}
