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
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.io;

import java.io.*;
import java.net.*;
import java.nio.file.*;

/**
 * Allow to load resources without the need to handle exceptions. These methods can be used to
 * initialize a static final variable were you can't handle exceptions.
 */
public final class Resources {
	
	private Resources() {
	}

	//==============================================================================================
	
	public static URL asUrl( Class< ? > clazz, String name ) throws ResourceNotFoundException {
		URL resourceUrl = clazz.getResource( name );
		if ( resourceUrl != null )
			return resourceUrl;
		else
			throw new ResourceNotFoundException( name );
	}
	
	//==============================================================================================
	
	public static URI asUri( Class< ? > clazz, String name ) throws ResourceNotFoundException {
		try {
			return asUrl( clazz, name ).toURI();
		} 
		catch ( URISyntaxException syntaxException ) {
			throw new ResourceNotFoundException( name, syntaxException );
		}
	}
	
	//==============================================================================================
	
	public static Path asPath( Class< ? > clazz, String name ) throws ResourceNotFoundException {
		return Paths.get( asUri( clazz, name ));
	}
	
	//==============================================================================================
	
	public static InputStream asStream( Class< ? > clazz, String name ) throws ResourceNotFoundException {
		InputStream inputStream = clazz.getResourceAsStream( name );
		if ( inputStream != null )
			return inputStream;
		else
			throw new ResourceNotFoundException( name );
	}
}
