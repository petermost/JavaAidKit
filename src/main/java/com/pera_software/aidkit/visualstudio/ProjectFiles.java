// Copyright 2016 Peter Most, PERA Software Solutions GmbH
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

import java.nio.file.*;

/**
 * @author P. Most
 *
 */
public final class ProjectFiles {

	//==============================================================================================
	
	private ProjectFiles() {
	}

	//==============================================================================================

	private static boolean hasExtension( Path path, String extension ) {
		return path.toString().endsWith( extension );
	}

	//==============================================================================================
	
	public static boolean isSolutionFilePath( Path solutionFilePath ) {
		return hasExtension( solutionFilePath, SolutionFile.EXTENSION );
	}
	//==============================================================================================

	public static boolean isCPlusPlusProjectFilePath( Path projectFilePath ) {
		return hasExtension( projectFilePath, CPlusPlusProjectFile.EXTENSION );
	}

	//==============================================================================================

	public static boolean isCSharpProjectFilePath( Path projectFilePath ) {
		return hasExtension( projectFilePath, CSharpProjectFile.EXTENSION );
	}

	//==============================================================================================
	
}
