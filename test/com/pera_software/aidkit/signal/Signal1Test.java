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

public class Signal1Test extends SignalTest
{
	//==============================================================================================

	public Signal1Test()
		throws Exception
	{
		super( Signal1.class, Slot1.class, Byte.class );
	}

	//==============================================================================================

	@Test
	@SuppressWarnings("static-method")
	public void testHandleDisconnectingSlot()
		throws Exception
	{
		// A Signal must be able to handle a Slot which disconnects while being called:

		Signal1< Byte > signal = new Signal1<>();

		Slot1< Byte > disconnectingSlot = new Slot1< Byte >() {
			@Override
			@SuppressWarnings("unused")
			public void handle( Byte value ) throws Exception {
				signal.disconnect( this );
			}
		};
		Slot1< Byte > unimportantSlot1 = ( value1 ) -> {};
		Slot1< Byte > unimportantSlot2 = ( value1 ) -> {};

		signal.connect( disconnectingSlot );
		signal.connect( unimportantSlot2 );
		signal.connect( unimportantSlot1 );

		signal.emit( EXPECTED_ARGUMENT_1 );
	}
}
