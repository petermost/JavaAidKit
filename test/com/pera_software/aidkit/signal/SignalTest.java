// Copyright 2014 Peter Most, PERA Software Solutions GmbH
//
// This file is part of JavaAidKit.
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

public abstract class SignalTest // < SignalClass // extends Class< SignalBase< T extends Slot2 > >>
{
	protected static final Byte    EXPECTED_VALUE_1 = 1;
	protected static final Long    EXPECTED_VALUE_2 = 2l;
	protected static final Short   EXPECTED_VALUE_3 = 3;
	protected static final Float   EXPECTED_VALUE_4 = 4f;
	protected static final Double  EXPECTED_VALUE_5 = 5d;
	protected static final String  EXPECTED_VALUE_6 = "6";
	protected static final Integer EXPECTED_VALUE_7 = 7;

	protected static final Object EXPECTED_VALUES[] = 
		{
		EXPECTED_VALUE_1,
		EXPECTED_VALUE_2,
		EXPECTED_VALUE_3,
		EXPECTED_VALUE_4,
		EXPECTED_VALUE_5,
		EXPECTED_VALUE_6,
		EXPECTED_VALUE_7
		};

	protected static void assertParameters( Object ... parameters )
	{
		for ( int i = 0; i < parameters.length; ++i ) {
			assertEquals( parameters[ i ], EXPECTED_VALUES[ i ]);
		}
	}

	private Class< ? extends Signal > _signalClass;
	private Class< ? > _slotClass;
	private Class< ? > _parameterTypes[];

	//==============================================================================================

	public SignalTest( Class< ? extends Signal > signalClass, Class< ? extends Slot > slotClass,
		Class< ? > ... parameterTypes )
	{
		_signalClass = signalClass;
		_slotClass = slotClass;

		// Because of type erasure we have to use an array of Object classes, but we keep the
		// more type safer signature:

		_parameterTypes = new Class< ? >[ parameterTypes.length ];
		Arrays.fill( _parameterTypes, Object.class );
	}

	//==============================================================================================

	@Test
	public void test() 
		throws Exception
	{
		// A Signal must be able to emit to multiple slots:

		InvocationHandler slot1Call = ( proxy, method, parameters ) -> {
			assertParameters( parameters );
			return null;
		};
		InvocationHandler slot2Call = ( proxy, method, parameters ) -> {
			assertParameters( parameters );
			return null;
		};
		Slot slot1 = ( Slot )Proxy.newProxyInstance( _slotClass.getClassLoader(), new Class< ? >[] { _slotClass }, slot1Call );
		Slot slot2 = ( Slot )Proxy.newProxyInstance( _slotClass.getClassLoader(), new Class< ? >[] { _slotClass }, slot2Call );

		Method connectMethod = _signalClass.getMethod( "connect", Object.class );
		Signal signal = _signalClass.newInstance();
		connectMethod.invoke( signal, slot1 );
		connectMethod.invoke( signal, slot2 );

		Method emitMethod = _signalClass.getMethod( "emit", _parameterTypes );
		Object parameters[] = Arrays.copyOf( EXPECTED_VALUES, _parameterTypes.length );
		emitMethod.invoke( signal, parameters );
	}

	//==============================================================================================

}
