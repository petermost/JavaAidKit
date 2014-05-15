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

//##################################################################################################

public abstract class SignalTest
{
	protected static final Byte EXPECTED_VALUE_1 = 1;
	protected static final Long EXPECTED_VALUE_2 = 2l;
	protected static final Short EXPECTED_VALUE_3 = 3;
	protected static final Float EXPECTED_VALUE_4 = 4f;
	protected static final Double EXPECTED_VALUE_5 = 5d;
	protected static final String EXPECTED_VALUE_6 = "6";
	protected static final Integer EXPECTED_VALUE_7 = 7;

	private Class< ? extends Signal > _signalClass;
	private Class< ? > _slotClass;
	private Class< ? > _parameterTypes[];

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

	protected static final Object EXPECTED_VALUES[] = {
		EXPECTED_VALUE_1, EXPECTED_VALUE_2, EXPECTED_VALUE_3, EXPECTED_VALUE_4, EXPECTED_VALUE_5,
		EXPECTED_VALUE_6, EXPECTED_VALUE_7
	};

	//==============================================================================================

	protected static void assertParameters( Object ... parameters )
	{
		for ( int i = 0; parameters != null && i < parameters.length; ++i ) {
			Object parameter = parameters[ i ];
			Object expectedValue = EXPECTED_VALUES[ i ];

			assertEquals( expectedValue, parameter );
			assertEquals( expectedValue.getClass(), parameter.getClass() );
		}
	}

	//==============================================================================================

	private Slot createSlotMock( InvocationHandler handler )
	{
		Class< ? >[] interfaces = new Class< ? >[] {
			_slotClass
		};
		Slot slot = ( Slot )Proxy.newProxyInstance( _slotClass.getClassLoader(), interfaces,
			handler );

		return slot;
	}

	private Slot createMustCallSlotMock()
	{
		InvocationHandler parameterAssertion = ( proxy, method, parameters ) -> {
			if ( method.equals( _callMethod )) {
				assertParameters( parameters );
			}
			return null;
		};
		return createSlotMock( parameterAssertion );
	}

	private Slot createMustNotCallSlotMock()
	{
		InvocationHandler failingAssertion = ( proxy, method, parameters ) -> {
			if ( method.getName().equals( "equals" )) {
				return proxy == parameters[ 0 ];
			} else if ( method.equals( _callMethod )) {
				fail( "Slot must not be called!" );
			}
			return null;
		};
		return createSlotMock( failingAssertion );
	}

	//==============================================================================================

	public SignalTest( Class< ? extends Signal > signalClass, Class< ? extends Slot > slotClass,
		Class< ? > ... parameterTypes ) throws Exception
	{
		_signalClass = signalClass;
		_slotClass = slotClass;

		// Because of type erasure we have to use an array of Object classes, but we keep the type
		// safe constructor signature anyway:

		_parameterTypes = new Class< ? >[ parameterTypes.length ];
		Arrays.fill( _parameterTypes, Object.class );

		// Get the Signal methods:

		_connectMethod = _signalClass.getMethod( "connect", Object.class );
		_emitMethod = _signalClass.getMethod( "emit", _parameterTypes );
		_disconnectMethod = _signalClass.getMethod( "disconnect", Object.class );

		// Get the Slot methods:

		_callMethod = _slotClass.getMethod( "call", _parameterTypes );

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
		assertTrue(( Boolean )_connectMethod.invoke( signal, _mustCallSlot1 ));
		assertTrue(( Boolean )_connectMethod.invoke( signal, _mustCallSlot2 ));

		Object parameters[] = Arrays.copyOf( EXPECTED_VALUES, _parameterTypes.length );
		_emitMethod.invoke( signal, parameters );
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

		Object parameters[] = Arrays.copyOf( EXPECTED_VALUES, _parameterTypes.length );
		_emitMethod.invoke( signal, parameters );
	}

}
