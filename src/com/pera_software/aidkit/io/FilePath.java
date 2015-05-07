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

import java.util.*;
import com.pera_software.aidkit.collection.*;

//##################################################################################################

public class FilePath
{
	private static final char DRIVE_SEPARATOR     = ':';
	private static final char DIRECTORY_SEPARATOR = '/'; // Works on all platforms.
	private static final char EXTENSION_SEPARATOR = '.';

	private String _drive = null;
	private CyclicList< String > _directories = new CyclicList<>( new ArrayList< String >() );
	private String _name = null;
	private CyclicList< String > _extensions = new CyclicList<>( new ArrayList< String >() );

	//==============================================================================================

	public FilePath( String path )
	{
		final char WINDOWS_DIRECTORY_SEPARATOR = '\\';
		final char UNIX_DIRECTORY_SEPARATOR = '/';

		char c;
		boolean isEnd;
		int begin = 0, end = 0;

		// Extract the drive and the directories:

		for ( end = 0; end < path.length(); ++end ) {
			if (( c = path.charAt( end )) == DRIVE_SEPARATOR ) {
				setDrive( path.substring( begin, end + 1 ));
				begin = end + 1;
			} else if ( c == UNIX_DIRECTORY_SEPARATOR || c == WINDOWS_DIRECTORY_SEPARATOR ) {
				addDirectory( path.substring( begin, end + 1 ));
				begin = end + 1;
			}
		}
		if ( getDrive() == null )
			setDrive( "" );

		// Extract the name and the extensions:

		for ( end = begin; end < path.length(); ++end ) {
			if (( isEnd = ( end == path.length() - 1 )) || ( c = path.charAt( end )) == EXTENSION_SEPARATOR ) {
				if ( getName() == null )
					setName( path.substring( begin, isEnd ? end + 1 : end ) );
				else
					addExtension( path.substring( begin, isEnd ? end + 1 : end ) );

				begin = end;
			}
		}
		if ( getName() == null )
			setName( "" );
	}

	//==============================================================================================

	@Override
	public String toString()
	{
		StringBuilder path = new StringBuilder();

		path.append( _drive );
		for ( String directory : _directories )
			path.append( directory );

		path.append( _name );

		for ( String extension : _extensions )
			path.append( extension );

		return ( path.toString() );
	}

	//==============================================================================================

	private static String addPrefixSeparator( String string, char separator )
	{
		if ( !string.isEmpty() && string.charAt( 0 ) != separator )
			return ( separator + string );
		else
			return ( string );
	}

	private static String addPostfixSeparator( String string, char separator )
	{
		if ( !string.isEmpty() && string.charAt( string.length() - 1 ) != separator )
			return ( string + separator );
		else
			return ( string );
	}

	//==============================================================================================

	private static String addDriveSeparator( String device )
	{
		return ( addPostfixSeparator( device, DRIVE_SEPARATOR ) );
	}

	public void setDrive( String drive )
	{
		_drive = addDriveSeparator( drive );
	}

	public String getDrive()
	{
		return ( _drive );
	}

	public void removeDrive()
	{
		_drive = "";
	}

	//==============================================================================================

	private static String addDirectorySeparator( String directory )
	{
		return ( addPostfixSeparator( directory, DIRECTORY_SEPARATOR ) );
	}

	public void addDirectory( String directory )
	{
		_directories.add( addDirectorySeparator( directory ) );
	}

	public void addDirectory( int index, String directory )
	{
		_directories.add( index, addDirectorySeparator( directory ) );
	}

	public void setDirectory( int index, String directory )
	{
		_directories.set( index, addDirectorySeparator( directory ) );
	}

	public String getDirectory( int index )
	{
		return ( _directories.get( index, "" ) );
	}

	public void removeDirectory( int index )
	{
		_directories.remove( index );
	}

	//==============================================================================================

	public void addName( String name )
	{
		setName( name );
	}

	public void setName( String name )
	{
		_name = name;
	}

	public String getName()
	{
		return ( _name );
	}

	public void removeName()
	{
		_name = "";
	}

	//==============================================================================================

	private static String addExtensionSeparator( String extension )
	{
		return ( addPrefixSeparator( extension, EXTENSION_SEPARATOR ) );
	}

	public void addExtension( String extension )
	{
		_extensions.add( addExtensionSeparator( extension ) );
	}

	public void addExtension( int index, String extension )
	{
		_extensions.add( index, addExtensionSeparator( extension ) );
	}

	public void setExtension( int index, String extension )
	{
		_extensions.set( index, addExtensionSeparator( extension ) );
	}

	/** Get the last extension: */
	public String getExtension()
	{
		return ( getExtension( -1 ) );
	}

	public String getExtension( int index )
	{
		return ( _extensions.get( index, "" ) );
	}

	/** Remove the last extension: */
	public void removeExtension()
	{
		removeExtension( -1 );
	}

	public void removeExtension( int index )
	{
		_extensions.remove( index );
	}
}
