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

public class Signal6< P1, P2, P3, P4, P5, P6 > extends SignalBase< Slot6< P1, P2, P3, P4, P5, P6 >> implements Slot6< P1, P2, P3, P4, P5, P6 >
{
	//==============================================================================================

	public void emit( P1 parameter1, P2 parameter2, P3 parameter3, P4 parameter4, P5 parameter5, P6 parameter6 )
		throws Exception
	{
		forEach( slot -> slot.handle( parameter1, parameter2, parameter3, parameter4, parameter5, parameter6 ));
	}

	//==============================================================================================

	@Override
	public void handle( P1 parameter1, P2 parameter2, P3 parameter3, P4 parameter4, P5 parameter5, P6 parameter6 )
		throws Exception
	{
		emit( parameter1, parameter2, parameter3, parameter4, parameter5, parameter6 );
	}
}
