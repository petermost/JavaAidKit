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

import java.io.IOException;
import java.nio.channels.*;
import java.util.*;
import com.pera_software.aidkit.signal.*;

//#############################################################################
/**
 * The NioDispatcher is the central pivot point for the socket communication. It
 * allows to register channels for different operations and if that operation
 * becomes ready then the corresponding handler method will be called.
 */
public class NioDispatcher
{
	private final Signal2< SelectableChannel, Integer > connectionRegisteredSignal = new Signal2<>();
	private final Signal2< SelectableChannel, Integer > connectionUnregisteredSignal = new Signal2<>();
	private final Signal2< SelectableChannel, Exception > connectionClosingFailedSignal = new Signal2<>();
	private final Signal1< SelectableChannel > connectionClosedSignal = new Signal1<>();

	// private static Logger msLogger = Logger.getLogger( NioDispatcher.class );

	private Selector _selector;
	private Object _selectorGate = new Object();

	//=============================================================================

	public NioDispatcher()
		throws Exception
	{
		_selector = Selector.open();
	}

	//=============================================================================

	@Override
	public void finalize()
		throws Exception
	{
		_selector.close();
	}

	//=============================================================================

	public void closeChannels()
		throws Exception
	{
		Exception firstException = null;

		// Unregister every registered channel:

		for ( SelectionKey key : _selector.keys() ) {
			// Ensure that even in case of an exception all channels are being closed:

			try {
				if ( key.isValid() )
					closeChannel( key.channel() );
			} catch ( Exception cause ) {
				connectionClosingFailedSignal.emit( key.channel(), cause );
				// msLogger.error( "Closing connection failed!", cause );
				if ( firstException == null )
					firstException = cause;
			}
		}
		_selector.close();

		// Notify an error with the first exception caught:

		if ( firstException != null )
			throw firstException;
	}

	//=============================================================================
	/**
	 * Register the channel for a set of operations.
	 *
	 *  @param channel The channel to register
	 *  @param operations A set of operation flags (SelectionKey.OP_XXX)
	 *  @param handler The handler to call when the requested operation becomes ready.
	 *
	 *  @see #dispatch
	 *  @see java.nio.channels.SelectionKey#OP_CONNECT
	 *  @see java.nio.channels.SelectionKey#OP_ACCEPT
	 *  @see java.nio.channels.SelectionKey#OP_READ
	 *  @see java.nio.channels.SelectionKey#OP_WRITE
	 */
	public void register( SelectableChannel channel, int operations, NioHandler handler )
		throws Exception
	{
		// If one thread is waiting in Selector.select() and another thread tries to
		// register a channel, then SelectableChannel.register() blocks!
		// http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6446653

		synchronized ( _selectorGate ) {
			_selector.wakeup();

			channel.configureBlocking( false );
			SelectionKey key = channel.register( _selector, operations );

			connectionRegisteredSignal.emit( channel, operations );
			// msLogger.trace( "Registered connection <" + channel + "> for <" + operationName( operations ) + ">" );

			key.attach( handler );
		}
	}

	//=============================================================================
	/**
	 * Unregisters the channel. After a channel is unregistered the handler methods
	 * will not be called anymore, but it stays open and so can be registered again.
	 * @param channel The channel to unregister
	 * @see #register
	 */
	public void unregister( SelectableChannel channel )
		throws Exception
	{
		synchronized ( _selectorGate ) {
			_selector.wakeup();

			SelectionKey key = channel.keyFor( _selector );
			int operations = key.interestOps();
			key.interestOps( 0 );

			connectionUnregisteredSignal.emit( channel, operations );
			// msLogger.trace( "Unregistered connection <" + channel + "> for <" + operationName( operations ) + ">" );
		}
	}

