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

package com.pera_software.aidkit.visualstudio;

import java.util.*;
import java.util.regex.*;

//##################################################################################################

public class CopyCommandParser
{
	//==============================================================================================

	public static List< String > findDestinationDirectoryNames( List< String > prePostBuildCommands )
	{
		List< String > directoryNames = new ArrayList<>();

		for ( String command : prePostBuildCommands ) {
			if ( containsCopyCommand( command )) {

				// If we have two arguments then we have a simple copy <source> <destination>
				// command, if we have three arguments then we have a robocopy with <source>
				// <destination> <wildcard> command:

				List< String > quotedArguments = findQuotedArguments( command );
				if ( quotedArguments.size() == 2 || quotedArguments.size() == 3 ) {
					directoryNames.add( quotedArguments.get( 1 ));
				}
			}
		}
		return directoryNames;
	}

	//==============================================================================================

	private static List< String > findQuotedArguments( String command )
	{
		List< String > arguments = new ArrayList<>();

		Pattern pattern = Pattern.compile( "\".*?\"");
		Matcher matcher = pattern.matcher( command );

		while ( matcher.find() ) {
			String argument = command.substring( matcher.start(), matcher.end() );
			argument = unquote( argument );
			argument = argument.trim();
			arguments.add( argument );
		}
		return arguments;
	}

	//==============================================================================================

	private static boolean containsCopyCommand( String command )
	{
		command = command.toLowerCase();
		if ( command.contains( "echo" )) {
			return false;
		}
		return command.contains( "copy" ) || command.contains( "xcopy" ) || command.contains( "robocopy" );
	}

	//==============================================================================================

	private static String unquote( String s )
	{
		final String QUOTES = "\"";

		return s.replace( QUOTES, "" );
	}
}
