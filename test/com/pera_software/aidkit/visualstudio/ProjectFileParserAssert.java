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
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.visualstudio;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.*;

public final class ProjectFileParserAssert {

	private ProjectFileParserAssert() {
	}
	//==============================================================================================

	public static void assertSourceFileNames( ProjectFileParser parser , List< String > expectedSourceFileNames  ) throws Exception {
		List< String > actualSourceFileNames = parser.findSourceFileNames();
		assertThat( actualSourceFileNames, is( expectedSourceFileNames ) );
	}

	//==============================================================================================

	public static void assertBuildConfigurations( ProjectFileParser parser , List< BuildConfiguration > expectedBuildConfigurations  ) throws Exception {
		List< BuildConfiguration > actualBuildConfigurations = parser.findBuildConfigurations();
		assertThat( actualBuildConfigurations, is( expectedBuildConfigurations ) );
	}

	//==============================================================================================

	public static void assertIntermediateDirectoryNames( ProjectFileParser parser , List< String > expectedIntermediateDirectoryNames  ) throws Exception {
		List< String > actualIntermediateDirectoryNames = parser.findIntermediateDirectoryNames();
		assertThat( actualIntermediateDirectoryNames, is( expectedIntermediateDirectoryNames ) );
	}

	//==============================================================================================

	public static void assertTargetName( ProjectFileParser parser , String expectedTargetName  ) throws Exception {
		String actualTargetName = parser.findTargetName();
		assertThat( actualTargetName, is( expectedTargetName ) );
	}

	//==============================================================================================

	public static void assertOutputDirectoryNames( ProjectFileParser parser , List< String > expectedOutputDirectoryNames  ) throws Exception {
		List< String > actualOutputDirectoryNames = parser.findOutputDirectoryNames();
		assertThat( actualOutputDirectoryNames, is( expectedOutputDirectoryNames ) );
	}

	//==============================================================================================

	public static void assertPreBuildCommands( ProjectFileParser parser , List< String > expectedBuildCommands  ) throws Exception {
		List< String > actualBuildEvents = parser.findPreBuildCommands();
		assertThat( actualBuildEvents, is( expectedBuildCommands ) );
	}

	//==============================================================================================

	public static void assertPostBuildCommands( ProjectFileParser parser , List< String > expectedBuildCommands  ) throws Exception {
		List< String > actualBuildEvents = parser.findPostBuildCommands();
		assertThat( actualBuildEvents, is( expectedBuildCommands ) );
	}

	//==============================================================================================

	public static void assertDeployDirectoryNames( ProjectFileParser parser , List< String > expectedDeployDirectories  ) throws Exception {
		List< String > actualDeployDirectories = parser.findCopyDirectoryNames();
		assertThat( actualDeployDirectories, is( expectedDeployDirectories ) );
	}
	
	//==============================================================================================
	
	public static void assertReferencedProjectNames( ProjectFileParser parser , List< String > expectedReferences  ) throws Exception {
		List< String > actualReferences = parser.findProjectReferenceNames();
		assertThat( actualReferences, is( expectedReferences ));
	}
	
	//==============================================================================================
	
	public static void assertTreatWarningsAsErrors( ProjectFileParser parser , List< String > expectedValues  ) throws Exception {
		List< String > actualValues = parser.findTreatWarningsAsErrorsValues();
		assertThat( actualValues, is( expectedValues ));
	}
}
