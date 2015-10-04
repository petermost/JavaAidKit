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

import java.util.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;
import com.pera_software.aidkit.io.*;
import static com.pera_software.aidkit.visualstudio.ProjectFileParserAssert.*;

//##################################################################################################

@RunWith( Parameterized.class )
public final class CustomCPlusPlusProjectFileParserTest extends CPlusPlusProjectFileParserTest {

	@Parameters
	public static Iterable< Object[] > loadProjectFiles() throws Exception {
		return Arrays.asList( new Object[][] {
			{ new CPlusPlusProjectFileParser( Resources.getAsPath( CustomCPlusPlusProjectFileParserTest.class,
				"2010/CPlusPlusProjectWithCustomOutputDirectories.vcxproj" )) },
			{ new CPlusPlusProjectFileParser( Resources.getAsPath( CustomCPlusPlusProjectFileParserTest.class,
				"2013/CPlusPlusProjectWithCustomOutputDirectories.vcxproj" )) }
		});
	}

	//==============================================================================================

	public CustomCPlusPlusProjectFileParserTest( ProjectFileParser projectFileParser ) {
		super( projectFileParser );
	}

	//==============================================================================================

	@Override
	public void doTestFindSourceFileNames(ProjectFileParser parser ) throws Exception {
		List< String > expectedSourceFileNames = Arrays.asList(
			"stdafx.h", "targetver.h", // Header
			"CPlusPlusProjectWithDefaults.cpp", "stdafx.cpp" // Source
		);
		assertSourceFileNames( parser, expectedSourceFileNames );
	}

	//==============================================================================================

	@Override
	public void doTestFindBuildConfigurations(ProjectFileParser parser ) throws Exception {
		assertBuildConfigurations( parser, Arrays.asList(
			new BuildConfiguration( "Debug", "Win32" ),
			new BuildConfiguration( "Release", "Win32" ),
			new BuildConfiguration( "Test", "Win32" )
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindIntermediateDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertIntermediateDirectoryNames( parser, Arrays.asList(
			"$(Configuration)\\DebugIntermediateDirectory",
			"$(Configuration)\\ReleaseIntermediateDirectory",
			"$(Configuration)\\TestIntermediateDirectory"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindTargetName(ProjectFileParser parser ) throws Exception {
		assertTargetName( parser, "CPlusPlusWithCustomOutputDirectoriesProjectName" );
	}

	//==============================================================================================

	@Override
	public void doTestFindOutputDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertOutputDirectoryNames( parser, Arrays.asList(
			"$(SolutionDir)$(Configuration)\\DebugOutputDirectory",
			"$(SolutionDir)$(Configuration)\\ReleaseOutputDirectory",
			"$(SolutionDir)$(Configuration)\\TestOutputDirectory"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindPreBuildCommands(ProjectFileParser parser ) throws Exception {
		assertPreBuildCommands( parser, Arrays.asList(
			"@ECHO OFF",
			"ECHO.",
			"ECHO Copy pre-requisites",
			"ECHO.",
			"cmd /c robocopy /NP /NDL /NJS /MIR \"\\\\hostName\\shareName\\framework\\$(Configuration)\" \"$(SolutionDir)\\..\\framework_deploy\\$(Configuration) \" ^& IF %ERRORLEVEL% LEQ 4 exit /B 0",
			"ECHO.",
			"ECHO Copy pre-requisites",
			"ECHO.",
			"cmd /c robocopy /NP /NDL /NJS /MIR \"\\\\hostName\\shareName\\library1_export\\$(Configuration)\" \"$(SolutionDir)\\..\\library1_deploy\\$(Configuration) \" \"*Shared*.*\" ^& IF %ERRORLEVEL% LEQ 4 exit /B 0",
			"ECHO.",
			"ECHO Copy pre-requisites",
			"ECHO.",
			"cmd /c robocopy /NP /NDL /NJS /MIR \"\\\\hostName\\shareName\\library2_export\\$(Configuration)\" \"$(SolutionDir)\\..\\library2_deploy\\$(Configuration) \" ^& IF %ERRORLEVEL% LEQ 4 exit /B 0"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindPostBuildCommands(ProjectFileParser parser ) throws Exception {
		assertPostBuildCommands( parser, Arrays.asList(
			"xcopy /C /Y /D /S /F \"$(SolutionDir)$(Configuration)\\DebugOutputDirectory\" \"$(SolutionDir)tmp\\$(Configuration)\\\"",
			"xcopy /C /Y /D /S /F \"$(SolutionDir)$(Configuration)\\ReleaseOutputDirectory\" \"$(SolutionDir)tmp\\$(Configuration)\\\""
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindDeployDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertDeployDirectoryNames( parser, Arrays.asList(

			// PreBuild deploy directories:

			"$(SolutionDir)\\..\\framework_deploy\\$(Configuration)",
			"$(SolutionDir)\\..\\library1_deploy\\$(Configuration)",
			"$(SolutionDir)\\..\\library2_deploy\\$(Configuration)",

			// PostBuild deploy directories:

			"$(SolutionDir)tmp\\$(Configuration)\\",
			"$(SolutionDir)tmp\\$(Configuration)\\"
		));
	}

	//==============================================================================================
	
	@Override
	public void doTestFindProjectReferenceNames(ProjectFileParser parser ) throws Exception {
		assertProjectReferenceNames( parser, Arrays.asList(
			"Win32Project.vcxproj"			
		));
	}

	//==============================================================================================
	
	@Override
	public void doTestFindLibraryReferenceNames( ProjectFileParser parser ) throws Exception {
		assertLibraryReferenceNames( parser, Arrays.asList() );
	}
	
	//==============================================================================================
	
	@Override
	public void doTestFindTreatWarningsAsErrors(ProjectFileParser parser ) throws Exception {
		assertTreatWarningsAsErrors( parser, Arrays.asList( 
			"true",
			"true",
			"true"
		));
	}
}
