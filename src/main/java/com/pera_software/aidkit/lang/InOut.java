package com.pera_software.aidkit.lang;

/**
 * A class which can be used to return a value from a method via a parameter. The difference to
 * {@link Out} is that it needs to be initialized with a value.
 *
 * {@snippet :
 * public static boolean splitString(String s, char separator, InOut<String> before, InOut<String> after)
 * {
 *     int index = s.indexOf(separator);
 *     if (index >= 0) {
 *         before.set(s.substring(0, index));
 *         after.set(s.substring(index + 1));
 *     }
 *     return index >= 0;
 * }
 *
 * }
 *
 * @author P. Most
 * @see com.pera_software.aidkit.lang.Out
 */

public class InOut<T> extends Out<T> {

	public InOut(T value)
	{
		set(value);
	}
}
