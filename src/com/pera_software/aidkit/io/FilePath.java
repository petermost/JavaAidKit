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

public final class FilePath {
	private FilePathBuilder _builder;

	//==============================================================================================

	public FilePath() {
		_builder = new FilePathBuilder();
	}
	
	public FilePath( String path ) {
		_builder = new FilePathBuilder( path );
	}

	public FilePath( FilePath other ) {
		_builder = new FilePathBuilder( other._builder );
	}
	
	private FilePath( FilePathBuilder path ) {
		_builder = path;
	}
	
	//==============================================================================================

	@Override
	public String toString() {
		return _builder.toString();
	}

	public FilePath setDrive( String drive ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.setDrive( drive );
		
		return new FilePath( path );
	}

	public String getDrive() {
		return _builder.getDrive();
	}

	public FilePath removeDrive() {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.removeDrive();
		
		return new FilePath( path );
	}

	//==============================================================================================

	public FilePath addDirectory( String directory ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.addDirectory( directory );
		
		return new FilePath( path );
	}

	public FilePath addDirectory( int index, String directory ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.addDirectory( index, directory );
		
		return new FilePath( path );
	}

	public FilePath setDirectory( int index, String directory ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.setDirectory( index, directory );
		
		return new FilePath( path );
	}

	public String getDirectory( int index ) {
		return _builder.getDirectory( index );
	}

	public FilePath removeDirectory( int index ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.removeDirectory( index );
		
		return new FilePath( path );
	}

	//==============================================================================================

	public FilePath setName( String name ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.setName( name );
		
		return new FilePath( path );
	}

	public String getName() {
		return _builder.getName();
	}

	public FilePath removeName() {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.removeName();
		
		return new FilePath( path );
	}

	//==============================================================================================

	public FilePath addExtension( String extension ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.addExtension( extension );
		
		return new FilePath( path );
	}

	public FilePath addExtension( int index, String extension ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.addExtension( index, extension );
		
		return new FilePath( path );
	}

	public FilePath setExtension( int index, String extension ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.setExtension( index, extension );
		
		return new FilePath( path );
	}

	//==============================================================================================
	
	/** 
	 * Get the last extension: 
	 */
	public String getExtension() {
		return getExtension( -1 );
	}

	public String getExtension( int index ) {
		return _builder.getExtension( index );
	}

	/** 
	 * Remove the last extension: 
	 */
	public FilePath removeExtension() {
		return removeExtension( -1 );
	}

	public FilePath removeExtension( int index ) {
		FilePathBuilder path = new FilePathBuilder( _builder );
		path.removeExtension( index );
		
		return new FilePath( path );
	}
}
