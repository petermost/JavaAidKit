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

package com.pera_software.aidkit.lang;

import static org.junit.Assert.*;
import org.junit.*;

//#############################################################################

public class SafeCastTest
{
	//=============================================================================

	@Test
	public void testCasting()
	{
		assertEquals( Integer.MAX_VALUE, SafeCast.toInt( Integer.MAX_VALUE ) );
		assertEquals( Integer.MIN_VALUE, SafeCast.toInt( Integer.MIN_VALUE ) );

		assertEquals( Short.MAX_VALUE, SafeCast.toShort( Short.MAX_VALUE ) );
		assertEquals( Short.MIN_VALUE, SafeCast.toShort( Short.MIN_VALUE ) );

		assertEquals( Byte.MAX_VALUE, SafeCast.toByte( Byte.MAX_VALUE ) );
		assertEquals( Byte.MIN_VALUE, SafeCast.toByte( Byte.MIN_VALUE ) );
	}

	//=============================================================================

	@Test( expected = OverflowError.class )
	public void testToIntOverflow()
	{
		SafeCast.toInt( Integer.MAX_VALUE + 1L );
	}

	//=============================================================================

	@Test( expected = UnderflowError.class )
	public void testToIntUnderflow()
	{
		SafeCast.toInt( Integer.MIN_VALUE - 1L );
	}

	//=============================================================================

	@Test( expected = OverflowError.class )
	public void testToShortOverflow()
	{
		SafeCast.toShort( Short.MAX_VALUE + 1 );
	}

	//=============================================================================

	@Test( expected = UnderflowError.class )
	public void testToShortUnderflow()
	{
		SafeCast.toShort( Short.MIN_VALUE - 1 );
	}

	//=============================================================================

	@Test( expected = OverflowError.class )
	public void testToByteOverflow()
	{
		SafeCast.toByte( Byte.MAX_VALUE + 1 );
	}

	//=============================================================================

	@Test( expected = UnderflowError.class )
	public void testToByteUnderflow()
	{
		SafeCast.toByte( Byte.MIN_VALUE - 1 );
	}
}
