package com.pera_software.aidkit.signal;

public class Signal1< P1 > extends SignalBase< Slot1< P1 >> implements Slot1< P1 >
{
	public void emit( P1 value1 )
	{
		forEach(( slot ) -> slot.call( value1 ));
	}

	@Override
	@SuppressWarnings("unused")
	public void call( P1 value1 )
		throws Exception
	{
		emit( value1 );
	}
}
