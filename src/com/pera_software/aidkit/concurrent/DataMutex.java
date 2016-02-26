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
import org.eclipse.jdt.annotation.*;

//##################################################################################################

/**
 * @author P. Most
 */
public final class DataMutex< @NonNull T > {

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
	private boolean _isLocked; 

	//==============================================================================================

	public DataMutex( T data ) {
		this( data, new ReentrantLock() );
	}

	//==============================================================================================
	
	public DataMutex( T data, Lock lock ) {
		_data = data;
		_lock = lock;
		_isLocked = false;
	}
	
	//==============================================================================================

	public Pointer lock() {
		_lock.lock();
		_isLocked = true;
		
		return new Pointer();
	}

	//==============================================================================================
	
	public @Nullable Pointer tryLock() {
		if ( _lock.tryLock() )
			return new Pointer();
		else
			return null;
	}
	
	//==============================================================================================

	private void unlock() {
		_isLocked = false;
		_lock.unlock();
	}

	//==============================================================================================

	boolean isLocked() {
		return _isLocked;
	}
}