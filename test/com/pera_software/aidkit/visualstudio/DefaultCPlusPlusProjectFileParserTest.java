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
public final class DefaultCPlusPlusProjectFileParserTest extends CPlusPlusProjectFileParserTest {

	@Parameters
	public static Iterable< Object[] > loadProjectFiles() throws Exception {
		return Arrays.asList( new Object[][] {
			{ new CPlusPlusProjectFileParser( Resources.asPath( DefaultCPlusPlusProjectFileParserTest.class,
				"2010/CPlusPlusProjectWithDefaultOutputDirectories.vcxproj" )) },
			{ new CPlusPlusProjectFileParser( Resources.asPath( DefaultCPlusPlusProjectFileParserTest.class,
				"2013/CPlusPlusProjectWithDefaultOutputDirectories.vcxproj" )) }
		});
	}

	//==============================================================================================

	public DefaultCPlusPlusProjectFileParserTest( ProjectFileParser projectFileParser ) {
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
			new BuildConfiguration( "Release", "Win32" )
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindIntermediateDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertIntermediateDirectoryNames( parser, Arrays.asList(
			"$(Configuration)\\"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindTargetName(ProjectFileParser parser ) throws Exception {
		assertTargetName( parser, "CPlusPlusProjectWithDefaultOutputDirectories" );
	}

	//==============================================================================================

	@Override
	public void doTestFindOutputDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertOutputDirectoryNames( parser, Arrays.asList(
			"$(SolutionDir)..\\deploy\\$(Configuration)\\lib\\"
		));
	}

	//==============================================================================================

	@Override
	public void doTestFindPreBuildCommands(ProjectFileParser parser ) throws Exception {
		assertPostBuildCommands( parser, Arrays.asList() );
	}

	//==============================================================================================

	@Override
	public void doTestFindPostBuildCommands(ProjectFileParser parser ) throws Exception {
		assertPostBuildCommands( parser, Arrays.asList() );
	}

	//==============================================================================================

	@Override
	public void doTestFindDeployDirectoryNames(ProjectFileParser parser ) throws Exception {
		assertDeployDirectoryNames( parser, Arrays.asList() );
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
		assertTreatWarningsAsErrors( parser, Arrays.asList() ); 
	}
}
