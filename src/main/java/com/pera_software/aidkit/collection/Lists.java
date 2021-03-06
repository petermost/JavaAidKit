// Copyright 2014 Peter Most, PERA Software Solutions GmbH
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

package com.pera_software.aidkit.collection;

import java.util.*;

//##################################################################################################

public final class Lists {

	//==============================================================================================

	private Lists() {
	}

	//==============================================================================================

	public static < T > List< T > removeDuplicates( List< T > list ) {
		Set< T > uniques = new TreeSet<>();

		uniques.addAll( list );

		return new ArrayList<>( uniques );
	}

	//==============================================================================================

	public static < T > List< T > findDuplicates( List< T > list ) {
		List< T > duplicates = new ArrayList<>();
		Set< T > uniques = new TreeSet<>();

		list.forEach( s -> {
			if ( !uniques.add( s ))
				duplicates.add( s );
		});
		return duplicates;
	}

	//==============================================================================================
	
	@SafeVarargs
	public static < T > List< T > addAll( List< T > list, T ...array ) {
		for ( T t : array )
			list.add( t );
		
		return list;
	}

	//==============================================================================================
	
	public static < T > T getOrDefault( List< T > list, int index, T defaultValue ) {
		return ( index >= 0 && index < list.size() ) ? list.get( index ) : defaultValue;
	}
	
}
