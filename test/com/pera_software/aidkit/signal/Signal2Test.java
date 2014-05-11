package com.pera_software.aidkit.signal;

import org.junit.*;
import static org.junit.Assert.*;

//##################################################################################################

public class Signal2Test extends SignalTest< Class< Signal2< Byte, Long >>>
{
	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testEmitToMultipleSlots()
	{
		// A Signal must be able to emit to multiple slots:

		Slot2< Byte, Long > mustCallSlot1 = ( value1, value2 ) -> {
			assertParameters( value1, value2 );
		};
		Slot2< Byte, Long > mustCallSlot2 = ( value1, value2 ) -> {
			assertParameters( value1, value2 );
		};

		Signal2< Byte, Long > signal = new Signal2<>();
		signal.connect( mustCallSlot1 );
		signal.connect( mustCallSlot2 );

		signal.emit( EXPECTED_VALUE_1, EXPECTED_VALUE_2 );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testDontEmitToDisconnectedSlot()
	{
		// A Signal must not emit to a disconnected slot:

		Slot2< Byte, Long > mustCallSlot = ( value1, value2 ) -> {
			assertParameters( value1, value2 );
		};
		Slot2< Byte, Long > mustNotCallSlot = ( value1, value2 ) -> {
			fail( "Slot must not be called!" );
		};

		Signal2< Byte, Long > signal = new Signal2<>();

		signal.connect( mustCallSlot );
		signal.connect( mustNotCallSlot );
		signal.disconnect( mustNotCallSlot );

		signal.emit( EXPECTED_VALUE_1, EXPECTED_VALUE_2 );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testEmitToSignal()
	{
		// A Signal can be used as a Slot:

		Signal2< Byte, Long > mustCallSignalSlot = new Signal2<>();
		Slot2< Byte, Long > mustCallSlot = ( value1, value2 ) -> {
			assertParameters( value1, value2 );
		};
		mustCallSignalSlot.connect( mustCallSlot );

		Signal2< Byte, Long > signal = new Signal2<>();
		signal.connect( mustCallSignalSlot );

		signal.emit( EXPECTED_VALUE_1, EXPECTED_VALUE_2 );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testHandleDisconnectingSlot()
	{
		// A Signal must be able to handle a Slot which disconnects while being called:

		Signal2< Byte, Long > signal = new Signal2<>();

		Slot2< Byte, Long > disconnectingSlot = new Slot2< Byte, Long >() {
			@Override
			@SuppressWarnings("unused")
			public void call( Byte value1, Long value2 ) throws Exception {
				signal.disconnect( this );
			}
		};
		Slot2< Byte, Long > unimportantSlot1 = ( value1, value2 ) -> {};
		Slot2< Byte, Long > unimportantSlot2 = ( value1, value2 ) -> {};

		signal.connect( disconnectingSlot );
		signal.connect( unimportantSlot2 );
		signal.connect( unimportantSlot1 );

		signal.emit( EXPECTED_VALUE_1, EXPECTED_VALUE_2 );
	}
}
