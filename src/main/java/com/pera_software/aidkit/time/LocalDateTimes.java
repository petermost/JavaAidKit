package com.pera_software.aidkit.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public final class LocalDateTimes {

	private LocalDateTimes() 
	{
	}

	public static LocalDateTime ofMilliseconds(long milliseconds)
	{
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), TimeZone.getDefault().toZoneId());
	}
}
