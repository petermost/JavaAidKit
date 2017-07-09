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
import static com.pera_software.aidkit.io.FilePathUtils.*;

//##################################################################################################

/**
 * This class represents an immutable file path i.e. each 'modifying' method returns a new
 * FilePath instance.
 * 
 * @author P. Most
 */

public final class FilePath extends FilePathBase {
	public enum ToStringOptions {
		Drive, Directories, Name, Extensions
	}
	
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

	public FilePath setDrive( String drive ) {
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

	public FilePath addDirectory( String directory ) {
		FilePath copy = new FilePath( this );
		copy._directories.add( addDirectorySeparator( directory ));
		
		return copy;
	}

	public FilePath addDirectory( int index, String directory ) {
		FilePath copy = new FilePath( this );
		copy._directories.add( index, addDirectorySeparator( directory ));
		
		return copy;
	}

	public FilePath setDirectory( int index, String directory ) {
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
		if ( _directories.isIndexWithinBounds( index ))
			return _directories.get( index );
		else
			return Strings.EMPTY;
	}
	
	//==============================================================================================

	public FilePath setName( String name ) {
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

	public FilePath addExtension( String extension ) {
		FilePath copy = new FilePath( this );
		copy._extensions.add( addExtensionSeparator( extension ));
		
		return copy;
	}

	public FilePath addExtension( int index, String extension ) {
		FilePath copy = new FilePath( this );
		copy._extensions.add( index, addExtensionSeparator( extension ));
		
		return copy;
	}

	public FilePath setExtension( int index, String extension ) {
		FilePath copy = new FilePath( this );
		copy._extensions.set( index, addExtensionSeparator( extension ));
		
		return copy;
	}
	
	public FilePath setExtension( String extension ) {
		return setExtension( -1, extension );
	}
	
	public FilePath removeExtension( int index ) {
		FilePath copy = new FilePath( this );
		copy._extensions.remove( index );
		
		return copy;
	}
	
	public FilePath removeExtension() {
		return removeExtension( -1 );
	}

	public String extensionAt( int index ) {
		if ( _extensions.isIndexWithinBounds( index ))
			return _extensions.get( index );
		else
			return Strings.EMPTY;
	}
	
	public String extension() {
		return extensionAt( -1 );
	}

}
