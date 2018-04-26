// Copyright 2018 Peter Most, PERA Software Solutions GmbH
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

package com.pera_software.aidkit.java.beans;

import java.util.function.Function;

/**
 * @author P. Most
 *
 */
public final class Properties {

    private Properties() {
    }

    @SuppressWarnings( "unchecked" )
	public static <T1, T2, T3, T4> T4 getNonNullOrDefault(T1 instance,
            Function<T1, T2> getter1,
            Function<T2, T3> getter2,
            Function<T3, T4> getter3, T4 defaultValue) {

    	return (T4)doGetNonNullOrDefault(instance, new Function[] { getter1, getter2, getter3 }, defaultValue);
    }

    @SuppressWarnings( "unchecked" )
	public static <T1, T2, T3> T3 getNonNullOrDefault(T1 instance,
            Function<T1, T2> getter1,
            Function<T2, T3> getter2, T3 defaultValue) {

    	return (T3)doGetNonNullOrDefault(instance, new Function[] { getter1, getter2 }, defaultValue);
    }


    @SuppressWarnings( "unchecked" )
	public static <T1, T2> T2 getNonNullOrDefault(T1 instance,
            Function<T1, T2> getter1, T2 defaultValue) {

    	return (T2)doGetNonNullOrDefault(instance, new Function[] { getter1 }, defaultValue);
    }

    public static <T> T getNonNullOrDefault(T value, T defaultValue) {
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object doGetNonNullOrDefault(Object instance, Function getters[], Object defaultValue) {
    	Object result = defaultValue;
		for (Function getter : getters) {
			if (instance != null) {
				result = getter.apply(instance);
				if (result != null)
					instance = result;
				else
					return defaultValue;
			}
		}
    	return result;
    }
}