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
import java.nio.file.*;
import java.util.regex.*;
import java.nio.charset.*;

//##################################################################################################

public class SolutionFile {
	private List< String > _lines = new ArrayList<>();
	private Path _path;

	//==============================================================================================

	public SolutionFile( Path solutionFilePath ) throws Exception {
		_path = solutionFilePath;
		_lines = Files.readAllLines( solutionFilePath, Charset.defaultCharset() );
	}

	//==============================================================================================

	public List< ProjectFile > findProjects() throws Exception {
		List< ProjectFile > projects = new ArrayList<>();
		Pattern projectPattern = Pattern.compile( "Project\\(\"\\{.*\\}\"\\) = \".*\", \"(.*)\", \"\\{.*\\}\"" );

		for ( String line : _lines ) {
			Matcher matcher = projectPattern.matcher( line );
			if ( matcher.matches() ) {
				Path projectPath = Paths.get( matcher.group( 1 ) );
				projectPath = _path.resolveSibling( projectPath );
				ProjectFileFactory.create( projectPath ).ifPresent( projectFile -> projects.add( projectFile ) );
			}
		}
		return projects;
	}

	//==============================================================================================

	public Path path() {
		return _path;
	}
}
