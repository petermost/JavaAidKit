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

import java.util.concurrent.locks.*;

//##################################################################################################

/**
 * @author P. Most
 */
public final class DataMutex< T > {

	public final class Pointer implements AutoCloseable {
		
		public T get() {
			return _data;
		}

		@Override
		public void close() throws Exception {
			unlock();
		}
	}
	
	private T _data;
	private Lock _lock;
	private int _lockCount; 

	//==============================================================================================

	public DataMutex( T data ) {
		this( data, new ReentrantLock() );
	}

	//==============================================================================================
	
	public DataMutex( T data, Lock lock ) {
		_data = data;
		_lock = lock;
		_lockCount = 0;
	}
	
	//==============================================================================================

	public Pointer lock() {
		_lock.lock();
		++_lockCount;
		
		return new Pointer();
	}

	//==============================================================================================
	
	public Pointer tryLock() {
		if ( _lock.tryLock() ) {
			++_lockCount;			
			return new Pointer();
		}
		else
			return null;
	}
	
	//==============================================================================================

	private void unlock() {
		--_lockCount;
		_lock.unlock();
	}

	//==============================================================================================

	boolean isLocked() {
		return lockCount() > 0;
	}
	
	//==============================================================================================
	
	int lockCount() {
		return _lockCount;
	}
}
