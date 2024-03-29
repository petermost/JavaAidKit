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

package com.pera_software.aidkit.system;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Enumeration;
import java.util.Properties;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * @author P. Most
 *
 */
public class SystemPropertiesTest {

	@Test
	public void printAllProperties()
	{
		Properties properties = System.getProperties();
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			String value = properties.getProperty(key);
			System.out.printf("'%s' = '%s'\n", key, value);
		}
	}

	@Test
	public void testGetOsArchitecture()
	{
		String osArchitecture = SystemProperties.getOsArchitecture().get();
		assertThat(osArchitecture, Matchers.anyOf(is(SystemProperties.OS_ARCHITECTURE_32), is(SystemProperties.OS_ARCHITECTURE_64)));
	}

}
