package com.pera_software.aidkit.lang;

import java.util.*;

/**
 * A class which can be used to return a value from a method via a parameter.
 *
 * {@snippet :
 * public static boolean splitString(String s, char separator, Out<String> before, Out<String> after)
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
 * @see com.pera_software.aidkit.lang.InOut
 */
public class Out<T> {
	private T _value = null;

	/**
	 * No initializing constructor because {@code out} parameters are only supposed to be set in the
	 * called method.
	 */
	public Out()
	{
	}

	/**
	 * Sets the value if not null, otherwise throws a {@code NullPointerException}.
	 * @param value the non-null value to set
	 * @throws NullPointerException if value is null
	 */
	public void set(T value)
	{
		if (value != null)
			_value = value;
		else
			throw new NullPointerException();

	}

	/**
	 * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
	 * @throws NoSuchElementException if there is no value present
	 * @return the non-null value held
	 */
	public T get()
	{
		if (_value != null)
			return _value;
		else
			throw new NoSuchElementException();
	}

	@Override
	public String toString()
	{
		return _value != null ? _value.toString() : "";
	}

	public String toDebugString()
	{
		return String.format("Out<%s>: '%s'",
			_value != null ? _value.getClass().getSimpleName() : "null",
			_value != null ? _value : "null");
	}
}
