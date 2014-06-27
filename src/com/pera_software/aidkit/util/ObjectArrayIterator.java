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

package com.pera_software.aidkit.util;

import java.util.*;

//#############################################################################

/**
 * Iterator for object arrays.
 * Note: This could also be implemented with the java.lang.reflect.Array class
 * like the PrimitiveArrayIterator below does, but this version is more straightforward
 * and hence easier to understand.
 */
public class ObjectArrayIterator< T > implements Iterator< T >
{
	private T _array[];
	private int _index;

	//===========================================================================

	public ObjectArrayIterator( T array[] )
	{
		_array = array;
		_index = 0;
	}

	//===========================================================================

	@Override
	public boolean hasNext()
	{
		return _index < _array.length;
	}

	//===========================================================================

	@Override
	public T next()
	{
		return _array[ _index++ ];
	}

	//===========================================================================

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException( "ArrayIterator< T >.remove()" );
	}
}
