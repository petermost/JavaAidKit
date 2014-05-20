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
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit.  If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.signal;

import java.util.*;
import java.util.concurrent.*;
import com.pera_software.aidkit.util.function.*;

//##################################################################################################
// An excellent article: "Java theory and practice: Be a good (event) listener"
// (http://www.ibm.com/developerworks/java/library/j-jtp07265/index.html)

abstract class SignalBase< T > extends Signal
{
	private List< T > _slots = new CopyOnWriteArrayList<>();

	//==============================================================================================
	/**
	 * Connect a slot to a signal.
	 * @param slot The slot which gets connected.
	 * @return Whether the slot was connected.
	 */
	public boolean connect( T slot )
	{
		Objects.requireNonNull( slot );

		return _slots.add( slot );
	}

	//==============================================================================================
	/**
	 * Disconnect a slot from a signal.
	 * @param slot The slot which get disconnected.
	 * @return Whether the slot was disconnected.
	 */
	public boolean disconnect( T slot )
	{
		Objects.requireNonNull( slot );

		return _slots.remove( slot );
	}

	//==============================================================================================

	public int receivers()
	{
		return _slots.size();
	}

	//==============================================================================================
	/**
	 * Apply an action to each slot.
	 * @param action The action to apply to each slot.
	 */
	protected void forEach( Callable1< ? super T > action )
		throws Exception
	{
		Objects.requireNonNull( action );

		for ( T slot : _slots ) {
			action.call( slot );
		}
	}
}
