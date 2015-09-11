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

package com.pera_software.aidkit.debug;

import static com.pera_software.aidkit.nullable.NullStrings.*;

//#############################################################################

public class ObjectDumperOutput implements Cloneable {
	protected StringBuilder _buffer = new StringBuilder();

	//===========================================================================

	/**
	 * Add the name of an attribute or an object to the output.
	 */
	public void addName( String name ) {
		_buffer.append( name ).append( " = " );
	}

	//===========================================================================

	/**
	 * Add the value of an attribute to the output.
	 */
	public void addValue( Object value ) {
		_buffer.append( '<' ).append( value ).append( '>' );
	}

	//===========================================================================

	/**
	 * Add a separator after an attribute or a collection to the output.
	 */
	public void addSeparator() {
		_buffer.append( ", " );
	}

	public void newLine() {
		_buffer.append( "\n" );
	}

	//===========================================================================

	/**
	 * Begin a collection in the output.
	 */
	public void beginCollection( String name, int length ) {
		_buffer.append( name ).append( '[' ).append( length ).append( "] = [" );
	}

	//===========================================================================

	/**
	 * End the collection in the output.
	 */
	public void endCollection() {
		_buffer.append( ']' );
	}

	//===========================================================================

	/**
	 * Begin the enumeration of attributes in the output.
	 */
	public void beginAttributes() {
		_buffer.append( '{' );
	}

	//===========================================================================

	/**
	 * End the enumeration of attributes in the output.
	 */
	public void endAttributes() {
		_buffer.append( '}' );
	}

	//===========================================================================

	@Override
	public String toString() {
		return ensureNonNull( _buffer.toString() );
	}

	//===========================================================================

	@Override
	public ObjectDumperOutput clone() {
		return ( new ObjectDumperOutput() );
	}
}
