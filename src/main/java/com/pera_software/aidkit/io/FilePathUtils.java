// Copyright 2016 Peter Most, PERA Software Solutions GmbH
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

/**
 * @author P. Most
 *
 */
public final class FilePathUtils extends FilePathBase {

	private FilePathUtils()
	{
	}

	public static String addPrefixSeparator(String string, char separator)
	{
		if (!string.isEmpty() && string.charAt(0) != separator)
			return separator + string;
		else
			return string;
	}

	public static String addPostfixSeparator(String string, char separator)
	{
		if (!string.isEmpty() && string.charAt(string.length() - 1) != separator)
			return string + separator;
		else
			return string;
	}

	public static String addDriveSeparator(String drive)
	{
		return addPostfixSeparator(drive, FilePath.DRIVE_SEPARATOR);
	}

	public static String addDirectorySeparator(String directory)
	{
		return addPostfixSeparator(directory, FilePath.DIRECTORY_SEPARATOR);
	}

	public static String addExtensionSeparator(String extension)
	{
		return addPrefixSeparator(extension, FilePath.EXTENSION_SEPARATOR);
	}

}
