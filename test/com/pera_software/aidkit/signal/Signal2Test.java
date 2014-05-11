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

import org.junit.*;
import static org.junit.Assert.*;

//##################################################################################################

public class Signal2Test extends SignalTest
{
	//==============================================================================================

	public Signal2Test()
	{
		super( Signal2.class, Slot2.class, Byte.class, Long.class );
	}
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
			@SuppressWarnings( "unused" )
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
