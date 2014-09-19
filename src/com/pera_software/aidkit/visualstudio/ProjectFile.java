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

package com.pera_software.aidkit.visualstudio;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.pera_software.aidkit.Console;
import com.pera_software.aidkit.nio.file.Paths;

//##################################################################################################

public class ProjectFile
{
	protected ProjectFileParser _parser;

	//==============================================================================================

	public ProjectFile( ProjectFileParser parser )
		throws Exception
	{
		_parser = parser;
	}

	//==============================================================================================

	public List< Path > findOutputFiles( Path solutionFilePath )
		throws Exception
	{
		List< String > outputFileNames = _parser.findOutputFileNames();
		List< Path > outputFiles = convertOutputDirectoryNames( solutionFilePath, outputFileNames );

		return outputFiles;
	}

	//==============================================================================================

	public OutputDirectory findIntermediateDirectories( Path solutionFilePath )
		throws Exception
	{
		List< String > intermediateDirectoryNames = _parser.findIntermediateDirectoryNames();
		List< Path > intermediateDirectories = convertOutputDirectoryNames( solutionFilePath, intermediateDirectoryNames );

		return new OutputDirectory( "intermediate", intermediateDirectories );
	}

	//==============================================================================================

	public OutputDirectory findOutputDirectories( Path solutionFilePath )
		throws Exception
	{
		List< String > outputDirectoryNames = _parser.findOutputDirectoryNames();
		List< Path > outputDirectories = convertOutputDirectoryNames( solutionFilePath, outputDirectoryNames );

		return new OutputDirectory( "output", outputDirectories );
	}

	//==============================================================================================

	public OutputDirectory findCopyDirectories( Path solutionFilePath )
		throws Exception
	{
		List< String > copyDirectoryNames = _parser.findCopyDirectoryNames();
		List< Path > copyDirectories = convertOutputDirectoryNames( solutionFilePath, copyDirectoryNames );

		copyDirectories.removeIf( copyDirectory -> !Files.isDirectory( copyDirectory ));

		return new OutputDirectory( "copy", copyDirectories );
	}

	//==============================================================================================

	public List< String > findBuildConfigurationNames()
		throws Exception
	{
		List< BuildConfiguration > buildConfigurations = _parser.findBuildConfigurations();

		List< String > buildConfigurationNames = new ArrayList<>();
		buildConfigurations.forEach( configuration -> {
			buildConfigurationNames.add( configuration.name() );
		});
		return buildConfigurationNames;
	}

	//==============================================================================================

	public List< String > findPlatformNames( String buildConfigurationName )
		throws Exception
	{
		List< BuildConfiguration > buildConfigurations = _parser.findBuildConfigurations();

		List< String > platformNames = new ArrayList<>();
		buildConfigurations.forEach( configuration -> {
			if ( configuration.name().equals( buildConfigurationName )) {
				platformNames.add( configuration.platformName() );
			}
		});
		return platformNames;
	}

	//==============================================================================================

	public List< OutputDirectory > collectOutputDirectories( Path solutionFilePath )
		throws Exception
	{
		// Replace the output files with their parent directory:

		List< Path > outputFiles = findOutputFiles( solutionFilePath );
		List< Path > outputFileDirectories = new ArrayList<>();
		for ( Path outputFile : outputFiles ) {
			outputFileDirectories.add( outputFile.getParent() );
		}
		List< OutputDirectory > outputDirectories = new ArrayList<>();
		outputDirectories.add( new OutputDirectory( "files", outputFileDirectories ));
		outputDirectories.add( findIntermediateDirectories( solutionFilePath ));
		outputDirectories.add( findOutputDirectories( solutionFilePath ));
		outputDirectories.add( findCopyDirectories( solutionFilePath ));

		return outputDirectories;
	}


	//==============================================================================================
	/**
	 * Convert the directory names into real directories:
	 * - replace the build variables.
	 * - resolve relative names to the solution directory.
	 */
	private List< Path > convertOutputDirectoryNames( Path solutionFilePath, List< String > projectOutputDirectoryNames )
		throws Exception
	{
		List< Path > allOutputDirectories = new ArrayList<>();
		for ( String outputDirectoryName : projectOutputDirectoryNames ) {
			List< String > outputDirectoryNames = replaceBuildVariables( outputDirectoryName, solutionFilePath );
			List< Path > outputDirectories = Paths.get( outputDirectoryNames );
			outputDirectories = resolveProjectSiblings( outputDirectories );
			allOutputDirectories.addAll( outputDirectories );
		}
		return allOutputDirectories;
	}

	//==============================================================================================

	private List< String > replaceBuildVariables( String pathName, Path solutionFilePath )
		throws Exception
	{
		final String targetName = _parser.findTargetName();
		final List< String > buildConfigurationNames = findBuildConfigurationNames();
		final List< String > intermediateDirectoryNames = _parser.findIntermediateDirectoryNames();
		final String solutionDirectoryName = solutionFilePath.getParent().toString() + File.separatorChar;

		// Replace the build variables with the actual values:

		List< String > pathNames = new ArrayList<>();
		for ( String buildConfigurationName : buildConfigurationNames ) {
			for ( String intermediateDirectoryName : intermediateDirectoryNames ) {
				String replacedPathName = pathName.replace( BuildVariables.SOLUTION_DIR,  solutionDirectoryName );
				replacedPathName = replacedPathName.replace( BuildVariables.INT_DIR,       intermediateDirectoryName );
				replacedPathName = replacedPathName.replace( BuildVariables.CONFIGURATION, buildConfigurationName );
				replacedPathName = replacedPathName.replace( BuildVariables.TARGET_NAME,   targetName );

				// Check for unknown build variables:

				if ( replacedPathName.contains( "$(" )) {
					Console.printError( "Skipping path with unknown build variable: '%s'", replacedPathName );
					continue;
				}
				pathNames.add( replacedPathName );
			}
		}
		return pathNames;
	}

	//==============================================================================================

	private List< Path > resolveProjectSiblings( List< Path > paths )
	{
		List< Path > resolvedPaths = new ArrayList<>( paths.size() );
		for ( Path path : paths ) {
			Path resolvedPath = path().resolveSibling( path );
			resolvedPaths.add( resolvedPath );
		}
		return resolvedPaths;
	}

	//==============================================================================================

	public Path path()
	{
		return _parser.path();
	}
}
