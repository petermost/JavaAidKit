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

package com.pera_software.aidkit.collection;

import java.lang.reflect.*;
import java.util.*;

/**
 * Iterator for primitive arrays which uses the java.lang.reflect.Array class
 * to iterate over the elements.
 */
public class PrimitiveArrayIterator implements Iterator< Object > {
	private Object _array;
	private int _index;

	//===========================================================================

	public PrimitiveArrayIterator( Object array ) {
		_array = array;
		_index = 0;
	}

	//===========================================================================

	@Override
	public boolean hasNext() {
		return _index < Array.getLength( _array );
	}

	//===========================================================================

	@SuppressWarnings( "null" )
	@Override
	public Object next() {
		return Array.get( _array, _index++ );
	}

	//===========================================================================

	@Override
	public void remove() {
		throw new UnsupportedOperationException( "PrimitiveArrayIterator.remove()" );
	}
}