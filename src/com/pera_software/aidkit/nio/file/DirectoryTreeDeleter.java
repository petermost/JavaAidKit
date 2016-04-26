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

package com.pera_software.aidkit.nio.file;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import com.pera_software.aidkit.signal.*;

//##################################################################################################

public class DirectoryTreeDeleter extends SimpleFileVisitor< Path >
{
	public enum DeletionMode
	{
		Real,
		Simulation
	}

	public final Signal1< Path > fileDeletedSignal = new Signal1<>();
	public final Signal2< Path, Exception > fileDeletionFailedSignal = new Signal2<>();

	private DeletionMode _deletionMode;

	//==============================================================================================

	public DirectoryTreeDeleter( DeletionMode deletionMode )
	{
		_deletionMode = deletionMode;
	}

	//==============================================================================================

	private void deleteFile( Path file )
		throws Exception
	{
		try {
			if ( _deletionMode == DeletionMode.Real )
				Files.delete( file );

			fileDeletedSignal.emit( file );
		} catch ( DirectoryNotEmptyException exception ) {
			// Can happen if visitFile() determined that a file shouldn't/couldn't be deleted and
			// then postVisitDirectory() tries to delete the directory in which this file resides.

			fileDeletionFailedSignal.emit( file, exception );
		} catch ( Exception exception ) {
			// Unfortunately getMessage() only returns the complete file path for the file that failed
			// so it doesn't help us to print it:

			fileDeletionFailedSignal.emit( file, exception );
		}
	}

	//==============================================================================================

	@Override
	@SuppressWarnings("unused")
	public FileVisitResult visitFile( Path file, BasicFileAttributes attributes )
		throws IOException
	{
		try {
			deleteFile( file );

			return FileVisitResult.CONTINUE;
		} catch ( Exception cause ) {
			throw new IOException( cause );
		}
	}

	//==============================================================================================

	@Override
	@SuppressWarnings("unused")
	public FileVisitResult postVisitDirectory( Path directory, IOException exception )
		throws IOException
	{
		try {
			deleteFile( directory );

			return FileVisitResult.CONTINUE;
		} catch ( Exception cause ) {
			throw new IOException( cause );
		}
	}

	//==============================================================================================

	@Override
	public FileVisitResult visitFileFailed( Path file, IOException exception )
		throws IOException
	{
		try {
			fileDeletionFailedSignal.emit( file, exception );

			return FileVisitResult.CONTINUE;
		} catch ( Exception cause ) {
			throw new IOException( cause );
		}
	}
}
