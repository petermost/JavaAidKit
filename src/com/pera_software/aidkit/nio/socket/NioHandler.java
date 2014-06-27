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

import java.nio.*;
import java.nio.channels.*;

//#############################################################################
/**
 * The <code>NioDispatcher</code> uses this class to notify the user code
 * about events for a channel.
 */
public class NioHandler
{
	private ByteBuffer _readBuffer = null;
	private ByteBuffer _writeBuffer = null;

	//=============================================================================

	public NioHandler( int readBufferSize, int writeBufferSize )
	{
		_readBuffer = ByteBuffer.allocateDirect( readBufferSize );
		_writeBuffer = ByteBuffer.allocateDirect( writeBufferSize );
	}

	//=============================================================================
	/**
	 * Will be called when a new connection was established.
	 * @param dispatcher The dispatcher with which to register the channel.
	 * @param channel The connected channel which represents the connection.
	 * @throws Exception
	 * @see NioClient#connect(InetAddress, int, NioHandler)
	 */
	public void onConnect( NioDispatcher dispatcher, SocketChannel channel )
		throws Exception
	{
	}

	//=============================================================================
	/**
	 * Will be called when a new connection has been accepted.
	 * @param dispatcher The dispatcher with which to register the channel.
	 * @param clientChannel The accepted channel which represents the connection.
	 * @throws Exception
	 * @see NioServer#listen(int, NioHandler)
	 */
	public void onAccept( NioDispatcher dispatcher, SocketChannel clientChannel )
		throws Exception
	{
	}

	//=============================================================================
	/**
	 * Will be called when a channel has become ready to read from.
	 * @param channel The channel which is readable.
	 * @throws Exception
	 */
	public int onRead( SocketChannel channel )
		throws Exception
	{
		int bufferCount = -1;
		int channelCount = -1;

		// Always try to read the data:

		while ( ( channelCount = channel.read( _readBuffer ) ) > 0 ) {
		}

		// Independent of whether we have received data, let the user code try to
		// read from the buffer:

		_readBuffer.flip();
		_readBuffer.mark();
		try {
			while ( ( bufferCount = doRead( channel, _readBuffer ) ) > 0 )
				_readBuffer.mark();

			if ( bufferCount == 0 )
				_readBuffer.reset();
		} catch ( BufferUnderflowException cause ) {
			_readBuffer.reset();
		}
		_readBuffer.compact();

		return ( channelCount < 0 ? channelCount : bufferCount );
	}

	//=============================================================================
	/**
	 * Will be called to extract the received data from the buffer.
	 *
	 * @param channel The channel which received the data
	 * @param buffer The buffer holding the data.
	 * @return >0 the number of bytes read from the buffer.
	 *           0 when there is currently nothing to read but the channel should
	 *             stay open.
	 *          -1 when there is nothing to read anymore and the channel can be
	 *             closed.
	 * @throws BufferUnderFlowException when more data is read from the buffer then
	 *          is currently available.
	 * @throws Exception is only declared to also allow throwing other exceptions
	 *          from the handler.
	 *
	 * @see #onRead
	 */
	@SuppressWarnings( "static-method" )
	public int doRead( SocketChannel channel, ByteBuffer buffer )
		throws Exception, BufferUnderflowException
	{
		// Fallback implementation in case it isn't overwritten to ensure the buffer
		// is drained:

		int count = buffer.remaining();
		if ( count > 0 )
			buffer.position( count );

		return ( count );
	}

	//=============================================================================
	/**
	 * Will be called when a channel has become ready to write to.
	 * @param channel The channel which is writable.
	 */
	public int onWrite( SocketChannel channel )
		throws Exception
	{
		int bufferCount = -1;
		int channelCount = -1;

		_writeBuffer.mark();
		try {
			while ( ( bufferCount = doWrite( channel, _writeBuffer ) ) > 0 )
				_writeBuffer.mark();

			if ( bufferCount == 0 )
				_writeBuffer.reset();
		} catch ( BufferOverflowException cause ) {
			_writeBuffer.reset();
		}

		// Even if the user code didn't provide any data, it's possible that the
		// write-buffer still contains data, so try to send it:

		_writeBuffer.flip();

		while ( ( channelCount = channel.write( _writeBuffer ) ) > 0 ) {
		}

		_writeBuffer.compact();

		return ( channelCount < 0 ? channelCount : bufferCount );
	}

	//=============================================================================
	/**
	 * Will be called to fill the buffer with the data to send.
	 * @param channel The channel which sends the data
	 * @param buffer The buffer holding the data.
	 * @return >0 the number of bytes written into the buffer.
	 *           0 when there is currently nothing to write but the channel should
	 *             stay open.
	 *          -1 when there is nothing to write anymore and the channel can be
	 *             closed.
	 * @throws BufferOverflowException when more data is written to the buffer then
	 *          can fit.
	 * @throws Exception is only declared to also allow throwing other exceptions
	 *          from the handler.
	 * @see #onWrite
	 */
	@SuppressWarnings( "static-method" )
	public int doWrite( SocketChannel channel, ByteBuffer buffer )
		throws Exception, BufferOverflowException
	{
		// Fallback implementation in case it isn't overwritten to ensure the buffer
		// is drained:

		int count = buffer.remaining();
		if ( count > 0 )
			buffer.position( count );

		return ( count );
	}

	//=============================================================================
	/**
	 * Will be called when a channel is being unregistered.
	 * @param channel The channel which is unregistered.
	 * @throws Exception
	 */
	public void onClose( SelectableChannel channel )
		throws Exception
	{
	}

	//=============================================================================
	/**
	 * Will be called when an error has occurred while trying to dispatch data.
	 * @param dispatcher The dispatcher which had the error.
	 * @param channel The channel which caused the error.
	 * @param exception The exception which occurred.
	 *
	 *  @return if the overwritten handler was able to deal with the error then
	 *  return <code>true</code> otherwise return <code>false</code> which will
	 *  result in the exception being thrown from <code>dispatch</code>.
	 */
	@SuppressWarnings( "static-method" )
	public boolean onError( NioDispatcher dispatcher, SelectableChannel channel, Exception exception )
		throws Exception
	{
		// In case of an error, close the channel:

		dispatcher.closeChannel( channel );

		return ( false );
	}
}
