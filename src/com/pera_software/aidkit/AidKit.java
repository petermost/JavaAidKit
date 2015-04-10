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

package com.pera_software.aidkit;

import java.io.*;
import java.util.*;

/**
 * @author P. Most
 *
 */
public final class AidKit {
	private static final String PACKAGE_NAME = "/com/pera_software/aidkit/icons16x16/";

	// The resource names must be valid Java identifiers!!!

	public static final String APPLICATION_EXIT_ICON = PACKAGE_NAME + "application_exit.png";
	public static final String DOCUMENT_OPEN_ICON    = PACKAGE_NAME + "document_open.png";
	
	private AidKit() {
	}
	
	@SuppressWarnings( "null" )
	public static InputStream getResourceAsStream( String resourceName ) {
		return Objects.requireNonNull( AidKit.class.getResourceAsStream( resourceName ));
	}
}