	/**
	 * Closes the channel. After a channel is closed the handler methods
	 * of the associated handler will not be called anymore.
	 *
	 * @param channel The channel to close
	 */
	//=============================================================================
	public void closeChannel( SelectableChannel channel ) throws Exception
	//=============================================================================
	{
		SelectionKey key = channel.keyFor( _selector );
		NioHandler handler = ( NioHandler )key.attachment();
		handler.onClose( channel );

		connectionClosedSignal.emit( channel );
		// msLogger.trace( "Closing connection <" + channel + ">" );
		channel.close();
	}

	/**
	 * Dispatch the ready channels to the handler methods. The method will wait
	 * until at least one channel becomes ready and the corresponding handler
	 * method was called, or the current thread is interrupted. It is typically
	 * used like this:
	 * <pre>
	 * NioDispatcher dispatcher = NioDispatcher.instance();
	 * while ( threadContinues() ) {
	 *      dispatcher.dispatch();
	 * }
	 * </pre>
	 *
	 * @see java.nio.channels.Selector#select()
	 * @see java.lang.Thread#interrupt()
	 */
	//=============================================================================
	@SuppressWarnings({ "resource", "null" })
	public void dispatch()
		throws Exception
	//=============================================================================
	{
		NioHandler handler = null;
		SelectableChannel selectedChannel = null;

		int selectableKeys = _selector.select();
		Set< SelectionKey > selectedKeys = _selector.selectedKeys();

		// Test whether select() returned because of a wakeup() call.
		// NOTE: This might not work and I couldn't find/get a definitive answer
		// whether this check is correct and works reliable. But for now it does what
		// it's supposed to do.
		// -- P. Most

		if ( selectableKeys == 0 || selectableKeys != selectedKeys.size() ) {
			synchronized ( _selectorGate ) {
				// Just wait for register() to finish (See register() for further explanations)
			}
		}
		// But we still have to iterate over the selected keys, because it's possible
		// that a wakeup() and the readiness of a channel happened at the same time!

		for ( SelectionKey key : selectedKeys ) {
			try {
				if ( key.isValid() ) {
					selectedChannel = key.channel();
					handler = ( NioHandler )key.attachment();

					// Dispatch to the appropriate callbacks:

					if ( key.isConnectable() ) {
						SocketChannel clientChannel = ( SocketChannel )selectedChannel;
						if ( clientChannel.finishConnect() )
							handler.onConnect( this, clientChannel );
					}
					if ( key.isAcceptable() ) {
						ServerSocketChannel serverChannel = ( ServerSocketChannel )selectedChannel;
						SocketChannel clientChannel = serverChannel.accept();
						handler.onAccept( this, clientChannel );
					}
					if ( key.isReadable() ) {
						SocketChannel clientChannel = ( SocketChannel )selectedChannel;
						if ( handler.onRead( clientChannel ) < 0 ) {
							closeChannel( selectedChannel );
							continue;
						}
					}
					if ( key.isWritable() ) {
						SocketChannel channel = ( SocketChannel )selectedChannel;
						if ( handler.onWrite( channel ) < 0 ) {
							closeChannel( selectedChannel );
							continue;
						}
					}
				}
			} catch ( IOException cause ) {
				handler.onError( this, selectedChannel, cause );
				// Debug.warn( NioDispatcher.class, cause.getMessage(), cause );
			} catch ( Exception cause ) {
				NioException exception = new NioException( cause );
				boolean isHandled = handler.onError( this, selectedChannel, exception );
				if ( !isHandled )
					throw exception;
			}
		}
		selectedKeys.clear();
	}

	//=============================================================================
	public static String operationName( int operation )
	//=============================================================================
	{
		String name = "";

		if ( ( operation & SelectionKey.OP_READ ) != 0 )
			name += "OP_READ ";

		if ( ( operation & SelectionKey.OP_WRITE ) != 0 )
			name += "OP_WRITE ";

		if ( ( operation & SelectionKey.OP_CONNECT ) != 0 )
			name += "OP_CONNECT ";

		if ( ( operation & SelectionKey.OP_ACCEPT ) != 0 )
			name += "OP_ACCEPT ";

		if ( name.isEmpty() )
			name = "OP_XXXXX";

		return ( name );
	}

}
