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

import static org.junit.Assert.*;
import org.junit.*;

//##################################################################################################

public class FilePathTest
{
	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testEmptyPath()
	{
		FilePath path = new FilePath( "" );
		assertEquals( "", path.getDrive() );
		assertEquals( "", path.getDirectory( 0 ) );
		assertEquals( "", path.getDirectory( 1 ) );
		assertEquals( "", path.getDirectory( -1 ) );
		assertEquals( "", path.getName() );
		assertEquals( "", path.getExtension() );
		assertEquals( "", path.getExtension( 0 ) );
		assertEquals( "", path.getExtension( 1 ) );
		assertEquals( "", path.getExtension( -1 ) );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testDriveSeparator()
	{
		FilePath path = new FilePath( "" );

		path.setDrive( "Z" );
		assertEquals( "Z:", path.getDrive() );

		path.setDrive( "Z:" );
		assertEquals( "Z:", path.getDrive() );

		path.setDrive( "" );
		assertEquals( "", path.getDrive() );

		path.removeDrive();
		assertEquals( "", path.getDrive() );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testDirectorySeparator()
	{
		FilePath path = new FilePath( "" );

		path.addDirectory( "dir" );
		assertEquals( "dir/", path.getDirectory( 0 ) );

		path.setDirectory( 0, "dir/" );
		assertEquals( "dir/", path.getDirectory( 0 ) );

		path.setDirectory( 0, "" );
		assertEquals( "", path.getDirectory( 0 ) );

		path.removeDirectory( 0 );
		assertEquals( "", path.getDirectory( 0 ) );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testExtensionSeparator()
	{
		FilePath path = new FilePath( "" );

		path.addExtension( "ext" );
		assertEquals( ".ext", path.getExtension( 0 ) );

		path.setExtension( 0, ".ext" );
		assertEquals( ".ext", path.getExtension( 0 ) );

		path.setExtension( 0, "" );
		assertEquals( "", path.getExtension( 0 ) );

		path.removeExtension();
		assertEquals( "", path.getExtension( 0 ) );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testFullPath()
	{
		FilePath path = new FilePath( "D:/dir1/dir2/name.ext1.ext2" );

		assertEquals( "D:", path.getDrive() );

		assertEquals( "/", path.getDirectory( 0 ) );

		assertEquals( "dir1/", path.getDirectory( 1 ) );
		assertEquals( "dir1/", path.getDirectory( -2 ) );

		assertEquals( "dir2/", path.getDirectory( 2 ) );
		assertEquals( "dir2/", path.getDirectory( -1 ) );

		assertEquals( "name", path.getName() );

		assertEquals( ".ext1", path.getExtension( 0 ) );
		assertEquals( ".ext1", path.getExtension( -2 ) );

		assertEquals( ".ext2", path.getExtension( 1 ) );
		assertEquals( ".ext2", path.getExtension( -1 ) );

		assertEquals( ".ext2", path.getExtension() );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testEmptyNameMultipleExtensions()
	{
		FilePath path = new FilePath( ".ext1.ext2" );

		assertEquals( "", path.getName() );

		assertEquals( ".ext1", path.getExtension( 0 ) );
		assertEquals( ".ext1", path.getExtension( -2 ) );

		assertEquals( ".ext2", path.getExtension() );
		assertEquals( ".ext2", path.getExtension( 1 ) );
		assertEquals( ".ext2", path.getExtension( -1 ) );

		assertEquals( "", path.getExtension( 2 ) );

		assertEquals( "", path.getExtension( -3 ) );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testFileNameWithoutExtension()
	{
		FilePath path = new FilePath( "D:/dir1/dir2/name" );

		assertEquals( "D:", path.getDrive() );
		assertEquals( "/", path.getDirectory( 0 ) );
		assertEquals( "dir1/", path.getDirectory( 1 ) );
		assertEquals( "dir2/", path.getDirectory( 2 ) );
		assertEquals( "name", path.getName() );
		assertEquals( "", path.getExtension() );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings( "static-method" )
	public void testAddingExtension()
	{
		FilePath path = new FilePath( "D:/dir1/dir2/name.ext" );

		path.setExtension( 0, ".second" );
		assertEquals( "D:/dir1/dir2/name.second", path.toString() );

		path.addExtension( 0, ".first" );
		assertEquals( "D:/dir1/dir2/name.first.second", path.toString() );

		path.setExtension( -1, ".third" );
		assertEquals( "D:/dir1/dir2/name.first.third", path.toString() );
	}
}
