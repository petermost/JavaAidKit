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
// along with JavaAidKit.  If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.lang;

import static com.pera_software.aidkit.eclipse.NullObjects.*;

//##################################################################################################
/**
 * Central class to query platform specific settings.
 */
public final class SystemEnvironment {
	private SystemEnvironment() {
	}

	//==============================================================================================

	public static String systemDrive() {
		return requireNonNull( System.getenv( "SystemDrive" ));
	}

	//==============================================================================================

	public static String programsDirectory() {
		return requireNonNull( System.getenv( "ProgramFiles(x86)" ));
	}

	//==============================================================================================

	public static String settingsDirectory() {
		return requireNonNull( System.getenv( "AppData" ));
	}

	//==============================================================================================

	public static String windowsDirectory() {
		return requireNonNull( System.getenv( "windir" ));
	}
}
