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

package com.pera_software.aidkit.util.jar;

import java.io.*;
import java.net.*;
import java.util.jar.*;
import org.eclipse.jdt.annotation.*;
import com.pera_software.aidkit.lang.*;

//##############################################################################

public class JarManifest extends Manifest {
	private static final String MANIFEST_CLASS_PATH_SEPERATOR = " ";

	private String _path = "";

	//============================================================================

	public static @Nullable JarManifest getCurrentManifest( Class< ? > mainClass ) {
		JarManifest manifest = getJarManifest( mainClass );
		if ( manifest == null )
			manifest = getClassManifest( mainClass );

		return ( manifest );
	}

	//============================================================================

	public static @Nullable JarManifest getJarManifest( Class< ? > mainClass ) {
		// The 'recommended' way of retrieving the manifest with:
		// InputStream inputStream = rootClass.getResourceAsStream( "/META-INF/MANIFEST.MF" );
		// Manifest manifest = new Manifest( inputStream );
		// retrieves the manifest from C:\\Programme\\Java\\jdk1.5.0_07\\jre\\lib\\rt.jar !!!

		try {
			JarManifest manifest = null;

			// Create the path name to the class in the jar:

			String classFileName = mainClass.getSimpleName() + ".class";
			URL classPathURL = mainClass.getResource( classFileName );
			if ( classPathURL != null ) {
				String classPathName = classPathURL.toString();

				// Retrieve the jar name:

				int exclamationMarkPosition = classPathName.indexOf( "!" );
				if ( exclamationMarkPosition >= 0 ) {
					String jarPathName = classPathName.substring( 0, exclamationMarkPosition + 1 );
					String manifestPathName = jarPathName + "/META-INF/MANIFEST.MF";
					URL manifestURL = new URL( manifestPathName );
					manifest = new JarManifest( jarPathName, manifestURL.openStream() );
				}
			}
			return manifest;
		} catch ( IOException cause ) {
			return null;
		}
	}

	//============================================================================

	public static JarManifest getClassManifest( Class< ? > mainClass ) {
		// There is no manifest for classes, so we 'fake' a manifest:

		// Make the class path look like in the manifest:

		String pathSeparator = SystemProperties.getPathSeparator();
		String classPath = SystemProperties.getClassPath();
		classPath = classPath.replace( pathSeparator, MANIFEST_CLASS_PATH_SEPERATOR );

		// Transform the full qualified name of the main class to a path:

		String fileSeparator = SystemProperties.getFileSeparator();
		String mainClassName = mainClass.getName().replace( ".", fileSeparator );
		mainClassName += ".class";

		// Initialize the 'faked' manifest with the class path:

		URL mainClassLocation = mainClass.getProtectionDomain().getCodeSource().getLocation();
		String mainClassPath = mainClassLocation != null ? mainClassLocation.getPath() : "" + mainClassName;

		return new JarManifest( mainClassPath, classPath );
	}

	//============================================================================

	public JarManifest( String path ) {
		_path = path;
	}

	//============================================================================

	public JarManifest( String path, InputStream inputStream ) throws IOException {
		super( inputStream );
		_path = path;
	}

	//============================================================================

	public JarManifest( String path, Manifest manifest ) {
		super( manifest );
		_path = path;
	}

	//============================================================================

	public JarManifest( String path, String classPath ) {
		_path = path;
		getMainAttributes().putValue( Attributes.Name.CLASS_PATH.toString(), classPath );
	}

	//============================================================================

	public String getPath() {
		return _path;
	}

	//============================================================================

	public String[] getClassPath() {
		String classPath = getMainAttributes().getValue( Attributes.Name.CLASS_PATH );

		// Split the path when separated with one or more blanks:

		return ( classPath != null ? classPath.split( MANIFEST_CLASS_PATH_SEPERATOR + "+" ) : new String[ 0 ] );
	}

	//============================================================================

	private String getAttribute( Attributes.Name name ) {
		String value = getMainAttributes().getValue( name );
		return ( value != null ? value : "" );
	}

	//============================================================================

	public String getImplementationTitle() {
		return ( getAttribute( Attributes.Name.IMPLEMENTATION_TITLE ) );
	}

	//============================================================================

	public String getImplementationVendor() {
		return ( getAttribute( Attributes.Name.IMPLEMENTATION_VENDOR ) );
	}

	//============================================================================

	public String getImplementationVersion() {
		return ( getAttribute( Attributes.Name.IMPLEMENTATION_VERSION ) );
	}

}
