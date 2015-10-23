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

import java.util.*;
import java.util.concurrent.locks.*;
import org.eclipse.jdt.annotation.*;

//##################################################################################################

/**
 * @author P. Most
 */
public final class Mutex< @NonNull T > {

	private ReentrantLock _lock;
	private T _resource;

	//==============================================================================================

	public Mutex( T resource ) {
		_lock = new ReentrantLock();
		_resource = resource;
	}

	//==============================================================================================

	public T lock() {
		_lock.lock();

		return _resource;
	}

	//==============================================================================================

	public void unlock( T resource ) {
		assert Objects.equals( _resource, resource );
		_lock.unlock();
	}

	//==============================================================================================

	boolean isLocked() {
		return _lock.isLocked();
	}
}
