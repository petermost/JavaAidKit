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

package com.pera_software.aidkit.javafx.scene.control.cell;

import java.util.*;
import javafx.beans.value.*;
import javafx.scene.image.*;

/**
 * @author P. Most
 *
 */
public class ImageTextCellValue extends ObservableValueBase< ImageTextCellValue >{
	private Image _image;
	private String _text;

	public ImageTextCellValue( Image image, String text ) {
		Objects.requireNonNull( image );
		Objects.requireNonNull( text );

		_image = image;
		_text = text;
	}

	public Image getImage() {
		return _image;
	}

	public String getText() {
		return _text;
	}

	@Override
	public ImageTextCellValue getValue() {
		return this;
	}
}
