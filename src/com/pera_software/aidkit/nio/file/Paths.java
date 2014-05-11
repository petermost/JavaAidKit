package com.pera_software.aidkit.nio.file;

import java.nio.file.*;
import java.util.*;

//##################################################################################################

public final class Paths
{
	//==============================================================================================

	private Paths()
	{
	}

	//==============================================================================================

	public static List< Path > get( List< String > pathNames )
	{
		List< Path > paths = new ArrayList<>( pathNames.size() );
		for ( String pathName : pathNames ) {
			paths.add( java.nio.file.Paths.get( pathName ));
		}
		return paths;
	}

	//==============================================================================================

	public static List< Path > normalize( List< Path > paths )
	{
		List< Path > normalizedPaths = new ArrayList<>( paths.size() );
		for ( Path path : paths ) {
			normalizedPaths.add( path.normalize() );
		}
		return normalizedPaths;
	}

    //==============================================================================================

	public static List< Path > removeOverlaps( List< Path > outputPaths )
	{
		// Walk through all paths and nullify those which have a parent path:

		List< Path > paths = new ArrayList<>( outputPaths );
		for ( int path1Idx = 0; path1Idx < paths.size(); ++path1Idx ) {
			Path path1 = paths.get( path1Idx );
			for ( int path2Idx = path1Idx + 1; path2Idx < paths.size(); ++path2Idx ) {
				Path path2 = paths.get( path2Idx );
				if ( path1 != null && path2 != null ) {
					if ( path1.startsWith( path2 ))
						paths.set( path1Idx, null );
					else if ( path2.startsWith( path1 ))
						paths.set( path2Idx,  null );
				}
			}
		}
		// Copy the remaining paths:

		List< Path > nonOverlappingPaths = new ArrayList<>();
		for ( Path path : paths ) {
			if ( path != null )
				nonOverlappingPaths.add( path );
		}
		return nonOverlappingPaths;
	}

	//==============================================================================================

	public static List< Path > removeDuplicates( List< Path > outputDirectories )
	{
		List< Path > uniqueOutputDirectories = new ArrayList<>();

		for ( Path outputDirectory : outputDirectories ) {
			if ( !uniqueOutputDirectories.contains( outputDirectory ))
				uniqueOutputDirectories.add( outputDirectory );
		}
		return uniqueOutputDirectories;
	}
}
