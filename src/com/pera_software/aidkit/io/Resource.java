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

package com.pera_software.aidkit.io;

import java.io.*;
import java.net.*;
import java.nio.file.*;

/**
 * @author P. Most
 *
 */
public class Resource {
	
	private Class< ? > _resourceClass;
	private String _resourceName;

	//==============================================================================================
	
	public Resource( Class< ? > resourceClass, String resourceName ) {
		_resourceClass = resourceClass;
		_resourceName = resourceName;
	}
	
	//==============================================================================================
	
	public Path getAsPath() throws Exception {
		URL resourceUrl = _resourceClass.getResource( _resourceName );
		URI resourceUri = resourceUrl.toURI();
		
		return Paths.get( resourceUri );
	}

	//==============================================================================================
	
	public InputStream getAsStream() throws Exception {
		InputStream inputStream = _resourceClass.getResourceAsStream( _resourceName );
		if ( inputStream != null )
			return inputStream;
		else
			throw new ResourceNotFoundException( _resourceName );
	}
}
