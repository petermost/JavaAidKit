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
public class SafeCast
//#############################################################################
{
	//=============================================================================
	private static void checkRange( long value, long maxValue, long minValue )
		throws ConstraintError
	//=============================================================================
	{
		if ( value > maxValue )
			throw new OverflowError( value, maxValue );

		else if ( value < minValue )
			throw new UnderflowError( value, minValue );
	}

	//=============================================================================
	public static int toInt( long value ) throws ConstraintError
	//=============================================================================
	{
		checkRange( value, Integer.MAX_VALUE, Integer.MIN_VALUE );

		return ( ( int )value );
	}

	//=============================================================================
	public static short toShort( long value ) throws ConstraintError
	//=============================================================================
	{
		checkRange( value, Short.MAX_VALUE, Short.MIN_VALUE );

		return ( ( short )value );
	}

	//=============================================================================
	public static short toByte( long value ) throws ConstraintError
	//=============================================================================
	{
		checkRange( value, Byte.MAX_VALUE, Byte.MIN_VALUE );

		return ( ( short )value );
	}

}
