// Copyright 2015 Peter Most, PERA Software Solutions GmbH
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
import javafx.scene.layout.*;
import javafx.scene.paint.*;

/**
 * @author P. Most
 *
 */
public class MainScene extends Scene {

	private MenuBar _menuBar;
	private StatusBar _statusBar;
	private BorderPane _borderPane;

	public MainScene() {
		super( new BorderPane() );
		setupMenuAndStatusBar();
	}

	public MainScene( Paint fill ) {
		super( new BorderPane(), fill );
		setupMenuAndStatusBar();
	}

	public MainScene( double width, double height ) {
		super( new BorderPane(), width, height );
		setupMenuAndStatusBar();
	}

	public MainScene( double width, double height, Paint fill ) {
		super( new BorderPane(), width, height, fill );
		setupMenuAndStatusBar();
	}

	public MainScene( double width, double height, boolean depthBuffer ) {
		super( new BorderPane(), width, height, depthBuffer );
		setupMenuAndStatusBar();
	}

	public MainScene( double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing ) {
		super( new BorderPane(), width, height, depthBuffer, antiAliasing );
		setupMenuAndStatusBar();
	}
	
	private void setupMenuAndStatusBar() {
	
		// We setup the border pane immediately because if we do it lazily then the height calculation
		// from the menu bar gets messed up.
		
		_menuBar = new MenuBar();
		_statusBar = new StatusBar();
		_borderPane = ( BorderPane )getRoot();
		
		_borderPane.setTop( _menuBar );
		_borderPane.setBottom( _statusBar );
	}
	
	public MenuBar menuBar() {
		return _menuBar;
	}
	
	public StatusBar statusBar() {
		return _statusBar;
	}
	
	public void setCentralNode( Node node ) {
		_borderPane.setCenter( node );
	}
}
