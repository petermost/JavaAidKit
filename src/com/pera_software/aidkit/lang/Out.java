// Copyright 2016 Peter Most, PERA Software Solutions GmbH
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

package com.pera_software.aidkit.lang;

import java.util.*;

/**
 * A class which can be used to return an value from a method via a parameter. The difference to
 * {@link com.pera_software.aidkit.lang.Ref} is that it doesn't need to be initialized with a value.
 * 
 * {@code}
 * <blockquote><pre>
 * public static boolean splitString( String s, char separator, Out< String > before, Out< String > after ) {
 *     int index = s.indexOf( separator );
 *     if ( index >= 0 ) {
 *         before.set( s.substring( 0, index ));
 *         after.set( s.substring( index + 1 ));
 *     }
 *     return index >= 0;
 * }
 * </pre></blockquote>
 * 
 * @author P. Most
 */
public class Out< T > {
	private T _value = null;

	/**
	 * No initializing constructor because {@code out} parameters are only supposed to be set in the 
	 * called method.
	 */
	public Out() {
	}

	/**
	 * Sets the value if not null, otherwise throws a {@code NullPointerException}.
	 * @param value the non-null value to set.
	 * @throws NullPointerException if value is null.
	 */
	public void set( T value ) {
		if ( value == null )
			throw new NullPointerException();
		
		_value = value;
	}

	/**
	 * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}. 
	 * @throws NoSuchElementException if there is no value present
	 * @return the non-null value held
	 */
	public T get() {
		if ( _value == null )
			throw new NoSuchElementException();
		
		return _value;
	}
}
