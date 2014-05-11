package com.pera_software.aidkit.signal;

public interface Slot2< T1, T2 > extends Slot
{
	void call( T1 value1, T2 value2 )
		throws Exception;
}
