// Copyright 2014 Peter Most, PERA Software Solutions GmbH
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

package com.pera_software.aidkit.thread;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

/**
 * @author P. Most
 */
public class MutexTest {

	@Test
	@SuppressWarnings( "static-method" )
	public void testLock() throws Exception {
		Mutex< List< String >> stringListMutex = new Mutex< List< String >>( new ArrayList< String >() );
		try ( Lock< List< String >> stringListLock = new Lock< List< String >>( stringListMutex )) {
			assertTrue( stringListMutex.isLocked() );
			stringListLock.get().add( "first string" );
			stringListLock.get().add( "second string" );
		}
	}
}
