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

package com.pera_software.aidkit.concurrent;

import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.locks.*;
import org.junit.*;

//##################################################################################################

/**
 * @author P. Most
 */
public class DataMutexTest {

	//==============================================================================================

	@Test
	public void testLock() throws Exception {

		// Must not be locked yet:

		DataMutex< List< String >> stringList = new DataMutex<>( new ArrayList< String >() );
		assertFalse( stringList.isLocked() );

		// Lock the resource:

		try ( DataMutex< List< String >>.Pointer stringListPtr = stringList.lock() ) {
			assertTrue( stringList.isLocked() );
			stringListPtr.get().add( "first string" );
			stringListPtr.get().add( "second string" );
		}
		// Must be unlocked again:

		assertFalse( stringList.isLocked() );
	}

	//==============================================================================================

	@Test
	public void testAutoLock() throws Exception {

		ReentrantLock lock = new ReentrantLock();
		assertFalse( lock.isLocked() );

		try ( LockLocker locker = new LockLocker( lock )) {
			assertTrue( lock.isLocked() );
		}
		assertFalse( lock.isLocked() );
	}
}
