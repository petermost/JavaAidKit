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

package com.pera_software.aidkit.signal;

import java.lang.reflect.*;
import static org.junit.Assert.*;

class MethodArgumentsAsserter implements InvocationHandler {
	private Method _expectedMethod;
	private int _callCounter = 0;
	private Object _expectedArguments[];

	public MethodArgumentsAsserter( Method expectedMethod, Object ... expectedArguments ) {
		_expectedMethod = expectedMethod;
		_expectedArguments = expectedArguments;
	}

	private void assertArguments( Object ... arguments ) {
		for ( int i = 0; arguments != null && i < arguments.length; ++i ) {
			Object argument = arguments[ i ];
			Object expectedArgument = _expectedArguments[ i ];

			assertEquals( expectedArgument, argument );
			assertEquals( expectedArgument.getClass(), argument.getClass() );
		}
	}

	@Override
	public Object invoke( Object proxy, Method method, Object arguments[] ) throws Throwable {
		if ( method.getName().equals( "equals" ) ) {
			return proxy == arguments[ 0 ];
		} else if ( method.equals( _expectedMethod ) ) {
			assertArguments( arguments );
			++_callCounter;
		}
		return null;
	}

	public int callCounter() {
		return _callCounter;
	}
}
