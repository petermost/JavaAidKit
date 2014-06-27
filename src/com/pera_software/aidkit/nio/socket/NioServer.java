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

package com.pera_software.aidkit.nio.socket;

import java.net.*;
import java.nio.channels.*;


//#############################################################################

public class NioServer
{
	private NioDispatcher _dispatcher;
	private ServerSocketChannel _serverChannel;

	//=============================================================================

	public NioServer( NioDispatcher dispatcher )
	{
		_dispatcher = dispatcher;
	}

	//=============================================================================

	@SuppressWarnings( "resource" )
	public void listen( int port, NioHandler handler )
		throws Exception
	{
		// Create the non-blocking server channel:

		_serverChannel = ServerSocketChannel.open();
		_serverChannel.configureBlocking( false );

		// Bind the socket to the port:

		ServerSocket serverSocket = _serverChannel.socket();
		serverSocket.setReuseAddress( true );
		InetSocketAddress socketAddress = new InetSocketAddress( port );
		serverSocket.bind( socketAddress );

		// Register for accepted connections:

		_dispatcher.register( _serverChannel, SelectionKey.OP_ACCEPT, handler );
	}

	//=============================================================================

	public void close() throws Exception
	{
		_dispatcher.closeChannel( _serverChannel );
	}
}
