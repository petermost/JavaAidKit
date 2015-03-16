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

package com.pera_software.aidkit.javafx.scene;

import org.controlsfx.control.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * @author P. Most
 *
 */
public class MainScene extends Scene {

	private Stage _stage;
	private MenuBar _menuBar;
	private StatusBar _statusBar;
	
	@SuppressWarnings( "null" )
	public MainScene( Stage stage ) {
		super( new BorderPane() );
		_stage = stage;
	}
	
	public void setWindowTitle( String title ) {
		_stage.setTitle( title );
	}
	
	public void setWindowIcon( Image icon ) {
		_stage.getIcons().add( icon );
	}
	
	@SuppressWarnings({ "null" })
	private BorderPane borderPane() {
		return ( BorderPane )getRoot();
	}
	
	@SuppressWarnings({ "null", "unused" })
	public MenuBar menuBar() {
		if ( _menuBar == null ) {
			_menuBar = new MenuBar();
			borderPane().setTop( _menuBar );
		}
		return _menuBar;
	}
	
	@SuppressWarnings({ "null", "unused" })
	public StatusBar statusBar() {
		if ( _statusBar == null ) {
			_statusBar = new StatusBar();
			borderPane().setBottom( _statusBar );
		}
		return _statusBar;
	}
	
	public void setCentralNode( Node node ) {
		borderPane().setCenter( node );
	}

	public void show() {
		_stage.setScene( this );
		_stage.show();
	}
}
