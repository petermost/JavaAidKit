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

//#############################################################################

public class LongInt
{
	private static final long HIGH_INT_MASK = ~0L << Integer.SIZE;
	private static final long LOW_INT_MASK = ~HIGH_INT_MASK;

	//===========================================================================

	public static int highInt( long l )
	{
		return ( ( int )( ( l & HIGH_INT_MASK ) >> Integer.SIZE ) );
	}

	//===========================================================================

	public static int lowInt( long l )
	{
		return ( ( int )( l & LOW_INT_MASK ) );
	}

	//===========================================================================

	public static long make( int high, int low )
	{
		return ( ( ( high & LOW_INT_MASK ) << Integer.SIZE ) | ( low & LOW_INT_MASK ) );
	}

	//===========================================================================

	public static long make( long high, long low )
	{
		return ( ( ( high & LOW_INT_MASK ) << Integer.SIZE ) | ( low & LOW_INT_MASK ) );
	}

}
