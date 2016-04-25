// Copyright 2015 Peter Most, PERA Software Solutions GmbH
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

package com.pera_software.aidkit.nullable;

import java.util.*;

// TODO: Replace with Collections.emptyIterator()?

/**
 * A null Iterator<> implementation of the Iterator interface. 
 * @author P. Most
 *
 */
public class NullIterator< T > implements Iterable< T >, Iterator< T > {

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator< T > iterator() {
		return this;
	}
	
	/**
	 * @return false Always since there are no elements.
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return false;
	}

	/**
	 * Always throws a NoSuchElementException, but in practice this shouldn't matter because when
	 * hasNext() returned false, you are not suppose to call next()!
	 * 
	 * @throws NoSuchElementException Always!
	 */
	@Override
	public T next() {
		throw new NoSuchElementException();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new NoSuchElementException();
	}
}
