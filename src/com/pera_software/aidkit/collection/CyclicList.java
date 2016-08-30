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

//##################################################################################################
/**
 * When accessing elements via an index, then this list 'wraps' negative indices
 * in such a way that the elements are accessed from the end i.e. -1 accesses the
 * last element -2 the element before that etc.
 */

public class CyclicList< T > implements List< T > {
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

	@Override
	public T remove( int index ) {
		return _list.remove( wrapIndex( index ) );
	}

	//==============================================================================================

	@Override
	public Iterator< T > iterator() {
		return _list.iterator();
	}

	//==============================================================================================

	@Override
	public int size() {
		return _list.size();
	}

	//==============================================================================================

	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	//==============================================================================================
	
	@Override
	public boolean contains( Object o ) {
		return _list.contains( o );
	}

	//==============================================================================================
	
	@Override
	public Object[] toArray() {
		return _list.toArray();
	}

	//==============================================================================================
	
	@Override
	public < T > T[] toArray( T[] a ) {
		return _list.toArray( a );
	}

	//==============================================================================================
	
	@Override
	public boolean add( T e ) {
		return _list.add( e );
	}

	//==============================================================================================
	
	@Override
	public boolean remove( Object o ) {
		return _list.remove( o );
	}

	//==============================================================================================
	
	@Override
	public boolean containsAll( Collection< ? > c ) {
		return _list.containsAll( c );
	}

	//==============================================================================================
	
	@Override
	public boolean addAll( Collection< ? extends T > c ) {
		return _list.addAll( c );
	}

	//==============================================================================================
	
	@Override
	public boolean addAll( int index, Collection< ? extends T > c ) {
		return _list.addAll( wrapIndex( index ), c );
	}

	//==============================================================================================
	
	@Override
	public boolean removeAll( Collection< ? > c ) {
		return _list.removeAll( c );
	}

	//==============================================================================================
	
	@Override
	public boolean retainAll( Collection< ? > c ) {
		return _list.retainAll( c );
	}

	//==============================================================================================
	
	@Override
	public void clear() {
		_list.clear();
	}

	//==============================================================================================
	
	@Override
	public T get( int index ) {
		return _list.get( wrapIndex( index ));
	}

	//==============================================================================================
	
	@Override
	public T set( int index, T element ) {
		return _list.set( wrapIndex( index ), element );
	}

	//==============================================================================================
	
	@Override
	public void add( int index, T element ) {
		_list.add( wrapIndex( index ), element );
	}

	//==============================================================================================
	
	@Override
	public int indexOf( Object o ) {
		return _list.indexOf( o );
	}

	//==============================================================================================
	
	@Override
	public int lastIndexOf( Object o ) {
		return _list.lastIndexOf( o );
	}

	//==============================================================================================
	
	@Override
	public ListIterator< T > listIterator() {
		return _list.listIterator();
	}

	//==============================================================================================
	
	@Override
	public ListIterator< T > listIterator( int index ) {
		return _list.listIterator( wrapIndex( index ));
	}

	//==============================================================================================
	
	@Override
	public List< T > subList( int fromIndex, int toIndex ) {
		return _list.subList( wrapIndex( fromIndex ), wrapIndex( toIndex ));
	}
	
}
