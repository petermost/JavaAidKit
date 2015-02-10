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
public final class CustomCSharpProjectFileParserTest extends CSharpProjectFileParserTest {

	@Parameters
	public static Iterable< Object[] > loadProjectFiles() throws Exception {
		return Arrays.asList( new Object[][] {
			{ new CSharpProjectFileParser( Resources.getPath( CustomCSharpProjectFileParserTest.class,
				"2010/CSharpProjectWithCustomOutputDirectories.csproj" )) },
			{ new CSharpProjectFileParser( Resources.getPath( CustomCSharpProjectFileParserTest.class,
				"2013/CSharpProjectWithCustomOutputDirectories.csproj" )) }
		});
	}

	//==============================================================================================

	public CustomCSharpProjectFileParserTest( ProjectFileParser projectFileParser ) {
		super( projectFileParser );
	}

	//==============================================================================================

	@Override
	public void doTestFindSourceFileNames(ProjectFileParser parser ) throws Exception {
		assertSourceFileNames( parser, Arrays.asList(
			"Class1.cs",
			"Class2.cs"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindIntermediateDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertIntermediateDirectoryNames( parser, Arrays.asList(
			"obj"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindTargetName(ProjectFileParser parser ) throws Exception {
		assertTargetName( parser, "CSharpWithCustomOutputDirectoriesProjectName" );
	}

	//==============================================================================================

	@Override
	public void doTestFindBuildConfigurations(ProjectFileParser parser ) throws Exception {
		assertBuildConfigurations( parser, Arrays.asList(
			new BuildConfiguration( "Debug", "x86" ),
			new BuildConfiguration( "Release", "x86" )
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindOutputDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertOutputDirectoryNames( parser, Arrays.asList(
			"deploy\\Debug\\",
			"deploy\\Release\\"
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
			"cmd /c robocopy /NP /NDL /NJS /MIR \"\\\\hostName\\shareName\\framework\\$(Configuration)\" \"$(SolutionDir)\\..\\framework_deploy\\$(Configuration) \" ^& IF %25ERRORLEVEL%25 LEQ 4 exit /B 0",
			"ECHO.",
			"ECHO Copy pre-requisites",
			"ECHO.",
			"cmd /c robocopy /NP /NDL /NJS /MIR \"\\\\hostName\\shareName\\library1_export\\$(Configuration)\" \"$(SolutionDir)\\..\\library1_deploy\\$(Configuration) \" \"*Shared*.*\" ^& IF %25ERRORLEVEL%25 LEQ 4 exit /B 0",
			"ECHO.",
			"ECHO Copy pre-requisites",
			"ECHO.",
			"cmd /c robocopy /NP /NDL /NJS /MIR \"\\\\hostName\\shareName\\library2_export\\$(Configuration)\" \"$(SolutionDir)\\..\\library2_deploy\\$(Configuration) \" ^& IF %25ERRORLEVEL%25 LEQ 4 exit /B 0"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindPostBuildCommands(ProjectFileParser parser ) throws Exception {
		assertPostBuildCommands( parser, Arrays.asList(
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

			"$(SolutionDir)tmp\\$(Configuration)\\"
		));
	}
	
	//==============================================================================================
	
	@Override
	public void doTestFindProjectReferenceNames(ProjectFileParser parser ) throws Exception {
		assertProjectReferenceNames( parser, Arrays.asList(
			"ClassLibrary.csproj"			
		));
	}

	//==============================================================================================
	
	@Override
	public void doTestFindLibraryReferenceNames( ProjectFileParser parser ) throws Exception {
		assertLibraryReferenceNames( parser, Arrays.asList(
			"System",
			"System.Windows.Forms"
		));
	}
	
	//==============================================================================================
	
	@Override
	public void doTestFindTreatWarningsAsErrors( ProjectFileParser parser ) throws Exception {
		assertTreatWarningsAsErrors( parser, Arrays.asList( 
			"true",
			"true"
		));
	}
}
