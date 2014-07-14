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

import java.lang.reflect.*;
import java.util.*;
import com.pera_software.aidkit.collection.*;

//#############################################################################

public class ObjectDumper
{
	private static ObjectDumperOutput s_outputProtoType = new ObjectDumperOutput();

	private final ObjectDumperOutput _output = s_outputProtoType.clone();
	private int _objectCounter = 0;

	//===========================================================================

	/**
	 * Set which output to use.
	 */

	public static void setOutputPrototype( ObjectDumperOutput protoType )
	{
		s_outputProtoType = protoType;
	}

	//===========================================================================

	private void addSeparator( int counter )
	{
		if ( counter > 0 )
			_output.addSeparator();
	}

	public void newLine()
	{
		_output.newLine();
	}

	//===========================================================================

	private int swapObjectCounter( int newValue )
	{
		int oldValue = _objectCounter;
		_objectCounter = newValue;
		return ( oldValue );
	}

	//===========================================================================

	private void dumpObject( Object object )
	{
		if ( object instanceof Dumpable ) {
			int oldObjectCounter = swapObjectCounter( 0 );

			_output.beginAttributes();
			( ( Dumpable )object ).dump( this );
			_output.endAttributes();

			swapObjectCounter( oldObjectCounter );
		} else
			_output.addValue( object );
	}

	//===========================================================================

	private void dumpCollection( String name, Iterator< ? > iterator, int length )
	{
		addSeparator( _objectCounter++ );

		_output.beginCollection( name, length );
		for ( int i = 0; iterator.hasNext(); ++i ) {
			addSeparator( i );

			dumpObject( iterator.next() );
		}
		_output.endCollection();
	}

	//===========================================================================

	public void dump( String name, Object object )
	{
		if ( object == null )
			object = "<null>";

		if ( object.getClass().isArray() )
			dumpCollection( name, new PrimitiveArrayIterator( object ), Array.getLength( object ) );
		else {
			addSeparator( _objectCounter++ );
			_output.addName( name );
			dumpObject( object );
		}
	}

	//===========================================================================

	public void dump( String name, Object objects[] )
	{
		dumpCollection( name, new ObjectArrayIterator<>( objects ), objects.length );
	}

	//===========================================================================

	public void dump( String name, List< ? > objects )
	{
		dumpCollection( name, objects.iterator(), objects.size() );
	}

	//===========================================================================

	@Override
	public String toString()
	{
		_objectCounter = 0;

		return ( _output.toString() );
	}

	public static String toString( Dumpable dumpable )
	{
		ObjectDumper objectDumper = new ObjectDumper();
		dumpable.dump( objectDumper );
		return objectDumper.toString();
	}
}
