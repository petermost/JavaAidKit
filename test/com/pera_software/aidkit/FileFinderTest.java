package com.pera_software.aidkit;

import static org.junit.Assert.*;
import org.junit.*;

@SuppressWarnings("static-method")
public class FileFinderTest
{
	@Test
	public void testConvertWildcardPatternToRegularExpression()
	{
		String regularExpression = FileFinder.convertWildcardPatternToRegularExpression( "*.csproj" );
		assertEquals( ".*\\.csproj", regularExpression );
	}
}
