package com.pera_software.aidkit;

import java.io.*;

public final class Console
{
	private static void print( PrintStream outputStream, String format, Object arguments[] )
	{
		String message = String.format( format, arguments );
		outputStream.format( "%s\n", message );
	}

	public static void printStatus( String format, Object ... arguments )
	{
		print( System.out, format, arguments );
	}

	public static void printError( String format, Object ... arguments )
	{
		print( System.err, format, arguments );
	}

	private Console() {}
}
