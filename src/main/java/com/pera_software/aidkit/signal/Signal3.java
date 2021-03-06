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

//##################################################################################################

public class Signal3< P1, P2, P3 > extends SignalBase< Slot3< P1, P2, P3 >> implements Slot3< P1, P2, P3 >
{
	//==============================================================================================

	public void emit( P1 parameter1, P2 parameter2, P3 parameter3 )
		throws Exception
	{
		forEach( slot -> slot.handle( parameter1, parameter2, parameter3 ));
	}

	//==============================================================================================

	@Override
	public void handle( P1 parameter1, P2 parameter2, P3 parameter3 )
		throws Exception
	{
		emit( parameter1, parameter2, parameter3 );
	}
}
