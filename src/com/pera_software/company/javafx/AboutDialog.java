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

package com.pera_software.company.javafx;

import com.pera_software.company.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class AboutDialog extends Dialog< ButtonType > {

	public AboutDialog( HostServices hostServices ) {

		Hyperlink hyperlink = new Hyperlink( PERA.FULL_DOMAIN_NAME );
		hyperlink.setOnAction( event -> hostServices.showDocument( hyperlink.getText() ));

		VBox vbox = new VBox();
		vbox.getChildren().addAll(
			new ImageView( PERA.logo() ),
			hyperlink
		);
		ButtonType okButtonType = new ButtonType( "OK", ButtonData.OK_DONE );
		getDialogPane().getButtonTypes().add( okButtonType );
		getDialogPane().setContent( vbox );
	}
}
