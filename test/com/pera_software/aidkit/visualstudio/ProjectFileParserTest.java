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

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public abstract class ProjectFileParserTest {
	private ProjectFileParser _projectFileParser;

	public ProjectFileParserTest( ProjectFileParser projectFileParser ) {
		_projectFileParser = projectFileParser;
	}

	//==============================================================================================

	@Test
	public abstract void testFindSourceFileNames() throws Exception;

	@Test
	public abstract void testFindIntermediateDirectoryNames() throws Exception;

	@Test
	public abstract void testFindTargetName() throws Exception;

	@Test
	public abstract void testFindBuildConfigurations() throws Exception;

	@Test
	public abstract void testFindOutputDirectoryNames() throws Exception;

	@Test
	public abstract void testFindPreBuildCommands() throws Exception;

	@Test
	public abstract void testFindPostBuildCommands() throws Exception;

	@Test
	public abstract void testFindDeployDirectoryNames() throws Exception;

	@Test
	public abstract void testFindReferencesProjectNames() throws Exception;
	
	//==============================================================================================

	protected void assertSourceFileNames( List< String > expectedSourceFileNames ) throws Exception {
		List< String > actualSourceFileNames = _projectFileParser.findSourceFileNames();
		assertThat( actualSourceFileNames, is( expectedSourceFileNames ) );
	}

	//==============================================================================================

	protected void assertBuildConfigurations( List< BuildConfiguration > expectedBuildConfigurations ) throws Exception {
		List< BuildConfiguration > actualBuildConfigurations = _projectFileParser.findBuildConfigurations();
		assertThat( actualBuildConfigurations, is( expectedBuildConfigurations ) );
	}

	//==============================================================================================

	protected void assertIntermediateDirectoryNames( List< String > expectedIntermediateDirectoryNames ) throws Exception {
		List< String > actualIntermediateDirectoryNames = _projectFileParser.findIntermediateDirectoryNames();
		assertThat( actualIntermediateDirectoryNames, is( expectedIntermediateDirectoryNames ) );
	}

	//==============================================================================================

	protected void assertTargetName( String expectedTargetName ) throws Exception {
		String actualTargetName = _projectFileParser.findTargetName();
		assertThat( actualTargetName, is( expectedTargetName ) );
	}

	//==============================================================================================

	protected void assertOutputDirectoryNames( List< String > expectedOutputDirectoryNames ) throws Exception {
		List< String > actualOutputDirectoryNames = _projectFileParser.findOutputDirectoryNames();
		assertThat( actualOutputDirectoryNames, is( expectedOutputDirectoryNames ) );
	}

	//==============================================================================================

	protected void assertPreBuildCommands( List< String > expectedBuildCommands ) throws Exception {
		List< String > actualBuildEvents = _projectFileParser.findPreBuildCommands();
		assertThat( actualBuildEvents, is( expectedBuildCommands ) );
	}

	//==============================================================================================

	protected void assertPostBuildCommands( List< String > expectedBuildCommands ) throws Exception {
		List< String > actualBuildEvents = _projectFileParser.findPostBuildCommands();
		assertThat( actualBuildEvents, is( expectedBuildCommands ) );
	}

	//==============================================================================================

	protected void assertDeployDirectoryNames( List< String > expectedDeployDirectories ) throws Exception {
		List< String > actualDeployDirectories = _projectFileParser.findCopyDirectoryNames();
		assertThat( actualDeployDirectories, is( expectedDeployDirectories ) );
	}
	
	//==============================================================================================
	
	protected void assertReferencedProjectNames( List< String > expectedReferences ) throws Exception {
		List< String > actualReferences = _projectFileParser.findProjectReferenceNames();
		assertThat( actualReferences, is( expectedReferences ));
	}
}
