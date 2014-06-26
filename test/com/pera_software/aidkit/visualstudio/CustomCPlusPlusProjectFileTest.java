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

public class CustomCPlusPlusProjectFileTest extends CPlusPlusProjectFileTest
{
	public static final Path PATH = Resource.getPath( CustomCPlusPlusProjectFileTest.class, "CPlusPlusProjectWithCustomOutputDirectories.vcxproj" );

	//==============================================================================================

	@Override
	public CPlusPlusProjectFile loadProjectFile()
		throws Exception
	{
		return new CPlusPlusProjectFile( PATH );
	}

	//==============================================================================================

	@Override
	public void testFindBuildConfigurationNames()
		throws Exception
	{
		assertBuildConfigurationNames( Arrays.asList( "Debug", "Release", "Test" ));
	}

	//==============================================================================================

	@Override
	public void testFindIntermediateDirectoryNames()
		throws Exception
	{
		assertIntermediateDirectoryNames( Arrays.asList(
			"$(Configuration)\\DebugIntermediateDirectory",
			"$(Configuration)\\ReleaseIntermediateDirectory",
			"$(Configuration)\\TestIntermediateDirectory"
		));
	}

	//==============================================================================================

	@Override
	public void testFindTargetName()
		throws Exception
	{
		assertTargetName( "CPlusPlusWithCustomOutputDirectoriesProjectName" );
	}

	//==============================================================================================

	@Override
	public void testFindOutputDirectoryNames()
		throws Exception
	{
		assertOutputDirectoryNames( Arrays.asList(
			"$(SolutionDir)$(Configuration)\\DebugOutputDirectory",
			"$(SolutionDir)$(Configuration)\\ReleaseOutputDirectory",
			"$(SolutionDir)$(Configuration)\\TestOutputDirectory"
		));
	}

	//==============================================================================================

	@Override
	public void testFindPreBuildCommands()
		throws Exception
	{
		assertPreBuildCommands( Arrays.asList(
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
	public void testFindPostBuildCommands()
		throws Exception
	{
		assertPostBuildCommands( Arrays.asList(
			"xcopy /C /Y /D /S /F \"$(SolutionDir)$(Configuration)\\DebugOutputDirectory\" \"$(SolutionDir)tmp\\$(Configuration)\\\"",
			"xcopy /C /Y /D /S /F \"$(SolutionDir)$(Configuration)\\ReleaseOutputDirectory\" \"$(SolutionDir)tmp\\$(Configuration)\\\""
		));
	}

	//==============================================================================================

	@Override
	public void testFindDeployDirectoryNames()
		throws Exception
	{
		assertDeployDirectoryNames( Arrays.asList(

			// PreBuild deploy directories:

			"$(SolutionDir)\\..\\framework_deploy\\$(Configuration)",
			"$(SolutionDir)\\..\\library1_deploy\\$(Configuration)",
			"$(SolutionDir)\\..\\library2_deploy\\$(Configuration)",

			// PostBuild deploy directories:

			"$(SolutionDir)tmp\\$(Configuration)\\",
			"$(SolutionDir)tmp\\$(Configuration)\\"
		));
	}
}
