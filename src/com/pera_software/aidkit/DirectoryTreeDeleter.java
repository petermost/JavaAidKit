package com.pera_software.aidkit;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import com.pera_software.aidkit.signal.*;

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
		deleteFile( file );

		return FileVisitResult.CONTINUE;
	}

	//==============================================================================================

	@Override
	@SuppressWarnings("unused")
	public FileVisitResult postVisitDirectory( Path directory, IOException exception )
		throws IOException
	{
		deleteFile( directory );

		return FileVisitResult.CONTINUE;
	}

	//==============================================================================================

	@Override
	@SuppressWarnings("unused")
	public FileVisitResult visitFileFailed( Path file, IOException exception )
		throws IOException
	{
		fileDeletionFailedSignal.emit( file, exception );

		return FileVisitResult.CONTINUE;
	}
}
