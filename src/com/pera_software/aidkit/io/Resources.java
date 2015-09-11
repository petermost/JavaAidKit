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

import java.net.*;
import java.nio.file.*;
import static com.pera_software.aidkit.nullable.NullObjects.*;


/**
 * Allow to load resources without the need to handle exceptions. These methods can be used to
 * initialize a static final variable were you can't handle exceptions.
 */
public final class Resources {
	
	private Resources() {
	}
	
	public static Path getPath( Class< ? > parentClass, String resourceName ) {
		try {
			URL resourceUrl = parentClass.getResource( resourceName );
			URI resourceUri = resourceUrl.toURI();
			return requireNonNull( Paths.get( resourceUri ));
		} catch ( Exception exception ) {
			throw new ExceptionInInitializerError( exception );
		}
	}
}
