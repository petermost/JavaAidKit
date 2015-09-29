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

package com.pera_software.aidkit.lang;

import static com.pera_software.aidkit.nullable.NullStrings.*;

//##################################################################################################
/**
 * Wrap the call to System.getProperty to avoid failing calls because of mistyped
 * key names.
 */
public final class SystemProperties {
	private static final String CLASS_PATH_KEY     = "java.class.path";
	private static final String PATH_SEPARATOR_KEY = "path.separator";
	private static final String FILE_SEPARATOR_KEY = "file.separator";
	private static final String LINE_SEPARATOR_KEY = "line.separator";
	private static final String TMP_DIR_KEY        = "java.io.tmpdir";
	private static final String USER_DIR_KEY       = "user.dir";

	//==============================================================================================

	private SystemProperties() {
	}

	//==============================================================================================

	public static String setClassPath( String classPath ) {
		return makeNonNull( System.setProperty( CLASS_PATH_KEY, classPath ));
	}

	//==============================================================================================

	public static String getClassPath() {
		return makeNonNull( System.getProperty( CLASS_PATH_KEY ));
	}

	//==============================================================================================
	/**
	 * @return ":" under UNIX and ";" under Windows
	 */
	public static String getPathSeparator() {
		return makeNonNull( System.getProperty( PATH_SEPARATOR_KEY ));
	}

	//==============================================================================================
	/**
	 * @return "/" under UNIX and "\" under Windows
	 */
	public static String getFileSeparator() {
		return makeNonNull( System.getProperty( FILE_SEPARATOR_KEY ));
	}

	//==============================================================================================
	/**
	 * @return "\n" under UNIX and "\r\n" under Windows
	 */
	public static String getLineSeparator() {
		return makeNonNull( System.getProperty( LINE_SEPARATOR_KEY ));
	}

	//==============================================================================================

	public static String getTmpDir() {
		return makeNonNull( System.getProperty( TMP_DIR_KEY ));
	}

	//==============================================================================================

	public static String getCurrentWorkingDirectory() {
		return makeNonNull( System.getProperty( USER_DIR_KEY ));
	}
}
