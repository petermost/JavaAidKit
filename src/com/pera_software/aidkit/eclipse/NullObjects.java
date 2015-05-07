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

package com.pera_software.aidkit.eclipse;

import java.util.function.*;
import org.eclipse.jdt.annotation.*;

/**
 * These methods are basically a copy of {@link java.util.Objects#requireNonNull} but enhanced with the eclipse
 * annotations for null analysis.
 * @see <a href="http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.user/tasks/task-using_null_annotations.htm#tips_analyzable">
 *      http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.user/tasks/task-using_null_annotations.htm#tips_analyzable</a>
 * @author P. Most
 */
public final class NullObjects {
	private NullObjects() {
	}
	
	@SuppressWarnings( "null" )
	public static< T > @NonNull T requireNonNull( @Nullable T obj ) {
		if ( obj != null )
			return obj;
		else
			throw new NullPointerException();
	}
	
	@SuppressWarnings( "null" )
	public static < T > @NonNull T requireNonNull( @Nullable T obj, @NonNull String message ) {
		if ( obj != null )
			return obj;
		else
			throw new NullPointerException( message );
	}

	@SuppressWarnings( "null" )
	public static < T > @NonNull T requireNonNull( @Nullable T obj, @NonNull Supplier< String > messageSupplier ) {
		if ( obj != null )
			return obj;
		else
			throw new NullPointerException( messageSupplier.get() );
	}
}
