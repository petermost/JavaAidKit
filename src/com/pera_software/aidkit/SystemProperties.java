package com.pera_software.aidkit;

//##################################################################################################

public class SystemProperties
{
	private static final String CLASS_PATH_KEY     = "java.class.path";
	private static final String PATH_SEPARATOR_KEY = "path.separator";
	private static final String FILE_SEPARATOR_KEY = "file.separator";
	private static final String LINE_SEPARATOR_KEY = "line.separator";
	private static final String TMP_DIR_KEY        = "java.io.tmpdir";
	private static final String USER_DIR_KEY       = "user.dir";

	//===========================================================================

	public static String setClassPath( String classPath )
	{
		return System.setProperty( CLASS_PATH_KEY, classPath );
	}

	//===========================================================================

	public static String getClassPath()
	{
		return System.getProperty( CLASS_PATH_KEY );
	}

	//===========================================================================
	/**
	 * @return ":" under UNIX and ";" under Windows
	 */
	public static String getPathSeparator()
	{
		return System.getProperty( PATH_SEPARATOR_KEY );
	}

	//===========================================================================
	/**
	 * @return "/" under UNIX and "\" under Windows
	 */
	public static String getFileSeparator()
	{
	return System.getProperty( FILE_SEPARATOR_KEY );
	}

	//===========================================================================
	/**
	 * @return "\n" under UNIX and "\r\n" under Windows
	 */
	public static String getLineSeparator()
	{
		return System.getProperty( LINE_SEPARATOR_KEY );
	}

	//===========================================================================

	public static String getTmpDir()
	{
		return System.getProperty( TMP_DIR_KEY );
	}

	//===========================================================================

	public static String getCurrentWorkingDirectory()
	{
		return System.getProperty( USER_DIR_KEY );
	}
}
