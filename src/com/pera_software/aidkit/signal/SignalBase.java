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
