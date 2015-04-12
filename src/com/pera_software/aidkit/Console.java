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
// along with JavaAidKit.  If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit;

import static com.pera_software.aidkit.eclipse.NullObjects.*;
import java.io.*;

public final class Console {
	private static void print( PrintStream outputStream, String format, Object arguments[] ) {
		String message = String.format( format, arguments );
		outputStream.format( "%s\n", message );
	}

	public static void printStatus( String format, Object ... arguments ) {
		print( requireNonNull( System.out ), format, arguments );
	}

	public static void printError( String format, Object ... arguments ) {
		print( requireNonNull( System.err ), format, arguments );
	}

	private Console() {
	}
}
