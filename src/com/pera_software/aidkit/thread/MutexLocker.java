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

import org.eclipse.jdt.annotation.*;

//##################################################################################################

/**
 * @author P. Most
 */
public final class MutexLocker< @NonNull T > implements AutoCloseable {

	private Mutex< T > _mutex;
	private T _resource;

	//==============================================================================================

	public MutexLocker( Mutex< T > mutex ) {
		_mutex = mutex;
		_resource = _mutex.lock();
	}

	//==============================================================================================

	@Override
	public void close() throws Exception {
		_mutex.unlock( _resource );
	}

	//==============================================================================================

	public T get() {
		return _resource;
	}
}
