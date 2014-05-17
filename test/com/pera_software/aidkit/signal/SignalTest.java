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
import com.pera_software.aidkit.util.function.*;

class MethodArgumentsAsserter implements InvocationHandler
{
	private Method _expectedMethod;
	private int _callCounter = 0;
	private Object _expectedArguments[];

	public MethodArgumentsAsserter( Method expectedMethod, Object ... expectedArguments )
	{
		_expectedMethod = expectedMethod;
		_expectedArguments = expectedArguments;
	}

	private void assertArguments( Object ... arguments )
	{
		for ( int i = 0; arguments != null && i < arguments.length; ++i ) {
			Object argument = arguments[ i ];
			Object expectedArgument = _expectedArguments[ i ];

			assertEquals( expectedArgument, argument );
			assertEquals( expectedArgument.getClass(), argument.getClass() );
		}
	}

	@Override
	public Object invoke( Object proxy, Method method, Object arguments[] ) 
		throws Throwable
	{
		if ( method.getName().equals( "equals" )) {
			return proxy == arguments[ 0 ];
		} else if ( method.equals( _expectedMethod )) {
			assertArguments( arguments );
			++_callCounter;
		}
		return null;
	}

	public int callCounter()
	{
		return _callCounter;
	}
}
//##################################################################################################

public abstract class SignalTest
{
	protected static final Byte EXPECTED_ARGUMENT_1 = 1;
	protected static final Long EXPECTED_ARGUMENT_2 = 2l;
	protected static final Short EXPECTED_ARGUMENT_3 = 3;
	protected static final Float EXPECTED_ARGUMENT_4 = 4f;
	protected static final Double EXPECTED_ARGUMENT_5 = 5d;
	protected static final String EXPECTED_ARGUMENT_6 = "6";
	protected static final Integer EXPECTED_ARGUMENT_7 = 7;

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

	private Method _callMethod;

	//==============================================================================================

	protected static final Object EXPECTED_ARGUMENTS[] = {
		EXPECTED_ARGUMENT_1,
		EXPECTED_ARGUMENT_2,
		EXPECTED_ARGUMENT_3,
		EXPECTED_ARGUMENT_4,
		EXPECTED_ARGUMENT_5,
		EXPECTED_ARGUMENT_6,
		EXPECTED_ARGUMENT_7
	};

	//==============================================================================================

	protected static void assertArguments( Object ... arguments )
	{
		for ( int i = 0; arguments != null && i < arguments.length; ++i ) {
			Object argument = arguments[ i ];
			Object expectedArgument = EXPECTED_ARGUMENTS[ i ];

			assertEquals( expectedArgument, argument );
			assertEquals( expectedArgument.getClass(), argument.getClass() );
		}
	}

	private Object[] expectedArguments()
	{
		return Arrays.copyOf( EXPECTED_ARGUMENTS, _argumentTypes.length );
	}

	//==============================================================================================

	private Signal createSignal() 
		throws Exception
	{
		return _signalClass.newInstance();
	}

	private Slot createSlotMock()
	{
		MethodArgumentsAsserter argumentsAsserter = new MethodArgumentsAsserter( _callMethod, expectedArguments() );

		Class< ? >[] interfaces = new Class< ? >[] { _slotClass };
		Slot slot = ( Slot )Proxy.newProxyInstance( _slotClass.getClassLoader(), interfaces, argumentsAsserter );

		return slot;
	}

	private Slot createSlotMock( Callable0 callable )
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static void assertSlotCallCounter( int expectedCallCounter, Slot slot )
	{
		MethodArgumentsAsserter argumentsAsserter = ( MethodArgumentsAsserter )Proxy.getInvocationHandler( slot );
		assertEquals( expectedCallCounter, argumentsAsserter.callCounter() );
	}

	//==============================================================================================

	public SignalTest( Class< ? extends Signal > signalClass, Class< ? extends Slot > slotClass,
		Class< ? > ... parameterTypes ) throws Exception
	{
		_signalClass = signalClass;
		_slotClass = slotClass;

		// Because of type erasure we have to use an array of Object classes, but we keep the type
		// safe constructor signature anyway:

		_argumentTypes = new Class< ? >[ parameterTypes.length ];
		Arrays.fill( _argumentTypes, Object.class );

		// Get the Signal methods:

		_connectMethod = _signalClass.getMethod( "connect", Object.class );
		_emitMethod = _signalClass.getMethod( "emit", _argumentTypes );
		_disconnectMethod = _signalClass.getMethod( "disconnect", Object.class );

		// Get the Slot methods:

		_callMethod = _slotClass.getMethod( "handle", _argumentTypes );

		// Create the signal:

		_signal1 = createSignal();
		_signal2 = createSignal();

		// Create the slot mocks:

		_connectedSlot1 = createSlotMock();
		_connectedSlot2 = createSlotMock();

		_disconnectedSlot = createSlotMock();
	}

	//==============================================================================================

	@Test
	public void testEmitToMultipleSlots() throws Exception
	{
		// A Signal must be able to emit to multiple slots:

		assertTrue(( Boolean )_connectMethod.invoke( _signal1, _connectedSlot1 ));
		assertTrue(( Boolean )_connectMethod.invoke( _signal1, _connectedSlot2 ));

		_emitMethod.invoke( _signal1, expectedArguments() );
		assertSlotCallCounter( 1, _connectedSlot1 );
		assertSlotCallCounter( 1, _connectedSlot2 );
	}

	//==============================================================================================

	@Test
	public void testDontEmitToDisconnectedSlot() throws Exception
	{
		// A Signal must not emit to a disconnected slot:

		assertTrue(( Boolean )_connectMethod.invoke( _signal1, _connectedSlot1 ));

		assertTrue(( Boolean )_connectMethod.invoke( _signal1, _disconnectedSlot ));
		assertTrue(( Boolean )_disconnectMethod.invoke( _signal1, _disconnectedSlot ));

		_emitMethod.invoke( _signal1, expectedArguments() );
		assertSlotCallCounter( 0, _disconnectedSlot );
	}

	//==============================================================================================

	@Test
	public void testEmitToSignal()
		throws Exception
	{
		// A Signal can be used as a Slot:

		assertTrue(( Boolean )_connectMethod.invoke( _signal2, _connectedSlot1 ));
		assertTrue(( Boolean )_connectMethod.invoke( _signal1, _signal2 ));

		_emitMethod.invoke( _signal1,  expectedArguments() );
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
}
