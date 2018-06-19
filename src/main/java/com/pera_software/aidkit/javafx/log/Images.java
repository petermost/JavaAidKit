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

package com.pera_software.aidkit.javafx.log;

import javafx.scene.image.*;

/**
 * @author P. Most
 *
 */
public final class Images {
	public static final String DEBUG_1 = "pmost-debug.png";
	public static final String DEBUG_2 = "eclipse-debug.png";
	public static final String TRACE = "baby-feet-black-icon.png";
	public static final String INFO = "dialog-information-16x16.png";
	public static final String WARN = "dialog-warning-16x16.png";
	public static final String ERROR = "dialog-error-16x16.png";

	public static Image loadImage( String imageName ) {
		return new Image( Images.class.getResourceAsStream( imageName ));
	}

	private Images() {
	}
}
