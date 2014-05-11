package com.pera_software.aidkit.signal;

import org.junit.*;
import static org.junit.Assert.*;

//##################################################################################################

public class Signal1Test extends SignalTest
{
	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testEmitToMultipleSlots()
	{
		// A Signal must be able to emit to multiple slots:

		Slot1< Byte > mustCallSlot1 = ( value1 ) -> {
			assertParameters( value1 );
		};
		Slot1< Byte > mustCallSlot2 = ( value1 ) -> {
			assertParameters( value1 );
		};

		Signal1< Byte > signal = new Signal1<>();
		signal.connect( mustCallSlot1 );
		signal.connect( mustCallSlot2 );

		signal.emit( EXPECTED_VALUE_1 );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testDontEmitToDisconnectedSlot()
	{
		// A Signal must not emit to a disconnected slot:

		Slot1< Byte > mustCallSlot = ( value1 ) -> {
			assertParameters( value1 );
		};
		Slot1< Byte > mustNotCallSlot = ( value1 ) -> {
			fail( "Slot must not be called!" );
		};

		Signal1< Byte > signal = new Signal1<>();

		signal.connect( mustCallSlot );
		signal.connect( mustNotCallSlot );
		signal.disconnect( mustNotCallSlot );

		signal.emit( EXPECTED_VALUE_1 );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testEmitToSignal()
	{
		// A Signal can be used as a Slot:

		Signal1< Byte > mustCallSignalSlot = new Signal1<>();
		Slot1< Byte > mustCallSlot = ( value1 ) -> {
			assertParameters( value1 );
		};
		mustCallSignalSlot.connect( mustCallSlot );

		Signal1< Byte > signal = new Signal1<>();
		signal.connect( mustCallSignalSlot );

		signal.emit( EXPECTED_VALUE_1 );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testHandleDisconnectingSlot()
	{
		// A Signal must be able to handle a Slot which disconnects while being called:

		Signal1< Byte > signal = new Signal1<>();

		Slot1< Byte > disconnectingSlot = new Slot1< Byte >() {
			@Override
			@SuppressWarnings("unused")
			public void call( Byte value ) throws Exception {
				signal.disconnect( this );
			}
		};
		Slot1< Byte > unimportantSlot1 = ( value1 ) -> {};
		Slot1< Byte > unimportantSlot2 = ( value1 ) -> {};

		signal.connect( disconnectingSlot );
		signal.connect( unimportantSlot2 );
		signal.connect( unimportantSlot1 );

		signal.emit( EXPECTED_VALUE_1 );
	}
}
