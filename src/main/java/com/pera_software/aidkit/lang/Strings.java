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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author P. Most
 *
 */
public final class Strings {

	public static final String EMPTY = "";

	public static final String EMPTY_ARRAY[] = new String[0];

	public static final List<String> EMPTY_LIST = new ArrayList<>();

	private Strings()
	{
	}

	public static String trimLeftIf(String s, Predicate<Character> condition)
	{
		for (int i = 0; i < s.length(); ++i) {
			if (!condition.test(Character.valueOf(s.charAt(i))))
				return s.substring(i);
		}
		return s;
	}

	public static String trimRightIf(String s, Predicate<Character> condition)
	{
		for (int i = s.length() - 1; i >= 0; --i) {
			if (!condition.test(Character.valueOf(s.charAt(i))))
				return s.substring(0, i + 1);
		}
		return s;
	}

	public static String trimIf(String s, Predicate<Character> condition)
	{
		return trimRightIf(trimLeftIf(s, condition), condition);
	}

	public static Optional<String> optionalOf(String value)
	{
		return Optional.ofNullable(value).filter(Predicate.not(String::isBlank));
	}
}
