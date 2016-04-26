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

package com.pera_software.aidkit.net;

import java.lang.reflect.*;
import java.net.*;
import java.util.*;

/*
 * According to this article http://support.microsoft.com/kb/308512 the windows
 * function gethostbyaddr() is causing the delay when WINS (NetBIOS) over TCP/IP
 * has been disabled. It stands to reason that an unavailable or missing WINS
 * server is also causing such an delay. Tests have also shown, that a reverse
 * look-up of unknown ip-addresses are also delayed.
 *
 * To mitigate this problem all calls to InetAddress.getHostName() (which calls
 * the underlying windows function gethostbyaddr) have been replaced with a caching
 * version. So the first look-up will still take approximately 4500 ms, but on all
 * subsequent calls there shouldn't be any observable delay.
 *
 * This sample shows the delay for an unknown or not existent address:
 * long before = System.currentTimeMillis();
 * InetAddress addr = InetAddress.getByName("192.0.0.48"); // <- Use unknown address here!
 * String hostName = InetAddress.getHostName();
 * long after = System.currentTimeMillis();
 */
//##################################################################################################

public class HostNameResolver
{
	private static HashMap< InetAddress, String > s_hostNameCache = new HashMap<>();

	//==============================================================================================

	public static synchronized String getHostName( InetAddress address )
	{
		String hostName;

		try {
			// Try to do as InetAddress.getHostName() does, if the InetAddress has been
			// constructed with a host name, then try to retrieve it.
			// Unfortunately the field 'hostName' has package visibility, so we have
			// to use reflection to get the value.

			Field hostNameField = InetAddress.class.getDeclaredField( "hostName" );
			hostNameField.setAccessible( true );
			hostName = ( String )hostNameField.get( address );

			// We don't have a host name, so try to get it from the cache:

			if ( hostName == null ) {
				if ( ( hostName = s_hostNameCache.get( address ) ) == null ) {
					hostName = address.getHostName();
					s_hostNameCache.put( address, hostName );
				}
			}
		} catch ( Exception cause ) {
			// If the above code doesn't work, then fall back to the original implementation,
			// even if it means with the delay.

			hostName = address.getHostName();
		}
		return hostName;
	}
}
