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

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class FileFinder
{
	static String convertWildcardPatternToRegularExpression( String wildcardPattern )
	{
		StringBuilder regularExpression = new StringBuilder( wildcardPattern.length() );
		for ( int i = 0; i < wildcardPattern.length(); ++i ) {
			char c = wildcardPattern.charAt( i );
			switch ( c ) {
				case '?':
					regularExpression.append( '.' );
					break;

				case '*':
					regularExpression.append( '.' ).append( '*' );
					break;

				case '.':
				case '[':
				case ']':
				case '^':
				case '$':
				case '(':
				case ')':
				case '{':
				case '}':
				case '+':
				case '|':
				case '\\':
					regularExpression.append( '\\' ).append( c );
					break;

				default:
					regularExpression.append( c );
					break;
			}
		}
		return regularExpression.toString();
	}


	private static Pattern[] convertWildcardPatternsToRegularExpressions( String ... wildcardPatterns )
	{
		Pattern regularExpressions[] = new Pattern[ wildcardPatterns.length ];
		for ( int i = 0; i < wildcardPatterns.length; ++i ) {
			String regularExpression = convertWildcardPatternToRegularExpression(wildcardPatterns[ i ]);
			regularExpressions[ i ] = Pattern.compile( regularExpression );
		}
		return regularExpressions;
	}



	public static List< Path > find( Path startDirectoryPath, String ... wildcardPatterns ) throws Exception
	{
		final Pattern filePatterns[] = convertWildcardPatternsToRegularExpressions( wildcardPatterns );
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

	private FileFinder()
	{
	}
}
