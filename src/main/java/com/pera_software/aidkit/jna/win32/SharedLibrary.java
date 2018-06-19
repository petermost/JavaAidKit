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

package com.pera_software.aidkit.jna.win32;

import java.nio.file.*;
import java.util.*;
import com.sun.jna.*;
import com.sun.jna.win32.*;

/**
 * @author P. Most
 *
 */
public class SharedLibrary< Library extends StdCallLibrary > {

	private Library _instance;

	@SuppressWarnings( "unchecked" )
	protected SharedLibrary( Path sharedLibraryName, Class< Library > sharedLibraryClass ) {

		// Map java names like 'SomeName' to stdcall names ('_SomeName@4'):

		Map< String, StdCallFunctionMapper > options = new HashMap<>();
		options.put( Library.OPTION_FUNCTION_MAPPER, new StdCallFunctionMapper() );

		_instance = ( Library )Native.loadLibrary( sharedLibraryName.toAbsolutePath().toString(), sharedLibraryClass, options );
	}

	protected Library instance() {
		return _instance;
	}
}
