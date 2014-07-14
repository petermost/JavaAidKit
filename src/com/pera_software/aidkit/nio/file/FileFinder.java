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

package com.pera_software.aidkit.nio.file;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import com.pera_software.aidkit.util.regex.*;

//##################################################################################################

public final class FileFinder
{
	//==============================================================================================

	private FileFinder()
	{
	}

	//==============================================================================================

	public static List< Path > find( Path startDirectoryPath, String ... wildcardPatterns )
		throws Exception
	{
		final Pattern filePatterns[] = Patterns.convertWildcardPatternsToRegularExpressions( wildcardPatterns );
		final List< Path > foundFiles = new ArrayList<>();

		Files.walkFileTree( startDirectoryPath, new SimpleFileVisitor< Path >() {
			@Override
			public FileVisitResult visitFile( Path file, BasicFileAttributes attributes ) throws IOException {
				String fileName = file.toString();
				for ( Pattern filePattern : filePatterns ) {
					Matcher matcher = filePattern.matcher( fileName );
					if ( matcher.matches() )
						foundFiles.add( file );
				}
				return super.visitFile( file, attributes );
			}
		});
		return foundFiles;
	}

	//==============================================================================================

	

	//==============================================================================================



}
