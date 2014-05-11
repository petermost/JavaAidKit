package com.pera_software.aidkit.signal;

public class Signal0 extends SignalBase< Slot0 > implements Slot0
{
	public void emit()
	{
		forEach(( slot ) -> slot.call() );
	}

	@Override
	@SuppressWarnings("unused")
	public void call() throws Exception
	{
		emit();
	}
}
