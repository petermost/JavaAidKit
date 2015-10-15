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

package com.pera_software.aidkit.collection;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

//##################################################################################################

public class ListsTest {

	//==============================================================================================

	@Test
	public void testRemoveDuplicates() {
		final List< String > expectedUniques = Arrays.asList( "1", "2", "3", "4" );
		List< String > duplicates = Arrays.asList( "1", "1", "2", "2", "2", "3", "3", "4" );

		List< String > actualUniques = Lists.removeDuplicates( duplicates );

		assertThat( actualUniques, is( expectedUniques ));
	}

	//==============================================================================================

	@Test
	public void testFindDuplicates() {
		final List< String > expectedDuplicates = Arrays.asList( "1", "2", "2", "3" );
		List< String > duplicates = Arrays.asList( "1", "1", "2", "2", "2", "3", "3", "4" );

		List< String > actualDuplicates = Lists.findDuplicates( duplicates );

		assertThat( actualDuplicates, is( expectedDuplicates ));
	}
}
