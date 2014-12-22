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

/**
 * @author P. Most
 */
public final class Mutex< T > {

	private ReentrantLock _lock;
	private T _instance;

	public Mutex( T instance ) {
		_lock = new ReentrantLock();
		_instance = instance;
	}

	T lock() {
		_lock.lock();

		return _instance;
	}

	void unlock( T instance ) {
		assert Objects.equals( _instance, instance );
		_lock.unlock();
	}

	boolean isLocked() {
		return _lock.isLocked();
	}
}
