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

public class CPlusPlusProjectFileParser extends ProjectFileParser {
	public static final String EXTENSION = ".vcxproj";

	//==============================================================================================

	public CPlusPlusProjectFileParser( Path projectFilePath ) throws Exception {
		super( projectFilePath );
	}

	//==============================================================================================

	@Override
	public String findTargetName() throws Exception {
		return findProjectName();
	}

	//==============================================================================================

	public String findProjectName() throws Exception {
		List< String > projectNames = findXmlTags( "//ProjectName" );
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
	public List< String > findSourceFileNames() throws Exception {
		List< String > sourceFileNames = new ArrayList<>();

		sourceFileNames.addAll( findXmlTags( "//ClInclude/@Include" ));
		sourceFileNames.addAll( findXmlTags( "//ClCompile/@Include" ));

		return sourceFileNames;
	}

	//==============================================================================================

	@Override
	public List< String > findOutputDirectoryNames() throws Exception {
		List< String > outputDirectoryNames = new ArrayList<>();

		// Add the 'Output Directory' names:

		List< String > outDirs = findXmlTags( "//OutDir" );
		if ( !outDirs.isEmpty() )
			outputDirectoryNames.addAll( outDirs );
		else
			outputDirectoryNames.add( BuildVariables.SOLUTION_DIR + "..\\deploy\\" + BuildVariables.CONFIGURATION + "\\lib\\" );

		return outputDirectoryNames;
	}

	//==============================================================================================

	@Override
	public List< String > findOutputFileNames() throws Exception {
		List< String > outputFileNames = new ArrayList<>();

		// Add the 'Precompiled Header Output File' names:

		List< String > precompiledHeaderOutputFiles = findXmlTags( "//PrecompiledHeaderOutputFile" );
		if ( !precompiledHeaderOutputFiles.isEmpty() )
			outputFileNames.addAll( precompiledHeaderOutputFiles );
		else
			outputFileNames.add( BuildVariables.INT_DIR + BuildVariables.TARGET_NAME + ".pch" );

		return outputFileNames;
	}

	//==============================================================================================

	@Override
	public List< String > findIntermediateDirectoryNames() throws Exception {
		// Add the 'Intermediate Directory' names:

		List< String > intermediateDirectories = findXmlTags( "//IntDir" );
		if ( !intermediateDirectories.isEmpty() )
			return intermediateDirectories;
		else
			return Arrays.asList( BuildVariables.CONFIGURATION + "\\" );
	}

	//==============================================================================================

	@Override
	public List< String > findPreBuildCommands() throws Exception {
		return findXmlLines( "//PreBuildEvent/Command" );
	}

	//==============================================================================================

	@Override
	public List< String > findPostBuildCommands() throws Exception {
		return findXmlLines( "//PostBuildEvent/Command" );
	}

	//==============================================================================================
	
	@Override
	public List< String > findTreatWarningsAsErrorsValues() throws Exception {
		return findXmlTags( "//TreatWarningAsError" );
	}
}
