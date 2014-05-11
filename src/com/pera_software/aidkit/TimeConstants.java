package com.pera_software.aidkit;

public final class TimeConstants
{
	private TimeConstants()
	{}

	private static final int MAX_MILLISECONDS = 1000;
	private static final int MAX_SECONDS      = 60;
	private static final int MAX_MINUTES      = 60;
	private static final int MAX_HOURS        = 24;

	public static final long MILLISECONDS_PER_SECOND = MAX_MILLISECONDS;
	public static final long MILLISECONDS_PER_MINUTE = MILLISECONDS_PER_SECOND * MAX_SECONDS;
	public static final long MILLISECONDS_PER_HOUR   = MILLISECONDS_PER_MINUTE * MAX_MINUTES;
	public static final long MILLISECONDS_PER_DAY    = MILLISECONDS_PER_HOUR * MAX_HOURS;

}
