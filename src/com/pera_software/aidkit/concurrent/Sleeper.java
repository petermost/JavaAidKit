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

package com.pera_software.aidkit.concurrent;

public class Sleeper {
	
	//=============================================================================
	/**
	 * Simple wrapper around Thread.sleep() to avoid writing repeatedly the exception
	 * handling code.
	 * @param timeSpan The time to sleep in milliseconds
	 * @return Whether the sleep has succeeded without being interrupted.
	 */
	public static boolean sleep( long timeSpan ) {
		try {
			Thread.sleep( timeSpan );
			return ( true );
		} catch ( InterruptedException cause ) {
			return ( false );
		}
	}
}
