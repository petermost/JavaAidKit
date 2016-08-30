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

import com.pera_software.aidkit.lang.*;
import java.util.*;
import com.pera_software.aidkit.collection.*;

//##################################################################################################

/**
 * This class represents an immutable file path i.e. each 'modifying' method returns a new
 * FilePath instance.
 * 
 * @author P. Most
 */

// TODO: Try to replace try/catch in *At() methods.

public final class FilePath {
	public enum ToStringOptions {
		Drive, Directories, Name, Extensions
	}
	
	private static final char DRIVE_SEPARATOR = ':';
	private static final char DIRECTORY_SEPARATOR = '/'; // Works on all platforms.
	private static final char EXTENSION_SEPARATOR = '.';

	private String _drive = Strings.EMPTY;
	private CyclicList< String > _directories = new CyclicList<>();
	private String _name = Strings.EMPTY;
	private CyclicList< String > _extensions = new CyclicList<>();

	//==============================================================================================

	public FilePath() {
		this( Strings.EMPTY );
	}
	
	public FilePath( String path ) {
		final char WINDOWS_DIRECTORY_SEPARATOR = '\\';
		final char UNIX_DIRECTORY_SEPARATOR = '/';

		char c;
		boolean isEnd;
		int begin = 0, end = 0;

		// Extract the drive and the directories:

		for ( end = 0; end < path.length(); ++end ) {
			if (( c = path.charAt( end )) == DRIVE_SEPARATOR ) {
				_drive = path.substring( begin, end + 1 );
				begin = end + 1;
			} else if ( c == UNIX_DIRECTORY_SEPARATOR || c == WINDOWS_DIRECTORY_SEPARATOR ) {
				_directories.add( path.substring( begin, end + 1 ));
				begin = end + 1;
			}
		}
		// Extract the name and the extensions:

		boolean hasName = false;
		for ( end = begin; end < path.length(); ++end ) {
			if (( isEnd = ( end == path.length() - 1 )) || ( c = path.charAt( end )) == EXTENSION_SEPARATOR ) {
				if ( !hasName ) {
					_name = path.substring( begin, isEnd ? end + 1 : end );
					hasName = true;
				}
				else
					_extensions.add( path.substring( begin, isEnd ? end + 1 : end ));

				begin = end;
			}
		}
	}

	public FilePath( FilePath other ) {
		_drive = other._drive;
		_directories = new CyclicList<>( other._directories );
		_name = other._name;
		_extensions = new CyclicList<>( other._extensions );
	}
	
	//==============================================================================================

	public String toString( EnumSet< ToStringOptions > options ) {
		StringBuilder builder = new StringBuilder();
		
		for ( ToStringOptions option : options ) {
			switch ( option ) {
				case Drive:
					builder.append( _drive );
					break;
					
				case Directories:
					for ( String directory : _directories )
						builder.append( directory );
					break;
					
				case Name:
					builder.append( _name );
					break;
					
				case Extensions:
					for ( String extension : _extensions )
						builder.append( extension );
					break;
			}
		}
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return toString( EnumSet.of( ToStringOptions.Drive, ToStringOptions.Directories, ToStringOptions.Name, ToStringOptions.Extensions ));
	}

	//==============================================================================================

	private static String addPrefixSeparator( String string, char separator ) {
		if ( !string.isEmpty() && string.charAt( 0 ) != separator )
			return separator + string;
		else
			return string;
	}

	private static String addPostfixSeparator( String string, char separator ) {
		if ( !string.isEmpty() && string.charAt( string.length() - 1 ) != separator )
			return string + separator;
		else
			return string;
	}

	//==============================================================================================

	private static String addDriveSeparator( String device ) {
		return addPostfixSeparator( device, DRIVE_SEPARATOR );
	}

	public FilePath replaceDrive( String drive ) {
		FilePath copy = new FilePath( this );
		copy._drive = addDriveSeparator( drive );
		
		return copy;
	}

	public FilePath removeDrive() {
		FilePath copy = new FilePath( this );
		copy._drive = Strings.EMPTY;
		
		return copy;
	}

	public String drive() {
		return _drive;
	}
	
	//==============================================================================================

	private static String addDirectorySeparator( String directory ) {
		return addPostfixSeparator( directory, DIRECTORY_SEPARATOR );
	}

	public FilePath appendDirectory( String directory ) {
		FilePath copy = new FilePath( this );
		copy._directories.add( addDirectorySeparator( directory ));
		
		return copy;
	}

	public FilePath insertDirectory( int index, String directory ) {
		FilePath copy = new FilePath( this );
		copy._directories.add( index, addDirectorySeparator( directory ));
		
		return copy;
	}

	public FilePath replaceDirectory( int index, String directory ) {
		FilePath copy = new FilePath( this );
		copy._directories.set( index, addDirectorySeparator( directory ));
		
		return copy;
	}

	public FilePath removeDirectory( int index ) {
		FilePath copy = new FilePath( this );
		copy._directories.remove( index );
		
		return copy;
	}

	public String directoryAt( int index ) {
		try {
			return _directories.get( index );
		} catch ( IndexOutOfBoundsException e ) {
			return Strings.EMPTY;
		}
	}
	
	//==============================================================================================

	public FilePath replaceName( String name ) {
		FilePath copy = new FilePath( this );
		copy._name = name;
		
		return copy;
	}

	public FilePath removeName() {
		FilePath copy = new FilePath( this );
		copy._name = Strings.EMPTY;
		
		return copy;
	}

	public String name() {
		return _name;
	}
	
	//==============================================================================================

	private static String addExtensionSeparator( String extension ) {
		return addPrefixSeparator( extension, EXTENSION_SEPARATOR );
	}

	public FilePath appendExtension( String extension ) {
		FilePath copy = new FilePath( this );
		copy._extensions.add( addExtensionSeparator( extension ));
		
		return copy;
	}

	public FilePath insertExtension( int index, String extension ) {
		FilePath copy = new FilePath( this );
		copy._extensions.add( index, addExtensionSeparator( extension ));
		
		return copy;
	}

	public FilePath replaceExtension( int index, String extension ) {
		FilePath copy = new FilePath( this );
		copy._extensions.set( index, addExtensionSeparator( extension ));
		
		return copy;
	}
	
	public FilePath removeExtension( int index ) {
		FilePath copy = new FilePath( this );
		copy._extensions.remove( index );
		
		return copy;
	}

	public String extensionAt( int index ) {
		try {
			return _extensions.get( index );
		} catch ( IndexOutOfBoundsException e ) {
			return Strings.EMPTY;
		}
	}
	
	//==============================================================================================
	
	/**
	 * Some convenience methods to access/modify the last index.
	 */
	
	public FilePath removeLastExtension() {
		return removeExtension( -1 );
	}

	public String lastExtension() {
		return extensionAt( -1 );
	}
}
