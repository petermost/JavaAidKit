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
import org.junit.runners.Parameterized.Parameters;

//##################################################################################################

@RunWith( Parameterized.class )
public final class DefaultCSharpProjectFileTest extends CSharpProjectFileTest
{
	@Parameters
	public static Iterable< Object[] > loadProjectFiles()
		throws Exception
	{
		return Arrays.asList( new Object[][] {
			{ new CSharpProjectFile( Resource.getPath( DefaultCSharpProjectFileTest.class,  "2010/CSharpProjectWithDefaultOutputDirectories.csproj" )) },
			{ new CSharpProjectFile( Resource.getPath( DefaultCSharpProjectFileTest.class,  "2013/CSharpProjectWithDefaultOutputDirectories.csproj" )) }
		});
	}

	//==============================================================================================

	public DefaultCSharpProjectFileTest( ProjectFile projectFile )
	{
		super( projectFile );
	}

	//==============================================================================================

	@Override
	public void testFindIntermediateDirectoryNames()
		throws Exception
	{
		assertIntermediateDirectoryNames( Arrays.asList( "obj" ));
	}

	//==============================================================================================

	@Override
	public void testFindTargetName()
		throws Exception
	{
		assertTargetName( "CSharpProjectWithDefaultOutputDirectories" );
	}

	//==============================================================================================

	@Override
	public void testFindBuildConfigurationNames()
		throws Exception
	{
		assertBuildConfigurationNames( Arrays.asList( "Debug", "Release" ));
	}

	//==============================================================================================

	@Override
	public void testFindOutputDirectoryNames() throws Exception
	{
		assertOutputDirectoryNames( Arrays.asList( "bin\\Debug\\", "bin\\Release\\" ));
	}

	//==============================================================================================

	@Override
	public void testFindPreBuildCommands()
		throws Exception
	{
		assertPostBuildCommands( Arrays.asList() );
	}

	//==============================================================================================

	@Override
	public void testFindPostBuildCommands() throws Exception
	{
		assertPostBuildCommands( Arrays.asList() );
	}
	//==============================================================================================

	@Override
	public void testFindDeployDirectoryNames()
		throws Exception
	{
		assertDeployDirectoryNames( Arrays.asList() );
	}
}
