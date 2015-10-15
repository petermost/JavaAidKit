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
import java.util.*;
import org.junit.*;
import com.pera_software.aidkit.lang.*;

//##################################################################################################

public class FilePathTest {
	//==============================================================================================

	@Test
	public void testEmptyPath() {
		FilePath path = new FilePath();
		assertEquals( Strings.EMPTY, path.drive() );
		assertEquals( Strings.EMPTY, path.directoryAt( 0 ));
		assertEquals( Strings.EMPTY, path.directoryAt( 1 ));
		assertEquals( Strings.EMPTY, path.directoryAt( -1 ));
		assertEquals( Strings.EMPTY, path.name() );
		assertEquals( Strings.EMPTY, path.lastExtension() );
		assertEquals( Strings.EMPTY, path.extensionAt( 0 ));
		assertEquals( Strings.EMPTY, path.extensionAt( 1 ));
		assertEquals( Strings.EMPTY, path.extensionAt( -1 ));
	}

	//==============================================================================================
	
	@Test
	public void testCopyConstructor() {
		FilePath path = new FilePath( "D:/dir1/dir2/name.ext1.ext2" );
		FilePath copy = new FilePath( path );
		
		assertEquals( path.drive(), copy.drive() );
	}
	
	//==============================================================================================

	@Test
	public void testDriveSeparator() {
		FilePath path = new FilePath();

		path = path.replaceDrive( "Z" );
		assertEquals( "Z:", path.drive() );

		path = path.replaceDrive( "Z:" );
		assertEquals( "Z:", path.drive() );

		path = path.replaceDrive( Strings.EMPTY );
		assertEquals( Strings.EMPTY, path.drive() );

		path = path.removeDrive();
		assertEquals( Strings.EMPTY, path.drive() );
	}

	//==============================================================================================

	@Test
	public void testDirectorySeparator() {
		FilePath path = new FilePath();

		path = path.appendDirectory( "dir" );
		assertEquals( "dir/", path.directoryAt( 0 ));

		path = path.replaceDirectory( 0, "dir/" );
		assertEquals( "dir/", path.directoryAt( 0 ));

		path = path.replaceDirectory( 0, Strings.EMPTY );
		assertEquals( Strings.EMPTY, path.directoryAt( 0 ));

		path = path.removeDirectory( 0 );
		assertEquals( Strings.EMPTY, path.directoryAt( 0 ));
	}

	//==============================================================================================

	@Test
	public void testExtensionSeparator() {
		FilePath path = new FilePath();

		path = path.appendExtension( "ext" );
		assertEquals( ".ext", path.extensionAt( 0 ));

		path = path.replaceExtension( 0, ".ext" );
		assertEquals( ".ext", path.extensionAt( 0 ));

		path = path.replaceExtension( 0, Strings.EMPTY );
		assertEquals( Strings.EMPTY, path.extensionAt( 0 ));

		path = path.removeLastExtension();
		assertEquals( Strings.EMPTY, path.extensionAt( 0 ));
	}

	//==============================================================================================

	@Test
	public void testFullPath() {
		FilePath path = new FilePath( "D:/dir1/dir2/name.ext1.ext2" );

		assertEquals( "D:", path.drive() );

		assertEquals( "/", path.directoryAt( 0 ));

		assertEquals( "dir1/", path.directoryAt( 1 ));
		assertEquals( "dir1/", path.directoryAt( -2 ));

		assertEquals( "dir2/", path.directoryAt( 2 ));
		assertEquals( "dir2/", path.directoryAt( -1 ));

		assertEquals( "name", path.name() );

		assertEquals( ".ext1", path.extensionAt( 0 ));
		assertEquals( ".ext1", path.extensionAt( -2 ));

		assertEquals( ".ext2", path.extensionAt( 1 ));
		assertEquals( ".ext2", path.extensionAt( -1 ));

		assertEquals( ".ext2", path.lastExtension() );
	}

	//==============================================================================================

	@Test
	public void testEmptyNameMultipleExtensions() {
		FilePath path = new FilePath( ".ext1.ext2" );

		assertEquals( Strings.EMPTY, path.name() );

		assertEquals( ".ext1", path.extensionAt( 0 ));
		assertEquals( ".ext1", path.extensionAt( -2 ));

		assertEquals( ".ext2", path.lastExtension() );
		assertEquals( ".ext2", path.extensionAt( 1 ));
		assertEquals( ".ext2", path.extensionAt( -1 ));

		assertEquals( Strings.EMPTY, path.extensionAt( 2 ));

		assertEquals( Strings.EMPTY, path.extensionAt( -3 ));
	}

	//==============================================================================================

	@Test
	public void testFileNameWithoutExtension() {
		FilePath path = new FilePath( "D:/dir1/dir2/name" );

		assertEquals( "D:", path.drive() );
		assertEquals( "/", path.directoryAt( 0 ));
		assertEquals( "dir1/", path.directoryAt( 1 ));
		assertEquals( "dir2/", path.directoryAt( 2 ));
		assertEquals( "name", path.name() );
		assertEquals( Strings.EMPTY, path.lastExtension() );
	}

	//==============================================================================================

	@Test
	public void testAddingExtension() {
		FilePath path = new FilePath( "D:/dir1/dir2/name.ext" );

		path = path.replaceExtension( 0, ".second" );
		assertEquals( "D:/dir1/dir2/name.second", path.toString() );

		path = path.insertExtension( 0, ".first" );
		assertEquals( "D:/dir1/dir2/name.first.second", path.toString() );

		path = path.replaceExtension( -1, ".third" );
		assertEquals( "D:/dir1/dir2/name.first.third", path.toString() );
	}
	
	//==============================================================================================
	
	@Test
	public void testToString() {
		FilePath path = new FilePath( "D:/dir1/dir2/name.ext" );

		String string = path.toString( EnumSet.of( FilePath.ToStringOptions.Directories, FilePath.ToStringOptions.Name ));
		assertEquals( "/dir1/dir2/name", string );
	}
}
