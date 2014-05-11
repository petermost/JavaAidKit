package com.pera_software.aidkit.signal;

public class Signal2< P1, P2 >  extends SignalBase< Slot2< P1, P2 >> implements Slot2< P1, P2 >
{
	public void emit( P1 value1, P2 value2 )
	{
		forEach(( slot ) -> slot.call( value1, value2 ));
	}

	@Override
	@SuppressWarnings("unused")
	public void call( P1 value1, P2 value2 )
		throws Exception
	{
		emit( value1, value2 );
	}
}
