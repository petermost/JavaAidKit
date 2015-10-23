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

import java.nio.file.*;
import java.util.*;
import org.eclipse.jdt.annotation.*;

//##################################################################################################

public final class Paths {
	
	//==============================================================================================

	private Paths() {
	}

	//==============================================================================================

	/**
	 * Simply forward this call so if this class was imported there is no conflict with java.nio.file.Paths
	 */
	public static Path get( String first, String ... more ) {
		return java.nio.file.Paths.get( first, more );
	}

	//==============================================================================================

	/**
	 * Get a list of Paths for a list of strings
	 */
	public static List< Path > get( List< String > pathNames ) {
		List< Path > paths = new ArrayList< >( pathNames.size() );
		for ( String pathName : pathNames ) {
			paths.add( java.nio.file.Paths.get( pathName ) );
		}
		return paths;
	}

	//==============================================================================================

	/**
	 * Normalize a list of Paths
	 */
	public static List< Path > normalize( List< Path > paths ) {
		List< Path > normalizedPaths = new ArrayList< >( paths.size() );
		for ( Path path : paths ) {
			normalizedPaths.add( path.normalize() );
		}
		return normalizedPaths;
	}

	//==============================================================================================

	public static List< Path > removeOverlaps( List< Path > paths ) {
		// Walk through all paths and nullify those which have a parent path:

		List< @Nullable Path > pathsCopy = new ArrayList< >( paths );
		for ( int path1Idx = 0; path1Idx < pathsCopy.size(); ++path1Idx ) {
			Path path1 = pathsCopy.get( path1Idx );
			for ( int path2Idx = path1Idx + 1; path2Idx < pathsCopy.size(); ++path2Idx ) {
				Path path2 = pathsCopy.get( path2Idx );
				if ( path1 != null && path2 != null ) {
					if ( path1.startsWith( path2 ) )
						pathsCopy.set( path1Idx, null );
					else if ( path2.startsWith( path1 ) )
						pathsCopy.set( path2Idx, null );
				}
			}
		}
		// Copy the remaining paths:

		List< Path > nonOverlappingPaths = new ArrayList< >();
		for ( Path path : pathsCopy ) {
			if ( path != null )
				nonOverlappingPaths.add( path );
		}
		return nonOverlappingPaths;
	}
}
