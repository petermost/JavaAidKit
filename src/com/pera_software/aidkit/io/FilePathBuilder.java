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
import com.pera_software.aidkit.collection.*;
import static com.pera_software.aidkit.nullable.NullStrings.*;

//##################################################################################################

public final class FilePathBuilder {
	private static final char DRIVE_SEPARATOR = ':';
	private static final char DIRECTORY_SEPARATOR = '/'; // Works on all platforms.
	private static final char EXTENSION_SEPARATOR = '.';

	private String _drive = Strings.EMPTY;
	private CyclicList< String > _directories = new CyclicList<>();
	private String _name = Strings.EMPTY;
	private CyclicList< String > _extensions = new CyclicList<>();

	//==============================================================================================

	public FilePathBuilder() {
		this( Strings.EMPTY );
	}
	
	public FilePathBuilder( String path ) {
		final char WINDOWS_DIRECTORY_SEPARATOR = '\\';
		final char UNIX_DIRECTORY_SEPARATOR = '/';

		char c;
		boolean isEnd;
		int begin = 0, end = 0;

		// Extract the drive and the directories:

		for ( end = 0; end < path.length(); ++end ) {
			if (( c = path.charAt( end )) == DRIVE_SEPARATOR ) {
				setDrive( makeNonNull( path.substring( begin, end + 1 )));
				begin = end + 1;
			} else if ( c == UNIX_DIRECTORY_SEPARATOR || c == WINDOWS_DIRECTORY_SEPARATOR ) {
				addDirectory( makeNonNull( path.substring( begin, end + 1 )));
				begin = end + 1;
			}
		}
		// Extract the name and the extensions:

		boolean hasName = false;
		for ( end = begin; end < path.length(); ++end ) {
			if (( isEnd = ( end == path.length() - 1 )) || ( c = path.charAt( end )) == EXTENSION_SEPARATOR ) {
				if ( !hasName ) {
					setName( makeNonNull( path.substring( begin, isEnd ? end + 1 : end )));
					hasName = true;
				}
				else
					addExtension( makeNonNull( path.substring( begin, isEnd ? end + 1 : end )));

				begin = end;
			}
		}
	}

	public FilePathBuilder( FilePathBuilder other ) {
		_drive = other._drive;
		_directories = new CyclicList<>( other._directories );
		_name = other._name;
		_extensions = new CyclicList<>( other._extensions );
	}
	
	//==============================================================================================

	@Override
	public String toString() {
		StringBuilder path = new StringBuilder();

		path.append( _drive );
		for ( String directory : _directories )
			path.append( directory );

		path.append( _name );

		for ( String extension : _extensions )
			path.append( extension );

		return makeNonNull( path.toString() );
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

	public FilePathBuilder setDrive( String drive ) {
		_drive = addDriveSeparator( drive );
		
		return this;
	}

	public String getDrive() {
		return _drive;
	}

	public FilePathBuilder removeDrive() {
		_drive = Strings.EMPTY;
		
		return this;
	}

	//==============================================================================================

	private static String addDirectorySeparator( String directory ) {
		return addPostfixSeparator( directory, DIRECTORY_SEPARATOR );
	}

	public FilePathBuilder addDirectory( String directory ) {
		_directories.add( addDirectorySeparator( directory ));
		
		return this;
	}

	public FilePathBuilder addDirectory( int index, String directory ) {
		_directories.add( index, addDirectorySeparator( directory ));
		
		return this;
	}

	public FilePathBuilder setDirectory( int index, String directory ) {
		_directories.set( index, addDirectorySeparator( directory ));
		
		return this;
	}

	public String getDirectory( int index ) {
		return _directories.get( index, Strings.EMPTY );
	}

	public FilePathBuilder removeDirectory( int index ) {
		_directories.remove( index );
		
		return this;
	}

	//==============================================================================================

	public FilePathBuilder setName( String name ) {
		_name = name;
		
		return this;
	}

	public String getName() {
		return _name;
	}

	public FilePathBuilder removeName() {
		_name = Strings.EMPTY;
		
		return this;
	}

	//==============================================================================================

	private static String addExtensionSeparator( String extension ) {
		return addPrefixSeparator( extension, EXTENSION_SEPARATOR );
	}

	public FilePathBuilder addExtension( String extension ) {
		_extensions.add( addExtensionSeparator( extension ));
		
		return this;
	}

	public FilePathBuilder addExtension( int index, String extension ) {
		_extensions.add( index, addExtensionSeparator( extension ));
		
		return this;
	}

	public FilePathBuilder setExtension( int index, String extension ) {
		_extensions.set( index, addExtensionSeparator( extension ));
		
		return this;
	}

	//==============================================================================================
	
	/** 
	 * Get the last extension: 
	 */
	public String getExtension() {
		return getExtension( -1 );
	}

	public String getExtension( int index ) {
		return _extensions.get( index, Strings.EMPTY );
	}

	/** 
	 * Remove the last extension: 
	 */
	public FilePathBuilder removeExtension() {
		return removeExtension( -1 );
	}

	public FilePathBuilder removeExtension( int index ) {
		_extensions.remove( index );
		
		return this;
	}
}
