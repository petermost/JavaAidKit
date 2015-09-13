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

import java.util.*;
import javafx.beans.property.*;
import javafx.scene.image.*;

/**
 * @author P. Most
 *
 */
public class LogMessage {
	private static final List< Image > IMAGES = Arrays.asList(  
		Images.loadImage( Images.TRACE ), 
		Images.loadImage( Images.DEBUG_2 ), 
		Images.loadImage( Images.INFO ), 
		Images.loadImage( Images.WARN ), 
		Images.loadImage( Images.ERROR ), 
		Images.loadImage( Images.ERROR )
	);

	private ReadOnlyObjectWrapper< Image > _image = new ReadOnlyObjectWrapper<>();
	private ReadOnlyObjectWrapper< LogLevel > _level = new ReadOnlyObjectWrapper<>();
	private ReadOnlyStringWrapper _name = new ReadOnlyStringWrapper();
	private ReadOnlyStringWrapper _text = new ReadOnlyStringWrapper();

	public LogMessage( LogLevel level, String name, String text ) {
		_image.set( IMAGES.get( level.ordinal() ));
		_level.set( level );
		_name.set( name );
		_text.set( text );

	}

	public final ReadOnlyObjectProperty< Image > imageProperty() {
		return _image.getReadOnlyProperty();
	}

	public final Image getImage() {
		return imageProperty().get();
	}

	public final ReadOnlyObjectProperty< LogLevel > levelProperty() {
		return _level.getReadOnlyProperty();
	}

	public final LogLevel getLevel() {
		return levelProperty().get();
	}

	public final ReadOnlyStringProperty nameProperty() {
		return _name.getReadOnlyProperty();
	}

	public final String getName() {
		return nameProperty().get();
	}

	public final ReadOnlyStringProperty textProperty() {
		return _text.getReadOnlyProperty();
	}

	public final String getText() {
		return textProperty().get();
	}
}
