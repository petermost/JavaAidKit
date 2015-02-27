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
	private Path _path;
	private List< String > _lines = new ArrayList<>();
	private Map< Path, ProjectFile > _projects = new TreeMap<>();

	//==============================================================================================

	public SolutionFile( Path solutionFilePath ) throws Exception {
		_path = solutionFilePath;
		_lines = Files.readAllLines( solutionFilePath, Charset.defaultCharset() );
	}

	//==============================================================================================

	public List< ProjectFile > loadProjects() throws Exception {
		if ( _projects.isEmpty() ) {
			Pattern projectPattern = Pattern.compile( "Project\\(\"\\{.*\\}\"\\) = \".*\", \"(.*)\", \"\\{.*\\}\"" );
	
			for ( String line : _lines ) {
				Matcher matcher = projectPattern.matcher( line );
				if ( matcher.matches() ) {
					Path projectPath = Paths.get( matcher.group( 1 ));
					projectPath = _path.resolveSibling( projectPath );
					Optional< ProjectFile > optionalProjectFile = ProjectFileFactory.create( projectPath );
					if ( optionalProjectFile.isPresent() )
						_projects.put( projectPath.normalize(), optionalProjectFile.get() );
				}
			}
		}
		return new ArrayList<>( _projects.values() );
	}

	//==============================================================================================
	
	public ProjectFile findProject( Path projectPath ) {
		Path normalizedProjectPath = projectPath.normalize();
		ProjectFile projectFile = _projects.get( normalizedProjectPath );
		if ( projectFile == null )
			return projectFile;
		else
			return projectFile;
	}
	
	//==============================================================================================

	public Path path() {
		return _path;
	}
}
