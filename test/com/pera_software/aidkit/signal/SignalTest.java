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

// TODO: Give it a better name since it has nothing to do with a slot.
class SlotMock implements InvocationHandler
{
	private boolean _called = false;
	private InvocationHandler _handler;

	public SlotMock( InvocationHandler handler )
	{
		_handler = handler;
	}

	@Override
	public Object invoke( Object proxy, Method method, Object arguments[] )
		throws Throwable
	{
		_called = true;
		return _handler.invoke( proxy, method, arguments );
	}

	public boolean called()
	{
		return _called;
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

	// Signal methods:

	private Method _connectMethod;
	private Method _emitMethod;
	private Method _disconnectMethod;

	// Slot methods:

	private Method _callMethod;

	// Slot mocks:

	private Slot _mustCallSlot1;
	private Slot _mustCallSlot2;
	private Slot _mustNotCallSlot1;

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

	private Slot createSlotMock( InvocationHandler handler )
	{
		Class< ? >[] interfaces = new Class< ? >[] {
			_slotClass
		};
		Slot slot = ( Slot )Proxy.newProxyInstance( _slotClass.getClassLoader(), interfaces,
			new SlotMock( handler ));

		return slot;
	}

	private Slot createMustCallSlotMock()
	{
		InvocationHandler parameterAssertion = ( proxy, method, arguments ) -> {
			if ( method.equals( _callMethod )) {
				assertArguments( arguments );
			}
			return null;
		};
		return createSlotMock( parameterAssertion );
	}

	private Slot createMustNotCallSlotMock()
	{
		InvocationHandler failingAssertion = ( proxy, method, arguments ) -> {
			if ( method.getName().equals( "equals" )) {
				return proxy == arguments[ 0 ];
			} else if ( method.equals( _callMethod )) {
				fail( "Slot must not be called!" );
			}
			return null;
		};
		return createSlotMock( failingAssertion );
	}

	private static void assertSlotCalled( Slot slot )
	{
		SlotMock slotMock = ( SlotMock )Proxy.getInvocationHandler( slot );
		assertTrue( slotMock.called() );
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

		_callMethod = _slotClass.getMethod( "call", _argumentTypes );

		// Create the slot mocks:

		_mustCallSlot1 = createMustCallSlotMock();
		_mustCallSlot2 = createMustCallSlotMock();

		_mustNotCallSlot1 = createMustNotCallSlotMock();
	}

	//==============================================================================================

	@Test
	public void testEmitToMultipleSlots() throws Exception
	{
		// A Signal must be able to emit to multiple slots:

		Signal signal = _signalClass.newInstance();
//		assertTrue(( Boolean )_connectMethod.invoke( signal, _mustCallSlot1 ));
//		assertTrue(( Boolean )_connectMethod.invoke( signal, _mustCallSlot2 ));

		_emitMethod.invoke( signal, expectedArguments() );
		assertSlotCalled( _mustCallSlot1 );
		assertSlotCalled( _mustCallSlot2 );
	}

	//==============================================================================================


	@Test
	public void testDontEmitToDisconnectedSlot() throws Exception
	{
		// A Signal must not emit to a disconnected slot:

		Signal signal = _signalClass.newInstance();

		assertTrue(( Boolean )_connectMethod.invoke( signal, _mustCallSlot1 ));
		assertTrue(( Boolean )_connectMethod.invoke( signal, _mustNotCallSlot1 ));
		assertTrue(( Boolean )_disconnectMethod.invoke( signal, _mustNotCallSlot1 ));

		assertEquals( 1, _emitMethod.invoke( signal, expectedArguments() ));
	}

	//==============================================================================================

	@Test
	public void _testEmitToSignal()
		throws Exception
	{
		// A Signal can be used as a Slot:

		Signal mustCallSignalSlot = _signalClass.newInstance();
		assertTrue(( Boolean )_connectMethod.invoke( mustCallSignalSlot, _mustCallSlot1 ));

		Signal signal = _signalClass.newInstance();
		assertTrue(( Boolean )_connectMethod.invoke( signal, mustCallSignalSlot ));

		assertEquals( 1, _emitMethod.invoke( signal,  expectedArguments() ));
	}

}
