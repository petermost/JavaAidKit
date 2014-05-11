// Copyright 2014 Peter Most, PERA Software Solutions GmbH
//
// This file is part of JavaAidKit.
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

	public boolean connect( T slot )
	{
		return _slots.add( slot );
	}

	//==============================================================================================

	public boolean disconnect( T slot )
	{
		return _slots.remove( slot );
	}

	//==============================================================================================

	public int receivers()
	{
		return _slots.size();
	}

	//==============================================================================================

	protected void forEach( Callable1< ? super T > action )
	{
		for ( T slot : _slots ) {
			try {
				action.call( slot );
			} catch ( Throwable exception ) {
				// Ignore slot exceptions.
			}
		}
	}
}
