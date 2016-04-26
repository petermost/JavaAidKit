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

import java.util.*;
import static java.util.Objects.*;

//##################################################################################################
/**
 * When accessing elements via an index, then this list 'wraps' negative indices
 * in such a way that the elements are accessed from the end i.e. -1 accesses the
 * last element -2 the element before that etc.
 */
public class CyclicList< T > implements Iterable< T > {
	private List< T > _list = new ArrayList<>();

	//==============================================================================================

	private int wrapIndex( int index ) {
		return index >= 0 ? index : _list.size() + index;
	}

	//==============================================================================================

	public CyclicList() {
	}

	//==============================================================================================
	
	public CyclicList( CyclicList< T > other ) {
		_list.addAll( other._list );
	}
	
	//==============================================================================================

	public void append( T element ) {
		requireNonNull( element );
		_list.add( element );
	}

	//==============================================================================================

	public void insert( int index, T element ) {
		requireNonNull( element );
		_list.add( wrapIndex( index ), element );
	}

	//==============================================================================================

	public void replace( int index, T element ) {
		requireNonNull( element );
		_list.set( wrapIndex( index ), element );
	}

	//==============================================================================================

	public T getAt( int index, T defaultValue ) {
		index = wrapIndex( index );
		return ( index >= 0 && index < _list.size() ) ? _list.get( index ) : defaultValue;
	}

	//==============================================================================================

	public void remove( int index ) {
		_list.remove( wrapIndex( index ) );
	}

	//==============================================================================================

	@Override
	public Iterator< T > iterator() {
		return _list.iterator();
	}
}