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

package com.pera_software.aidkit.fx.log;

import javafx.beans.property.*;
import javafx.scene.image.*;

/**
 * @author P. Most
 *
 */
public class LogMessage
{
	private static final Image DEBUG_IMAGE = new Image( LogMessage.class.getResourceAsStream( "Debug.png" ));
	private static final Image TRACE_IMAGE = DEBUG_IMAGE;
	private static final Image INFO_IMAGE = new Image( LogMessage.class.getResourceAsStream( "dialog-information-16x16.png" ));
	private static final Image WARN_IMAGE = new Image( LogMessage.class.getResourceAsStream( "dialog-warning-16x16.png" ));
	private static final Image ERROR_IMAGE = new Image( LogMessage.class.getResourceAsStream( "dialog-error-16x16.png" ));
	private static final Image FATAL_IMAGE = ERROR_IMAGE;

	private static final Image IMAGES[] = {
		TRACE_IMAGE, DEBUG_IMAGE, INFO_IMAGE, WARN_IMAGE, ERROR_IMAGE, FATAL_IMAGE
	};

	private ReadOnlyObjectWrapper< Image > _icon = new ReadOnlyObjectWrapper<>();
	private ReadOnlyObjectWrapper< LogLevel > _level = new ReadOnlyObjectWrapper<>();
	private ReadOnlyStringWrapper _name = new ReadOnlyStringWrapper();
	private ReadOnlyStringWrapper _text = new ReadOnlyStringWrapper();

	public LogMessage( LogLevel level, String name, String text )
	{
		_icon.set( IMAGES[ level.ordinal() ]);
		_level.set( level );
		_name.set( name );
		_text.set( text );

	}

	public final ReadOnlyObjectProperty< Image > iconProperty()
	{
		return _icon.getReadOnlyProperty();
	}

	public final Image getIcon()
	{
		return iconProperty().get();
	}

	public final ReadOnlyObjectProperty< LogLevel > levelProperty()
	{
		return _level.getReadOnlyProperty();
	}

	public final LogLevel getLevel()
	{
		return levelProperty().get();
	}

	public final ReadOnlyStringProperty nameProperty()
	{
		return _name.getReadOnlyProperty();
	}

	public final String getName()
	{
		return nameProperty().get();
	}

	public final ReadOnlyStringProperty textProperty()
	{
		return _text.getReadOnlyProperty();
	}

	public final String getText()
	{
		return textProperty().get();
	}
}
