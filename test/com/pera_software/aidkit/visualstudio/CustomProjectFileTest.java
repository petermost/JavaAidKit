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

import static org.junit.Assert.*;
import java.util.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;
import com.pera_software.aidkit.io.*;

@RunWith( Parameterized.class )
public class CustomProjectFileTest extends ProjectFileTest {

	@Parameters
	public static Iterable< Object[] > loadProjectFiles() throws Exception {
		return Arrays.asList( new Object[][] {
			{ new CPlusPlusProjectFile( Resources.asPath( CustomProjectFileTest.class,
				"2010/CPlusPlusProjectWithCustomOutputDirectories.vcxproj" )) },
				
			{ new CPlusPlusProjectFile( Resources.asPath( CustomProjectFileTest.class,
				"2013/CPlusPlusProjectWithCustomOutputDirectories.vcxproj" )) },
				
			{ new CSharpProjectFile( Resources.asPath( CustomProjectFileTest.class,
				"2010/CSharpProjectWithCustomOutputDirectories.csproj" )) },
				
			{ new CSharpProjectFile( Resources.asPath( CustomProjectFileTest.class,
				"2013/CSharpProjectWithCustomOutputDirectories.csproj" )) }
		});
	}
	
	public CustomProjectFileTest( ProjectFile projectFile ) {
		super( projectFile );
	}

	@Override
	public void doTestFindTreatWarningsAsErrors( ProjectFile project ) throws Exception {
		assertTrue( project.getTreatWarningsAsErrors() );
	}
}
