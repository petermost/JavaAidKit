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

public abstract class ProjectFile
{
	private Path _projectFilePath;
	protected ProjectFileParser _parser;

	public abstract List< String > findPlatformNames( String buildConfiguration )
		throws Exception;

	//==============================================================================================

	protected abstract List< String > findOutputFileNames()
		throws Exception;

	public List< Path > findOutputFiles( Path solutionFilePath )
		throws Exception
	{
		List< String > outputFileNames = findOutputFileNames();
		List< Path > outputFiles = convertOutputDirectoryNames( solutionFilePath, outputFileNames );

		return outputFiles;
	}

	//==============================================================================================

	protected abstract List< String > findIntermediateDirectoryNames()
		throws Exception;

	public List< Path > findIntermediateDirectories( Path solutionFilePath )
		throws Exception
	{
		List< String > intermediateDirectoryNames = findIntermediateDirectoryNames();
		List< Path > intermediateDirectories = convertOutputDirectoryNames( solutionFilePath, intermediateDirectoryNames );

		return intermediateDirectories;
	}

	//==============================================================================================

	protected abstract List< String > findOutputDirectoryNames()
		throws Exception;

	public List< Path > findOutputDirectories( Path solutionFilePath )
		throws Exception
	{
		List< String > outputDirectoryNames = findOutputDirectoryNames();
		List< Path > outputDirectories = convertOutputDirectoryNames( solutionFilePath, outputDirectoryNames );

		return outputDirectories;
	}

	//==============================================================================================

	protected abstract String findTargetName()
		throws Exception;

	//==============================================================================================

	protected abstract List< String > findPreBuildCommands()
		throws Exception;

	protected abstract List< String > findPostBuildCommands()
		throws Exception;

	public List< String > findDeployDirectoryNames()
		throws Exception
	{
		List< String > prePostBuildCommands = new ArrayList<>();

		prePostBuildCommands.addAll( findPreBuildCommands() );
		prePostBuildCommands.addAll( findPostBuildCommands() );

		return CopyCommandParser.findDestinationDirectoryNames( prePostBuildCommands );
	}

	public List< Path > findDeployDirectories( Path solutionFilePath )
		throws Exception
	{
		List< String > deployDirectoryNames = findDeployDirectoryNames();
		List< Path > deployDirectories = convertOutputDirectoryNames( solutionFilePath, deployDirectoryNames );

		deployDirectories.removeIf( deployDirectory -> !Files.isDirectory( deployDirectory ));

		return deployDirectories;
	}

	//==============================================================================================

	public ProjectFile( Path projectFilePath )
		throws Exception
	{
		_projectFilePath = projectFilePath;
		_parser = new ProjectFileParser( projectFilePath );
	}

	//==============================================================================================

	public abstract List< String > findBuildConfigurationNames()
		throws Exception;

	//==============================================================================================

	public List< Path > findAllOutputDirectories( Path solutionFilePath )
		throws Exception
	{
		List< Path > outputDirectories = new ArrayList<>();

		// Replace the output files with their parent directory:

		outputDirectories.addAll( findOutputFiles( solutionFilePath ));
		for ( int i = 0; i < outputDirectories.size(); ++i ) {
			outputDirectories.set( i, outputDirectories.get( i ).getParent() );
		}

		outputDirectories.addAll( findIntermediateDirectories( solutionFilePath ));
		outputDirectories.addAll( findOutputDirectories( solutionFilePath ));
		outputDirectories.addAll( findDeployDirectories( solutionFilePath ));

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
		final String targetName = findTargetName();
		final List< String > buildConfigurationNames = findBuildConfigurationNames();
		final List< String > intermediateDirectoryNames = findIntermediateDirectoryNames();
		final String solutionDirectoryName = solutionFilePath.getParent().toString() + File.separatorChar;

		// Replace the build variables with the actual values:

		List< String > pathNames = new ArrayList<>();
		for ( String buildConfigurationName : buildConfigurationNames ) {
			for ( String intermediateDirectoryName : intermediateDirectoryNames ) {
				pathName = pathName.replace( BuildVariables.SOLUTION_DIR,  solutionDirectoryName );
				pathName = pathName.replace( BuildVariables.INT_DIR,       intermediateDirectoryName );
				pathName = pathName.replace( BuildVariables.CONFIGURATION, buildConfigurationName );
				pathName = pathName.replace( BuildVariables.TARGET_NAME,   targetName );

				// Check for unknown build variables:

				if ( pathName.contains( "$(" )) {
					Console.printError( "Skipping path with unknown build variable: '%s'", pathName );
					continue;
				}
				pathNames.add( pathName );
			}
		}
		return pathNames;
	}

	//==============================================================================================

	private List< Path > resolveProjectSiblings( List< Path > paths )
	{
		List< Path > resolvedPaths = new ArrayList<>( paths.size() );
		for ( Path path : paths ) {
			Path resolvedPath = _projectFilePath.resolveSibling( path );
			resolvedPaths.add( resolvedPath );
		}
		return resolvedPaths;
	}

	//==============================================================================================

	public Path path()
	{
		return _projectFilePath;
	}

}
