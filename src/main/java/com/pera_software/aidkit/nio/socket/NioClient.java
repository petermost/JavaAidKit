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

import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.nio.channels.*;

//#############################################################################
/**
 * This class is used to establish a 'client' connection.
 */
public class NioClient implements Closeable {
	
	private NioDispatcher _dispatcher;
	private SocketChannel _clientChannel;

	//=============================================================================

	public NioClient( NioDispatcher dispatcher ) {
		_dispatcher = dispatcher;
	}

	//=============================================================================
	/**
	 * Initiates a connection. When a connection was established, then
	 * <code>NioHandler.onConnected</code> is called.
	 *
	 * @param address The address to connect to.
	 * @param port    The port to connect to.
	 * @param handler The handler which will be used to handle the different requests.
	 *
	 * @see NioHandler#onConnect
	 */
	@SuppressWarnings("resource")
	public void connect( InetAddress address, int port, NioHandler handler ) throws Exception {
		// Create the non-blocking client channel:

		_clientChannel = SocketChannel.open();
		_clientChannel.configureBlocking( false );

		// Initiate the connection:

		if ( _clientChannel.connect( new InetSocketAddress( address, port ) ) )
			handler.onConnect( _dispatcher, _clientChannel );
		else
			_dispatcher.register( _clientChannel, SelectionKey.OP_CONNECT, handler );
	}

	//=============================================================================
	/**
	 * Closes the connection and <code>NioHandler.onClose</code>is called.
	 */
	@Override
	public void close() throws IOException {
		try {
			_dispatcher.closeChannel( _clientChannel );
		} catch (Exception exception) {
			throw new IOException(exception);
		}
	}
}
