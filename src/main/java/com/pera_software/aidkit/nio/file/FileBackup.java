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
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.nio.file;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.zip.*;
import com.pera_software.aidkit.io.*;
import com.pera_software.aidkit.signal.*;

//##################################################################################################

public class FileBackup extends SimpleFileVisitor< Path > implements Closeable {
	
	public final Signal1< String > storingFileSignal = new Signal1< >();
	public final Signal2< String, Exception > storingFileFailedSignal = new Signal2< >();

	private ZipOutputStream _zipStream;
	private PathMatcher _pathMatcher;
	private String _fileName;

	//==============================================================================================

	public FileBackup( String backupFileName ) throws FileNotFoundException {
		_fileName = backupFileName;
		_zipStream = new ZipOutputStream( new FileOutputStream( _fileName ) );
		_pathMatcher = createPathMatcher( "*" );
	}

	//==============================================================================================

	public void backup( String wildCardPath ) throws Exception {
		try {
			if ( wildCardPath.contains( "*" ) || wildCardPath.contains( "?" ) ) {

				// Extract the path so we know where to start and get the name which contains the
				// wildcard so we know what to store:

				File wildCardFile = new File( wildCardPath );
				String parentDirectory = wildCardFile.getParent();
				String wildCardPattern = wildCardFile.getName();
				_pathMatcher = createPathMatcher( wildCardPattern );
				Files.walkFileTree( Paths.get( parentDirectory ), this );
			} else
				storeFile( new File( wildCardPath ) );
		} catch ( Exception exception ) {
			storingFileFailedSignal.emit( wildCardPath, exception );
		}
	}

	private static PathMatcher createPathMatcher( String wildCardPattern ) {
		return FileSystems.getDefault().getPathMatcher( "glob:" + wildCardPattern );
	}

	//==============================================================================================

	@Override
	public void close() throws IOException {
		_zipStream.close();
	}

	//==============================================================================================

	@Override
	public FileVisitResult visitFile( Path file, BasicFileAttributes attrs ) throws IOException {
		if ( _pathMatcher.matches( file.getFileName() ) )
			storeFile( file.toFile() );

		return super.visitFile( file, attrs );
	}

	//==============================================================================================

	private void storeFile( File file ) throws IOException {
		try {
			String fileName = file.getCanonicalPath();
			storingFileSignal.emit( fileName );
			try ( FileInputStream inputStream = new FileInputStream( file ) ) {
				_zipStream.putNextEntry( new ZipEntry( fileName ) );
				Streams.copy( inputStream, _zipStream );
				_zipStream.closeEntry();
			}
		} catch ( Exception exception ) {
			throw new IOException( exception );
		}
	}
}
