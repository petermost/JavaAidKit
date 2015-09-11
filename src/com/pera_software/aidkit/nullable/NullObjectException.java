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

package com.pera_software.aidkit.nullable;

/**
 * Represents a null object and is basically the same as a null pointer exception but as a checked
 * exception.
 * @author P. Most
 */
public class NullObjectException extends Exception {

	public NullObjectException() {
	}

	public NullObjectException( String message ) {
		super( message );
	}

	public NullObjectException( Throwable cause ) {
		super( cause );
	}

	public NullObjectException( String message, Throwable cause ) {
		super( message, cause );
	}

	public NullObjectException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super( message, cause, enableSuppression, writableStackTrace );
	}
}
