// Copyright 2015 Peter Most, PERA Software Solutions GmbH
//
// This file is part of the JavaAidKit library.
//
// JavaAidKit is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// JavaAidKit is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.lang;

import static com.pera_software.aidkit.lang.Exceptions.tryFunction;
import static com.pera_software.aidkit.lang.Exceptions.tryProcedure;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.pera_software.aidkit.util.function.Function;

/**
 * @author P. Most
 *
 */
public class ExceptionsTest {

	private static class TestException extends Exception {
	}
	
	private void throwingProcedure() throws Exception {
		throw new TestException();
	}
	
	private void nonThrowingProcedure() throws Exception {
	}
	
	private String throwingFunction() throws Exception {
		throw new TestException();
	}
	
	private String nonThrowingFunction() throws Exception {
		return "Text";
	}
	
	
	@Test
	public void testTryProcedureWithThrowingProcedure() {
		assertThrows(Error.class, () -> tryProcedure( this::throwingProcedure ));
	}

	@Test
	public void testTryProcedureWithNonThrowingProcedure() {
		tryProcedure( this::nonThrowingProcedure );
	}
	
	@Test
	public void testTryFunctionWithThrowingFunction() {
		Function< String > function = this::throwingFunction;
		assertThrows(Error.class, () -> tryFunction( function ));
	}
	
	@Test
	public void testTryFunctionWithNonThrowingFunction() {
		Function< String > function = this::nonThrowingFunction;
		String result = tryFunction( function );
		assertEquals( "Text", result );
	}
}
