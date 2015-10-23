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

import java.lang.reflect.*;
import java.net.*;
import com.pera_software.aidkit.io.*;

/**
 * Query properties of classes.
 */
public final class Classes {

	//==============================================================================================
	
	private Classes() {
	}

	//==============================================================================================
	
	/**
	 * Try to get the resource as an URL but throws an exception if the given resource name wasn't 
	 * found.
	 * 
	 * @see Class#getResource(String)
	 */
	public static URL getResource( Class< ? > clazz, String resourceName ) throws ResourceNotFoundException {
		URL url = clazz.getResource( resourceName );
		if ( url != null )
			return url;
		else
			throw new ResourceNotFoundException( resourceName );
	}
	
	//==============================================================================================
	
	/**
	 * Is the class abstract
	 */
	public static boolean isAbstract( Class< ? > clazz ) {
		return Modifier.isAbstract( clazz.getModifiers() );
	}
	
	// TODO: Add more property queries.
}
