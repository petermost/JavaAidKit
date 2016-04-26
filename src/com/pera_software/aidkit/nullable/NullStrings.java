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

import com.pera_software.aidkit.lang.*;

/**
 * This class is similar to {@link NullObjects} but for strings and instead of throwing an 
 * exception it returns an empty string.
 * 
 * @author P. Most
 */
public final class NullStrings {

	private NullStrings() {
	}

	//==============================================================================================
	
	public static String makeNonNull( String str ) {
		if ( str != null )
			return str;
		else
			return Strings.EMPTY;
	}
}
