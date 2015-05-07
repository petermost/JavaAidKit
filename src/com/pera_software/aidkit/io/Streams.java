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

package com.pera_software.aidkit.io;

import java.io.*;
import static com.pera_software.aidkit.eclipse.NullObjects.*;

//##################################################################################################

public final class Streams {
	private static final int BUFFER_SIZE = 8192;

	//==============================================================================================

	private Streams() {
	}

	//==============================================================================================

	/**
	 * Copy the content of one stream to another stream.
	 */
	public static void copy( InputStream inputStream, OutputStream outputStream ) throws IOException {
		int length;
		byte buffer[] = new byte[ BUFFER_SIZE ];
		while ( ( length = inputStream.read( buffer ) ) > 0 ) {
			outputStream.write( buffer, 0, length );
		}
	}

	//==============================================================================================

	/**
	 * Read the complete content from the stream.
	 */
	public static String read( InputStream inputStream ) throws IOException {
		int length;

		char buffer[] = new char[ BUFFER_SIZE ];
		StringBuilder content = new StringBuilder( BUFFER_SIZE );
		InputStreamReader streamReader = new InputStreamReader( inputStream );
		while ( ( length = streamReader.read( buffer ) ) > 0 )
			content.append( buffer, 0, length );

		return requireNonNull( content.toString() );
	}

	//==============================================================================================

	/**
	 * Write the content to the stream.
	 */
	public static void write( OutputStream outputStream, String content ) throws IOException {
		OutputStreamWriter streamWriter = new OutputStreamWriter( outputStream );
		streamWriter.write( content );
		streamWriter.flush();
	}
}
