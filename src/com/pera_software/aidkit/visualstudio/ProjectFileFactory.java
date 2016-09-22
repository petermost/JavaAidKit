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

import java.nio.file.*;
import java.util.*;

//##################################################################################################

public final class ProjectFileFactory {

	//==============================================================================================

	private ProjectFileFactory() {
	}

	//==============================================================================================

	public static Optional< ProjectFile > create( Path projectPath ) throws Exception {
		if ( ProjectFiles.isCPlusPlusProjectFilePath( projectPath ) )
			return Optional.of( new CPlusPlusProjectFile( projectPath ));
		else if ( ProjectFiles.isCSharpProjectFilePath( projectPath ) )
			return Optional.of( new CSharpProjectFile( projectPath ));
		else
			return Optional.empty();
	}
}
