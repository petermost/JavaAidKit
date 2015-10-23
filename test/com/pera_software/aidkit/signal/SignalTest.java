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

package com.pera_software.aidkit.signal;

import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public abstract class SignalTest {
	
	private static final Object EXPECTED_ARGUMENTS[] = {
		new Byte( ( byte )1 ), 
		new Long( 2 ), 
		new Short( ( short )3 ),
		new Float( 4 ), 
		new Double( 5 ), 
		new String( "6" ), 
		new Integer( 7 ), 
		new Boolean( true ), 
		new Character( '9' )
	};
	
	private Class< ? extends Signal > _signalClass;
	private Class< ? > _slotClass;
	private Class< ? > _argumentTypes[];

	// Signals:

	private Signal _signal1;
	private Signal _signal2;

	private Method _connectMethod;
	private Method _emitMethod;
	private Method _disconnectMethod;

	// Slots:

	private Slot _connectedSlot1;
	private Slot _connectedSlot2;
	private Slot _disconnectedSlot;

	private Method _handleMethod;


	//==============================================================================================

	public SignalTest( Class< ? extends Signal > signalClass, Class< ? extends Slot > slotClass, int parameterCount ) throws Exception {
		_signalClass = signalClass;
		_slotClass = slotClass;

		// Create the signal:
		
		_signal1 = _signalClass.newInstance();
		_signal2 = _signalClass.newInstance();
		
		// Because of type erasure we have to use an array of Object classes:

		_argumentTypes = new Class< ? >[ parameterCount ];
		Arrays.fill( _argumentTypes, Object.class );

		// Get the Signal methods:

		_connectMethod = _signalClass.getMethod( "connect", Object.class );
		assertEquals( "connect() has wrong parameter count!", 1, _connectMethod.getParameterCount() );

		_emitMethod = _signalClass.getMethod( "emit", _argumentTypes );
		assertEquals( "emit() has wrong parameter count!", parameterCount, _emitMethod.getParameterCount() );

		_disconnectMethod = _signalClass.getMethod( "disconnect", Object.class );
		assertEquals( "disconnect() has wrong parameter count!", 1, _disconnectMethod.getParameterCount() );

		// Get the Slot methods:

		_handleMethod = _slotClass.getMethod( "handle", _argumentTypes );
		assertEquals( "handle() has wrong parameter count!", parameterCount, _handleMethod.getParameterCount() );

		// Create the slot mocks:

		_connectedSlot1 = createSlotMock();
		_connectedSlot2 = createSlotMock();

		_disconnectedSlot = createSlotMock();
	}

	//==============================================================================================

	private static void assertArguments( Object ... arguments ) {
		for ( int i = 0; arguments != null && i < arguments.length; ++i ) {
			Object argument = arguments[ i ];
			Object expectedArgument = EXPECTED_ARGUMENTS[ i ];

			assertEquals( expectedArgument, argument );
			assertEquals( expectedArgument.getClass(), argument.getClass() );
		}
	}

	private Object[] expectedArguments() {
		return Arrays.copyOf( EXPECTED_ARGUMENTS, _argumentTypes.length );
	}

	//==============================================================================================

	private Slot createSlotMock() {
		MethodArgumentsAsserter argumentsAsserter = new MethodArgumentsAsserter( _handleMethod, expectedArguments() );

		Class< ? >[] interfaces = new Class< ? >[] {
			_slotClass
		};
		Slot slot = ( Slot )Proxy.newProxyInstance( _slotClass.getClassLoader(), interfaces, argumentsAsserter );

		return slot;
	}

	//	private Slot createSlotMock( Callable0 callable )
	//	{
	//		return null;
	//	}

	private static void assertSlotCallCounter( int expectedCallCounter, Slot slot ) {
		MethodArgumentsAsserter argumentsAsserter = ( MethodArgumentsAsserter )Proxy.getInvocationHandler( slot );
		assertEquals( expectedCallCounter, argumentsAsserter.callCounter() );
	}

	//==============================================================================================

	@Test
	public void testEmitToMultipleSlots() throws Exception {
		// A Signal must be able to emit to multiple slots:

		assertTrue( ( Boolean )_connectMethod.invoke( _signal1, _connectedSlot1 ) );
		assertTrue( ( Boolean )_connectMethod.invoke( _signal1, _connectedSlot2 ) );

		_emitMethod.invoke( _signal1, expectedArguments() );
		assertSlotCallCounter( 1, _connectedSlot1 );
		assertSlotCallCounter( 1, _connectedSlot2 );
	}

	//==============================================================================================

	@Test
	public void testDontEmitToDisconnectedSlot() throws Exception {
		// A Signal must not emit to a disconnected slot:

		assertTrue( ( Boolean )_connectMethod.invoke( _signal1, _connectedSlot1 ) );

		assertTrue( ( Boolean )_connectMethod.invoke( _signal1, _disconnectedSlot ) );
		assertTrue( ( Boolean )_disconnectMethod.invoke( _signal1, _disconnectedSlot ) );

		_emitMethod.invoke( _signal1, expectedArguments() );
		assertSlotCallCounter( 0, _disconnectedSlot );
	}

	//==============================================================================================

	@Test
	public void testEmitToSignal() throws Exception {
		// A Signal can be used as a Slot:

		assertTrue( ( Boolean )_connectMethod.invoke( _signal2, _connectedSlot1 ) );
		assertTrue( ( Boolean )_connectMethod.invoke( _signal1, _signal2 ) );

		_emitMethod.invoke( _signal1, expectedArguments() );
		assertSlotCallCounter( 1, _connectedSlot1 );
	}

	//==============================================================================================

	//	@Test
	//	public void _testHandleDisconnectingSlot()
	//		throws Exception
	//	{
	//		// A Signal must be able to handle a Slot which disconnects while being called:
	//
	//		Signal signal = createSignal();
	//
	//		Slot disconnectingSlot = createSlotMock( new Callable0() {
	//			@Override
	//			public void call() throws Exception
	//			{
	//				_disconnectMethod.invoke( signal, this );
	//			}
	//		});
	//
	//		Slot unimportantSlot1 = createSlotMock();
	//		Slot unimportantSlot2 = createSlotMock();
	//
	//		_connectMethod.invoke( signal, disconnectingSlot );
	//		_connectMethod.invoke( signal, unimportantSlot2 );
	//		_connectMethod.invoke( signal, unimportantSlot1 );
	//
	//		_emitMethod.invoke( signal,  expectedArguments() );
	//	}

	//	@Test
	//	@SuppressWarnings("static-method")
	//	public void testHandleDisconnectingSlot()
	//		throws Exception
	//	{
	//		// A Signal must be able to handle a Slot which disconnects while being called:
	//
	//		Signal2< Byte, Long > signal = new Signal2<>();
	//
	//		Slot2< Byte, Long > disconnectingSlot = new Slot2< Byte, Long >() {
	//			@Override
	//			@SuppressWarnings( "unused" )
	//			public void handle( Byte parameter1, Long parameter2 ) throws Exception {
	//				signal.disconnect( this );
	//			}
	//		};
	//		Slot2< Byte, Long > unimportantSlot1 = ( parameter1, parameter2 ) -> {};
	//		Slot2< Byte, Long > unimportantSlot2 = ( parameter1, parameter2 ) -> {};
	//
	//		signal.connect( disconnectingSlot );
	//		signal.connect( unimportantSlot2 );
	//		signal.connect( unimportantSlot1 );
	//
	//		signal.emit( EXPECTED_PARAMETER_1, EXPECTED_ARGUMENT_2 );
	//	}

}
