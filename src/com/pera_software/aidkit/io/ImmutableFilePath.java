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

//##################################################################################################

public final class ImmutableFilePath {
	private FilePath _path;

	//==============================================================================================

	public ImmutableFilePath() {
		_path = new FilePath();
	}
	
	public ImmutableFilePath( String path ) {
		_path = new FilePath( path );
	}

	public ImmutableFilePath( ImmutableFilePath other ) {
		_path = new FilePath( other._path );
	}
	
	private ImmutableFilePath( FilePath path ) {
		_path = path;
	}
	
	//==============================================================================================

	@Override
	public String toString() {
		return _path.toString();
	}

	public ImmutableFilePath setDrive( String drive ) {
		FilePath path = new FilePath( _path );
		path.setDrive( drive );
		
		return new ImmutableFilePath( path );
	}

	public String getDrive() {
		return _path.getDrive();
	}

	public ImmutableFilePath removeDrive() {
		FilePath path = new FilePath( _path );
		path.removeDrive();
		
		return new ImmutableFilePath( path );
	}

	//==============================================================================================

	public ImmutableFilePath addDirectory( String directory ) {
		FilePath path = new FilePath( _path );
		path.addDirectory( directory );
		
		return new ImmutableFilePath( path );
	}

	public ImmutableFilePath addDirectory( int index, String directory ) {
		FilePath path = new FilePath( _path );
		path.addDirectory( index, directory );
		
		return new ImmutableFilePath( path );
	}

	public ImmutableFilePath setDirectory( int index, String directory ) {
		FilePath path = new FilePath( _path );
		path.setDirectory( index, directory );
		
		return new ImmutableFilePath( path );
	}

	public String getDirectory( int index ) {
		return _path.getDirectory( index );
	}

	public ImmutableFilePath removeDirectory( int index ) {
		FilePath path = new FilePath( _path );
		path.removeDirectory( index );
		
		return new ImmutableFilePath( path );
	}

	//==============================================================================================

	public ImmutableFilePath setName( String name ) {
		FilePath path = new FilePath( _path );
		path.setName( name );
		
		return new ImmutableFilePath( path );
	}

	public String getName() {
		return _path.getName();
	}

	public ImmutableFilePath removeName() {
		FilePath path = new FilePath( _path );
		path.removeName();
		
		return new ImmutableFilePath( path );
	}

	//==============================================================================================

	public ImmutableFilePath addExtension( String extension ) {
		FilePath path = new FilePath( _path );
		path.addExtension( extension );
		
		return new ImmutableFilePath( path );
	}

	public ImmutableFilePath addExtension( int index, String extension ) {
		FilePath path = new FilePath( _path );
		path.addExtension( index, extension );
		
		return new ImmutableFilePath( path );
	}

	public ImmutableFilePath setExtension( int index, String extension ) {
		FilePath path = new FilePath( _path );
		path.setExtension( index, extension );
		
		return new ImmutableFilePath( path );
	}

	//==============================================================================================
	
	/** 
	 * Get the last extension: 
	 */
	public String getExtension() {
		return getExtension( -1 );
	}

	public String getExtension( int index ) {
		return _path.getExtension( index );
	}

	/** 
	 * Remove the last extension: 
	 */
	public ImmutableFilePath removeExtension() {
		return removeExtension( -1 );
	}

	public ImmutableFilePath removeExtension( int index ) {
		FilePath path = new FilePath( _path );
		path.removeExtension( index );
		
		return new ImmutableFilePath( path );
	}
}
