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

public final class SizeOf {
	//===========================================================================

	public final static int BYTE_SIZE = Byte.SIZE / Byte.SIZE;

	public final static int CHAR_SIZE = Character.SIZE / Byte.SIZE;
	public final static int CHARACTER_SIZE = CHAR_SIZE;

	public final static int SHORT_SIZE = Short.SIZE / Byte.SIZE;

	public final static int INT_SIZE = Integer.SIZE / Byte.SIZE;
	public final static int INTEGER_SIZE = INT_SIZE;

	public final static int LONG_SIZE = Long.SIZE / Byte.SIZE;

	//===========================================================================

	public static final int sizeof( @SuppressWarnings( "unused" ) byte unused ) {
		return BYTE_SIZE;
	}

	public static final int sizeof( @SuppressWarnings( "unused" ) short unused ) {
		return SHORT_SIZE;
	}

	public static final int sizeof( @SuppressWarnings( "unused" ) int unused ) {
		return INT_SIZE;
	}

	public static int sizeof( @SuppressWarnings( "unused" ) long unused ) {
		return LONG_SIZE;
	}

}
