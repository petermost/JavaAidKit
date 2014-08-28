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

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

//#############################################################################

class CustomObject implements Dumpable
{
	private static enum Color
	{
		RED, GREEN, BLUE
	}

	@Override
	public void dump( ObjectDumper dumper )
	{
		dumper.dump( "One", 1 );
		dumper.dump( "Colors", Color.values() );
		dumper.dump( "Two", 2 );
	}
}

//#############################################################################

public class ObjectDumperTest
{
	private ObjectDumper mDumper;

	//===========================================================================

	@Before
	public void setUp()
	{
		mDumper = new ObjectDumper();
	}

	//===========================================================================

	@Test
	public void testDumpObject()
	{
		CustomObject object = new CustomObject();

		mDumper.dump( "object", object );
		assertEquals( "object = {One = <1>, Colors[3] = [<RED>, <GREEN>, <BLUE>], Two = <2>}",
			mDumper.toString() );
		// System.out.println( mDumper.toString() );
	}

	//===========================================================================

	@Test
	public void testDumpObjectArray()
	{
		CustomObject array[] = new CustomObject[] {
			new CustomObject(), new CustomObject()
		};
		mDumper.dump( "array", array );
		assertEquals( "array[2] = [{One = <1>, Colors[3] = [<RED>, <GREEN>, <BLUE>], Two = <2>}, "
			+ "{One = <1>, Colors[3] = [<RED>, <GREEN>, <BLUE>], Two = <2>}]", mDumper.toString() );
		// System.out.println( mDumper.toString() );
	}

	//===========================================================================

	@Test
	public void testDumpPrimitiveArray()
	{
		int array[] = {
			10, 20, 30
		};
		mDumper.dump( "array", array );
		assertEquals( "array[3] = [<10>, <20>, <30>]", mDumper.toString() );
		// System.out.println( mDumper.toString() );
	}

	//===========================================================================

	@Test
	public void testDumpList()
	{
		List< CustomObject > list = Arrays.asList( new CustomObject(), new CustomObject() );
		mDumper.dump( "list", list );
		assertEquals( "list[2] = [{One = <1>, Colors[3] = [<RED>, <GREEN>, <BLUE>], Two = <2>}, "
			+ "{One = <1>, Colors[3] = [<RED>, <GREEN>, <BLUE>], Two = <2>}]", mDumper.toString() );
		// System.out.println( mDumper.toString() );
	}
}
