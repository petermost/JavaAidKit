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

import org.junit.*;

//##################################################################################################

public class Signal2Test extends SignalTest
{
	//==============================================================================================

	public Signal2Test()
		throws Exception
	{
		super( Signal2.class, Slot2.class, EXPECTED_PARAMETER_1.getClass(), EXPECTED_ARGUMENT_2.getClass() );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testHandleDisconnectingSlot()
		throws Exception
	{
		// A Signal must be able to handle a Slot which disconnects while being called:

		Signal2< Byte, Long > signal = new Signal2<>();

		Slot2< Byte, Long > disconnectingSlot = new Slot2< Byte, Long >() {
			@Override
			@SuppressWarnings( "unused" )
			public void handle( Byte parameter1, Long parameter2 ) throws Exception {
				signal.disconnect( this );
			}
		};
		Slot2< Byte, Long > unimportantSlot1 = ( parameter1, parameter2 ) -> {};
		Slot2< Byte, Long > unimportantSlot2 = ( parameter1, parameter2 ) -> {};

		signal.connect( disconnectingSlot );
		signal.connect( unimportantSlot2 );
		signal.connect( unimportantSlot1 );

		signal.emit( EXPECTED_PARAMETER_1, EXPECTED_ARGUMENT_2 );
	}
}
