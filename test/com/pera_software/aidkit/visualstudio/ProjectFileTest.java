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

public abstract class ProjectFileTest
{
	private ProjectFile _projectFile;

	public ProjectFileTest( ProjectFile projectFile )
	{
		_projectFile = projectFile;
	}

	//==============================================================================================

	@Test
	public abstract void testFindIntermediateDirectoryNames()
		throws Exception;

	@Test
	public abstract void testFindTargetName()
		throws Exception;

	@Test
	public abstract void testFindBuildConfigurationNames()
		throws Exception;

	@Test
	public abstract void testFindPlatformNames()
		throws Exception;

	@Test
	public abstract void testFindOutputDirectoryNames()
		throws Exception;

	@Test
	public abstract void testFindPreBuildCommands()
		throws Exception;

	@Test
	public abstract void testFindPostBuildCommands()
		throws Exception;

	@Test
	public abstract void testFindDeployDirectoryNames()
		throws Exception;

	//==============================================================================================

	protected void assertPlatformNames( List< String > expectedPlatformNames )
		throws Exception
	{
		List< String > buildConfigurationNames = _projectFile.findBuildConfigurationNames();
		for ( String buildConfigurationName : buildConfigurationNames ) {
			List< String > actualPlatformNames = _projectFile.findPlatformNames( buildConfigurationName );
			assertThat( actualPlatformNames, is( expectedPlatformNames ));
		}
	}

	//==============================================================================================

	protected void assertBuildConfigurationNames( List< String > expectedBuildConfigurationNames )
		throws Exception
	{
		List< String > actualBuildConfigurationNames = _projectFile.findBuildConfigurationNames();
		assertThat( actualBuildConfigurationNames, is( expectedBuildConfigurationNames ));
	}

	//==============================================================================================

	protected void assertIntermediateDirectoryNames( List< String > expectedIntermediateDirectoryNames )
		throws Exception
	{
		List< String > actualIntermediateDirectoryNames = _projectFile.findIntermediateDirectoryNames();
		assertThat( actualIntermediateDirectoryNames, is( expectedIntermediateDirectoryNames ));
	}

	//==============================================================================================

	protected void assertTargetName( String expectedTargetName )
		throws Exception
	{
		String actualTargetName = _projectFile.findTargetName();
		assertThat( actualTargetName, is( expectedTargetName ));
	}

	//==============================================================================================

	protected void assertOutputDirectoryNames( List< String > expectedOutputDirectoryNames )
		throws Exception
	{
		List< String > actualOutputDirectoryNames = _projectFile.findOutputDirectoryNames();
		assertThat( actualOutputDirectoryNames, is( expectedOutputDirectoryNames ));
	}

	//==============================================================================================

	protected void assertPreBuildCommands( List< String > expectedBuildCommands )
		throws Exception
	{
		List< String > actualBuildEvents = _projectFile.findPreBuildCommands();
		assertThat( actualBuildEvents, is( expectedBuildCommands ));
	}

	//==============================================================================================

	protected void assertPostBuildCommands( List< String > expectedBuildCommands )
		throws Exception
	{
		List< String > actualBuildEvents = _projectFile.findPostBuildCommands();
		assertThat( actualBuildEvents, is( expectedBuildCommands ));
	}

	//==============================================================================================

	protected void assertDeployDirectoryNames( List< String > expectedDeployDirectories )
		throws Exception
	{
		List< String > actualDeployDirectories = _projectFile.findCopyDirectoryNames();
		assertThat( actualDeployDirectories, is( expectedDeployDirectories ));
	}
}
