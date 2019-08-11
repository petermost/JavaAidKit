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

package com.pera_software.aidkit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import com.pera_software.aidkit.io.ResourceNotFoundException;

/**
 * @author P. Most
 *
 */
public class AidKitTest {

	@Test
	public void testGetResourceAsStream() throws ResourceNotFoundException {
		// We are just interested whether we can load a resource, so if one works, then we assume
		// the others will too:
		
		assertNotNull( AidKit.getResourceAsStream( AidKit.APPLICATION_EXIT_ICON ));
	}
}
