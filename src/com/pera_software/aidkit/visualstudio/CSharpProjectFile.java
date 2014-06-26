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

import java.nio.file.*;
import java.util.*;

//##################################################################################################

public class CSharpProjectFile extends ProjectFile
{
	public static final String EXTENSION = ".csproj";

	//==============================================================================================

	public CSharpProjectFile( Path projectFilePath )
		throws Exception
	{
		super( projectFilePath );
	}

	//==============================================================================================

	@Override
	public String findTargetName()
		throws Exception
	{
		return findAssemblyName();
	}

	//==============================================================================================

	public String findAssemblyName()
		throws Exception
	{
		List< String > assemblyNames = _parser.findXmlTags( "//AssemblyName" );
		if ( !assemblyNames.isEmpty() )
			return assemblyNames.get( 0 );
		else
			return "";
	}

	//==============================================================================================

	@Override
	protected List< String > findOutputDirectoryNames()
		throws Exception
	{
		return _parser.findXmlTags( "//OutputPath" );
	}

	//==============================================================================================

	@Override
	protected List< String > findOutputFileNames()
		throws Exception
	{
		return Arrays.asList();
	}

	//==============================================================================================

	@Override
	protected List<String> findIntermediateDirectoryNames()
		throws Exception
	{
		// C# projects don't allow to define an intermediate directory so we use hard coded
		// values.
		// Note: Beneath the 'obj' directory there is a 'x86/Debug' and 'x86/Release' directory. But
		// we only want to delete the directories, so this additional information isn't needed.

		return Arrays.asList( "obj" );
	}

	//==============================================================================================

	@Override
	protected List<String> findPreBuildCommands()
		throws Exception
	{
		return _parser.findXmlLines( "//PreBuildEvent" );
	}

	//==============================================================================================

	@Override
	protected List<String> findPostBuildCommands()
		throws Exception
	{
		return _parser.findXmlLines( "//PostBuildEvent" );
	}
}
