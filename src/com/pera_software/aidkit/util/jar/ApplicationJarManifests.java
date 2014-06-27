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
import java.util.*;
import java.util.jar.*;

//##############################################################################

public class ApplicationJarManifests implements Iterable< ApplicationJarManifests.Entry >
{
	//############################################################################

	static public class Entry
	{
		private JarManifest _manifest;
		private Exception _exception;

		//==========================================================================

		public Entry( JarManifest manifest, Exception exception )
		{
			_manifest = manifest;
			_exception = exception;
		}

		//==========================================================================

		public JarManifest manifest()
		{
			return ( _manifest );
		}

		//==========================================================================

		public Exception exception()
		{
			return ( _exception );
		}

		//==========================================================================

		public boolean isErroneous()
		{
			return ( _exception != null );
		}
	}

	private String _requiredVersion = "";
	private String _requiredVendor = "";
	private List< Entry > _loadedManifests = new Vector<>();

	//============================================================================

	public ApplicationJarManifests( JarManifest mainManifest )
	{
		// The main manifest specifies which vendor and version is required:

		_requiredVersion = mainManifest.getImplementationVersion();
		_requiredVendor = mainManifest.getImplementationVendor();

		// Add it to the loaded manifests so it shows up first in the table:

		_loadedManifests.add( new Entry( mainManifest, null ) );

		Set< String > loadedJars = new TreeSet<>();
		loadManifests( mainManifest, loadedJars );
	}

	//============================================================================

	private void loadManifests( JarManifest manifest, Set< String > loadedJars )
	{
		String classPaths[] = manifest.getClassPath();
		for ( String classPath : classPaths ) {
			// Avoid endless recursion if we have already seen it:

			if ( !loadedJars.contains( classPath ) ) {
				loadedJars.add( classPath );
				try {
					File classPathFile = new File( classPath );
					if ( classPathFile.exists() ) {
						// We can only iterate of jar files:

						if ( classPathFile.isFile() ) {
							try ( JarFile jarFile = new JarFile( classPath ) ) {
								Manifest childManifest = jarFile.getManifest();
								if ( childManifest != null ) {
									Exception exception = null;
									JarManifest jarManifest = new JarManifest( classPath, childManifest );

									// Check the version but only if it is from the same vendor:

									if ( _requiredVendor.length() > 0 && _requiredVendor.equals( jarManifest.getImplementationVendor() )
										&& !_requiredVersion.equals( jarManifest.getImplementationVersion() ) ) {
										exception = new JarException( "Wrong version!" );
									}
									_loadedManifests.add( new Entry( jarManifest, exception ) );
									loadManifests( jarManifest, loadedJars );
								} else
									throw ( new JarException( "Manifest not found!" ) );
							}
						} else
							_loadedManifests.add( new Entry(
								new JarManifest( classPath, classPath ), null ) );
					} else
						throw ( new FileNotFoundException( "JAR not found!" ) );
				} catch ( Exception cause ) {
					_loadedManifests.add( new Entry( new JarManifest( classPath ), cause ) );
				}
			}
		}
	}

	//============================================================================

	@Override
	public Iterator< Entry > iterator()
	{
		return ( _loadedManifests.iterator() );
	}

	//============================================================================

	public boolean isErroneous()
	{
		for ( Entry entry : this ) {
			if ( entry.isErroneous() )
				return ( true );
		}
		return false;
	}

}
