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

import org.junit.*;

public abstract class ProjectFileParserTest {
	private ProjectFileParser _projectFileParser;

	public ProjectFileParserTest( ProjectFileParser projectFileParser ) {
		_projectFileParser = projectFileParser;
	}

	//==============================================================================================

	public abstract void doTestFindSourceFileNames( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindIntermediateDirectoryNames( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindBuildConfigurations( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindTargetName( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindOutputDirectoryNames( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindPreBuildCommands( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindPostBuildCommands(ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindDeployDirectoryNames(ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindProjectReferenceNames(ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindLibraryReferenceNames( ProjectFileParser parser ) throws Exception;
	
	public abstract void doTestFindTreatWarningsAsErrors(ProjectFileParser parser ) throws Exception;
	
	@Test
	public void testFindSourceFileNames() throws Exception {
		doTestFindSourceFileNames( _projectFileParser );
	}
	
	@Test
	public void testFindIntermediateDirectoryNames() throws Exception {
		doTestFindIntermediateDirectoryNames( _projectFileParser );
	}
	
	@Test
	public void testFindBuildConfigurations() throws Exception {
		doTestFindBuildConfigurations( _projectFileParser );
	}

	@Test
	public void testFindTargetName() throws Exception {
		doTestFindTargetName( _projectFileParser );
	}

	@Test
	public void testFindOutputDirectoryNames() throws Exception {
		doTestFindOutputDirectoryNames( _projectFileParser );
	}

	@Test
	public void testFindPreBuildCommands() throws Exception {
		doTestFindPreBuildCommands(_projectFileParser);
	}

	@Test
	public void testFindPostBuildCommands() throws Exception {
		doTestFindPostBuildCommands(_projectFileParser);
	}

	@Test
	public void testFindDeployDirectoryNames() throws Exception {
		doTestFindDeployDirectoryNames(_projectFileParser);
	}

	@Test
	public void testFindProjectReferenceNames() throws Exception {
		doTestFindProjectReferenceNames(_projectFileParser);
	}
	
	@Test
	public void testFindLibraryReferenceNames() throws Exception {
		doTestFindLibraryReferenceNames( _projectFileParser );
	}
	
	@Test
	public void testFindTreatWarningsAsErrors() throws Exception {
		doTestFindTreatWarningsAsErrors(_projectFileParser);
	}
}
