package com.pera_software.aidkit;

/**
 * Central class to query platform specific settings.
 */
public final class Platform
{
	public static String tempDirectory()
	{
		return System.getProperty( "java.io.tmpdir" );
	}

	public static String systemDrive()
	{
		return System.getenv( "SystemDrive" );
	}

	public static String programsDirectory()
	{
		return System.getenv( "ProgramFiles(x86)" );
	}

	public static String settingsDirectory()
	{
		return System.getenv( "AppData" );
	}

	public static String windowsDirectory()
	{
		return System.getenv( "windir" );
	}

	private Platform() { }

}
