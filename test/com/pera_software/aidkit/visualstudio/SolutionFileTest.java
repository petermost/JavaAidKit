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

import static org.junit.Assert.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;

public class SolutionFileTest
{
	public static final Path PATH = Resource.getPath(SolutionFileTest.class, "Solution.sln" );

	private SolutionFile _solutionFile;

	@Before
	public void before()
		throws Exception
	{
		_solutionFile = new SolutionFile( PATH );
	}

	@Test
	public void testFindProjectsSize()
		throws Exception
	{
		List< ProjectFile > projectFiles = _solutionFile.findProjects();
		assertEquals( 4, projectFiles.size() );
	}

	@Test
	public void testFindProjectsContent()
		throws Exception
	{
		List< Path > projectPaths = new ArrayList<>();
		for ( ProjectFile projectFile : _solutionFile.findProjects() ) {
			projectPaths.add( projectFile.path() );
		}
		assertTrue( projectPaths.contains( CustomCPlusPlusProjectFileTest.PATH ));
		assertTrue( projectPaths.contains( DefaultCPlusPlusProjectFileTest.PATH));
		assertTrue( projectPaths.contains( CustomCSharpProjectFileTest.PATH ));
		assertTrue( projectPaths.contains( DefaultCSharpProjectFileTest.PATH ));
	}

	@Test
	@SuppressWarnings("static-method")
	public void testIsCPlusPlusProjectFilePath()
	{
		Path cplusPlusProjectPath = DefaultCPlusPlusProjectFileTest.PATH;
		assertTrue( SolutionFile.isCPlusPlusProjectFilePath( cplusPlusProjectPath ));
	}

	@Test
	@SuppressWarnings("static-method")
	public void testIsCSharpProjectFilePath()
	{
		Path csharpProjectPath = DefaultCSharpProjectFileTest.PATH;
		assertTrue( SolutionFile.isCSharpProjectFilePath( csharpProjectPath ));
	}
}