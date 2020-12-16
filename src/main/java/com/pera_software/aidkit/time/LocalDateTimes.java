package com.pera_software.aidkit.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class LocalDateTimes {

	private LocalDateTimes() 
	{
	}

	public static LocalDateTime ofMilliseconds(long milliseconds)
	{
		return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
		// return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), TimeZone.getDefault().toZoneId());
	}
}
