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

import java.nio.file.*;
import java.util.*;

//##################################################################################################

public class CPlusPlusProjectFile extends ProjectFile
{
	public static final String EXTENSION = ".vcxproj";

	//==============================================================================================

	public CPlusPlusProjectFile( Path projectFilePath )
		throws Exception
	{
		super( projectFilePath );
	}

	//==============================================================================================

	@Override
	public List< String > findBuildConfigurationNames()
		throws Exception
	{
		List< String > buildConfigurationNames = new ArrayList<>();

//		buildConfigurationNames.addAll( _parser.findXmlTags( "/Project/ItemGroup/ProjectConfiguration/Configuration" ));
		buildConfigurationNames.addAll( _parser.findXmlTags( "//PropertyGroup/@Condition" ));

		return buildConfigurationNames;
	}

	//==============================================================================================

	@Override
	public List< String > findPlatformNames( String buildConfiguration )
		throws Exception
	{
		List< String > platformNames = new ArrayList<>();

		String nodeName = String.format( "/Project/ItemGroup/ProjectConfiguration/Configuration[text()=\"%s\"]/../Platform", buildConfiguration );
		platformNames.addAll( _parser.findXmlTags( nodeName ));

		return platformNames;
	}

	//==============================================================================================

	@Override
	public String findTargetName()
		throws Exception
	{
		return findProjectName();
	}

	//==============================================================================================

	public String findProjectName()
		throws Exception
	{
		List< String > projectNames = _parser.findXmlTags( "//ProjectName" );
		if ( !projectNames.isEmpty() )
			return projectNames.get( 0 );
		else {
			String projectFileName = path().getFileName().toString();
			int extensionIndex = projectFileName.toLowerCase().lastIndexOf( EXTENSION.toLowerCase() );
			if ( extensionIndex != -1 )
				return projectFileName.substring( 0, extensionIndex );
			else
				return projectFileName;
		}
	}

	//==============================================================================================

	@Override
	protected List< String > findOutputDirectoryNames()
		throws Exception
	{
		List< String > outputDirectoryNames = new ArrayList<>();

		// Add the 'Output Directory' names:

		List< String > outDirs = _parser.findXmlTags( "//OutDir" );
		if ( !outDirs.isEmpty() )
			outputDirectoryNames.addAll( outDirs );
		else
			outputDirectoryNames.add( BuildVariables.SOLUTION_DIR + "..\\deploy\\" + BuildVariables.CONFIGURATION + "\\lib\\" );

		return outputDirectoryNames;
	}

	//==============================================================================================

	@Override
	protected List< String > findOutputFileNames()
		throws Exception
	{
		List< String > outputFileNames = new ArrayList<>();

		// Add the 'Precompiled Header Output File' names:

		List< String > precompiledHeaderOutputFiles = _parser.findXmlTags( "//PrecompiledHeaderOutputFile" );
		if ( !precompiledHeaderOutputFiles.isEmpty() )
			outputFileNames.addAll( precompiledHeaderOutputFiles );
		else
			outputFileNames.add( BuildVariables.INT_DIR + BuildVariables.TARGET_NAME + ".pch" );

		return outputFileNames;
	}

	//==============================================================================================

	@Override
	protected List< String > findIntermediateDirectoryNames()
		throws Exception
	{
		// Add the 'Intermediate Directory' names:

		List< String > intermediateDirectories = _parser.findXmlTags( "//IntDir" );
		if ( !intermediateDirectories.isEmpty() )
			return intermediateDirectories;
		else
			return Arrays.asList( BuildVariables.CONFIGURATION + "\\" );
	}

	//==============================================================================================

	@Override
	protected List<String> findPreBuildCommands()
		throws Exception
	{
		return _parser.findXmlLines( "//PreBuildEvent/Command" );
	}

	//==============================================================================================

	@Override
	protected List<String> findPostBuildCommands()
		throws Exception
	{
		return _parser.findXmlLines( "//PostBuildEvent/Command" );
	}
}
