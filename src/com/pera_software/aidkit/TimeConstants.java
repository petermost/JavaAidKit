// Copyright 2014 Peter Most, PERA Software Solutions GmbH
//
// This file is part of JavaAidKit.
//
// JavaAidKit is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// JavaAidKit is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit.  If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit;

public final class TimeConstants
{
	private TimeConstants()
	{}

	private static final int MAX_MILLISECONDS = 1000;
	private static final int MAX_SECONDS      = 60;
	private static final int MAX_MINUTES      = 60;
	private static final int MAX_HOURS        = 24;

	public static final long MILLISECONDS_PER_SECOND = MAX_MILLISECONDS;
	public static final long MILLISECONDS_PER_MINUTE = MILLISECONDS_PER_SECOND * MAX_SECONDS;
	public static final long MILLISECONDS_PER_HOUR   = MILLISECONDS_PER_MINUTE * MAX_MINUTES;
	public static final long MILLISECONDS_PER_DAY    = MILLISECONDS_PER_HOUR * MAX_HOURS;

}
