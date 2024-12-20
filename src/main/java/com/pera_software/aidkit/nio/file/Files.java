package com.pera_software.aidkit.nio.file;

import static java.nio.file.StandardCopyOption.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.time.Duration;
import com.pera_software.aidkit.lang.Out;
import com.pera_software.aidkit.lang.Strings;
import com.pera_software.aidkit.lang.Threads;

public final class Files {

	private Files()
	{
	}

	public static long tryCopy(InputStream in, Path target, Out<Exception> exception)
	{
		try {
			return java.nio.file.Files.copy(in, target, REPLACE_EXISTING);
		} catch (IOException ioException) {
			exception.set(ioException);
			return -1;
		}
	}

	public static boolean tryDeleteIfExists(Path path, Out<Exception> exception)
	{
		try {
			java.nio.file.Files.deleteIfExists(path);
			return true;
		} catch (IOException ioException) {
			exception.set(ioException);
			return false;
		}
	}

	public static long waitUntilWritingFinished(Path path) throws Exception
	{
		final Duration POLLING_CYCLE = Duration.ofSeconds(1);

		// Wait until the file size doesn't change anymore and therefore writing has finished:

		for (;;) {
			var firstSize = java.nio.file.Files.size(path);
			Threads.sleep(POLLING_CYCLE);
			var secondSize = java.nio.file.Files.size(path);

			if (firstSize == secondSize)
				return firstSize;
			else
				Threads.sleep(POLLING_CYCLE);
		}
	}

	public static boolean compare(InputStreamReader expectedStream, InputStreamReader actualStream,
		Out<Integer> rowDifference, Out<Integer> columnDifference) throws IOException
	{
		var expectedReader = new BufferedReader(expectedStream);
		var actualReader = new BufferedReader(actualStream);

		for (int lineNumber = 0; ; ++lineNumber) {
			String expectedLine = expectedReader.readLine();
			String actualLine = actualReader.readLine();

			if (expectedLine == null && actualLine == null)
				return true;

			if (expectedLine != null && actualLine == null) {
				rowDifference.set(lineNumber + 1);
				return false;
			}
			if (expectedLine == null && actualLine != null) {
				rowDifference.set(lineNumber + 1);
				return false;
			}
			int lineIndex = Strings.compare(expectedLine, actualLine);
			if (lineIndex != -1) {
				rowDifference.set(lineNumber + 1);
				columnDifference.set(lineIndex + 1);
				return false;
			}
		}
	}

}
