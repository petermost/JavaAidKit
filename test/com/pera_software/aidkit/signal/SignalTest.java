package com.pera_software.aidkit.signal;

import static org.junit.Assert.*;
import org.junit.*;

//##################################################################################################

abstract class SignalTest< SignalClass extends Class< SignalBase< T extends Slot2 > >>
{
	protected static final Byte    EXPECTED_VALUE_1 = 1;
	protected static final Long    EXPECTED_VALUE_2 = 2l;
	protected static final Short   EXPECTED_VALUE_3 = 3;
	protected static final Float   EXPECTED_VALUE_4 = 4f;
	protected static final Double  EXPECTED_VALUE_5 = 5d;
	protected static final String  EXPECTED_VALUE_6 = "6";
	protected static final Integer EXPECTED_VALUE_7 = 7;

	protected static final Object EXPECTED_VALUES[] = {
		EXPECTED_VALUE_1,
		EXPECTED_VALUE_2,
		EXPECTED_VALUE_3,
		EXPECTED_VALUE_4,
		EXPECTED_VALUE_5,
		EXPECTED_VALUE_6,
		EXPECTED_VALUE_7
	};

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void test( SignalClass signalClass )
	{

//		// A Signal must be able to emit to multiple slots:
//
//		Slot2< Byte, Long > mustCallSlot1 = ( value1, value2 ) -> {
//			assertParameters( value1, value2 );
//		};
//		Slot2< Byte, Long > mustCallSlot2 = ( value1, value2 ) -> {
//			assertParameters( value1, value2 );
//		};
//
//		SignalBase signal = signalClass.newInstance();
//		signal.connect( mustCallSlot1 );
//		signal.connect( mustCallSlot2 );
//
//		signalClass.getMethod( "emit" name, parameterTypes)emit( EXPECTED_VALUE_1, EXPECTED_VALUE_2 );
	}

	//==============================================================================================

	protected static void assertParameters( Object ... parameters )
	{
		for ( int i = 0; i < parameters.length; ++i ) {
			assertEquals( parameters[ i ], EXPECTED_VALUES[ i ]);
		}
	}
}
