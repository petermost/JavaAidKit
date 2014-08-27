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

package com.pera_software.aidkit.fx;

import javafx.scene.*;
import javafx.scene.control.*;

public class Label extends javafx.scene.control.Label
{
	// These are just forwarding constructor:

	public Label()
	{
		super();

		setDefaults( null );
	}

	public Label( String text )
	{
		super( text );

		setDefaults( null );
	}


	public Label( String text, Node graphic )
	{
		super( text, graphic );

		setDefaults( null );
	}

	// These are the enhanced constructors which allow you to immediately label a control:

	public Label( String text, Control labeledControl )
	{
		super( text );

		setDefaults( labeledControl );
	}

	public Label( String text, Node graphic, Control labeledControl )
	{
		super( text, graphic );

		setDefaults( labeledControl );
	}

	private void setDefaults( Control labeledControl  )
	{
		setMnemonicParsing( true );

		if ( labeledControl != null )
			setLabelFor( labeledControl );
	}
}
