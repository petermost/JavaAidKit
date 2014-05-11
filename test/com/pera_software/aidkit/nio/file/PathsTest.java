package com.pera_software.aidkit.nio.file;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;

public class PathsTest
{
	@Test
	@SuppressWarnings("static-method")
	public void testRemoveOverlaps()
	{
		final List< Path > expectedDirs = Arrays.asList(
			java.nio.file.Paths.get( "c:\\dir1" ),
			java.nio.file.Paths.get( "c:\\single"
		));
		List< Path > overlappedDirs = new ArrayList<>();
		overlappedDirs.add( java.nio.file.Paths.get( "c:\\dir1\\dir2\\dir3" ));
		overlappedDirs.add( java.nio.file.Paths.get( "c:\\dir1" ));
		overlappedDirs.add( java.nio.file.Paths.get( "c:\\dir1\\dir2" ));
		overlappedDirs.add( java.nio.file.Paths.get( "c:\\single" ));

		List< Path > actualDirs = Paths.removeOverlaps( overlappedDirs );
		assertThat( actualDirs, is( expectedDirs ));
	}
}
