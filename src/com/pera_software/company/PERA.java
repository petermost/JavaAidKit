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

package com.pera_software.company;

import javafx.scene.image.*;

/**
 * @author P. Most
 *
 */
public final class PERA {

	public static final String NAME      = "PERA Software Solutions";
	public static final String FULL_NAME = NAME + " GmbH";
	public static final String STREET = "Ulrich-Nanshaimer-Straße 29";
	public static final String CITY = "85652 Landsham";

	public static final String PHONE = "089 / 909 692 48";
	public static final String FAX   = "089 / 909 692 49";
	
	public static final String DOMAIN_NAME = "PERA-Software.com";
	public static final String FULL_DOMAIN_NAME = "http://www." + DOMAIN_NAME;
	
	public static final String COPYRIGHT_LINE = "(c) by " + FULL_NAME;
	
	private static final String ICON_NAME = "PERA-Icon.png";
	private static final String LOGO_NAME = "PERA-Logo.png";
	
	private PERA() {
	}
	
	public static Image icon() {
		return new Image( PERA.class.getResourceAsStream( ICON_NAME ));	
	}
	
	public static Image logo() {
		return new Image( PERA.class.getResourceAsStream( LOGO_NAME ));
	}
}
