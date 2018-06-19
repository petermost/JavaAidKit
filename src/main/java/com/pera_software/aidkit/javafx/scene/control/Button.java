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

package com.pera_software.aidkit.javafx.scene.control;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class Button extends javafx.scene.control.Button {
	private KeyCombination _accelerator = KeyCombination.NO_MATCH;

	public Button() {
		super();

		setDefaults();
	}

	public Button( String text ) {
		super( text );

		setDefaults();
	}

	public Button( String text, Node graphic ) {
		super( text, graphic );

		setDefaults();
	}

	private void setDefaults() {
		// Prevent the button from shrinking bellow its preferred size:

		setMinWidth( USE_PREF_SIZE );

		// Grow the button with its parent:

		setMaxWidth( Double.MAX_VALUE );
		HBox.setHgrow( this, Priority.ALWAYS );
	}

	public final void setAccelerator( KeyCombination accelerator ) {
		_accelerator = accelerator;

		Scene scene = getScene();
		if ( scene != null ) {
			scene.addEventHandler( KeyEvent.KEY_PRESSED, ( KeyEvent event ) -> {
				if ( _accelerator.match( event ) ) {
					arm();
				}
			} );
			scene.addEventHandler( KeyEvent.KEY_RELEASED, ( KeyEvent event ) -> {
				if ( _accelerator.match( event ) ) {
					fire();
					disarm();
				}
			} );
		}

	}

	public final KeyCombination getAccelerator() {
		return _accelerator;
	}
}
