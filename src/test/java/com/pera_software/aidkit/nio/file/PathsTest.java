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

package com.pera_software.aidkit.nio.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PathsTest
{
	@Test
	public void testRemoveOverlaps()
	{
		final List< Path > expectedDirs = Arrays.asList(
			java.nio.file.Paths.get( "c:/dir1" ),
			java.nio.file.Paths.get( "c:/single"
				));
		List< Path > overlappedDirs = new ArrayList<>();
		overlappedDirs.add( java.nio.file.Paths.get( "c:/dir1/dir2/dir3" ));
		overlappedDirs.add( java.nio.file.Paths.get( "c:/dir1" ));
		overlappedDirs.add( java.nio.file.Paths.get( "c:/dir1/dir2" ));
		overlappedDirs.add( java.nio.file.Paths.get( "c:/single" ));

		List< Path > actualDirs = Paths.removeOverlaps( overlappedDirs );
		assertThat( actualDirs, is( expectedDirs ));
	}
}
