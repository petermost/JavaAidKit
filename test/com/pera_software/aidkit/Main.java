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

package com.pera_software.aidkit;

import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import java.lang.reflect.*;
import com.google.common.collect.*;
import com.google.common.reflect.*;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.pera_software.aidkit.lang.*;

public final class Main {

//	private static ImmutableSet< ClassInfo > getAllClasses() throws Exception {
//		ClassLoader classLoader = Main.class.getClassLoader();
//		ClassPath classPath = ClassPath.from( classLoader );
//		ImmutableSet< ClassInfo > classes = classPath.getAllClasses();
//		
//		return classes;
//	}
//	
//	private static boolean hasTestMethods( Class< ? > clazz ) {
//		Method methods[] = clazz.getMethods();
//		for ( Method method : methods ) {
//			if ( method.getAnnotation( Test.class ) != null ) {
//				return true;
//			}
//		}
//		return false;		
//	}
//	
//	public static void main( String x[] ) throws Exception {
//		
//		List< String > testClasses = new ArrayList<>();
//		ImmutableSet< ClassInfo > classes = getAllClasses();
//		for ( ClassInfo classInfo : classes ) {
//			try {
//				Class< ? > clazz = classInfo.load();
//				if ( !Classes.isAbstract( clazz ) && hasTestMethods( clazz )) {
//					String testClassName = classInfo.getName();
//					testClasses.add( testClassName );
//					System.out.printf( "Added test class '%s'\n", testClassName );
//				}
//			} catch ( Throwable exception ) {
//				System.err.printf( "Unable to load '%s' because '%s'!\n", classInfo.getName(), 
//					exception.toString() );
//			}
//		}
//		String testClassNames[] = new String[ testClasses.size() ];
//		testClassNames = testClasses.toArray( testClassNames );
//		JUnitCore.main( testClassNames );
//	}
}
