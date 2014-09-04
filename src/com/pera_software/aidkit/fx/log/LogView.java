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

import com.pera_software.aidkit.fx.scene.control.cell.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.*;

/**
 * @author P. Most
 *
 */
public class LogView extends TableView< LogMessage >
{
	public LogView()
	{
		super();

		init();
	}

	public LogView( ObservableList< LogMessage > items )
	{
		super( items );

		init();
	}

	private void init()
	{
		createColumns();
	}

	@SuppressWarnings({ "unchecked" })
	private void createColumns()
	{
		TableColumn< LogMessage, Image > iconColumn = new TableColumn<>();
//		iconColumn.setSortable( false );
		iconColumn.setCellFactory( new ImageTableCellFactory< LogMessage >() );
		iconColumn.setCellValueFactory(( CellDataFeatures< LogMessage, Image > p ) -> p.getValue().iconProperty() );

		TableColumn< LogMessage, LogLevel > levelColumn = new TableColumn<>( "Level" );
//		levelColumn.setSortable( false );
		levelColumn.setCellValueFactory(( CellDataFeatures< LogMessage, LogLevel > p ) -> p.getValue().levelProperty() );

		TableColumn< LogMessage, String > nameColumn = new TableColumn< >( "Name" );
//		nameColumn.setSortable( false );
		nameColumn.setCellValueFactory(( CellDataFeatures< LogMessage, String > p ) -> p.getValue().nameProperty() );

		TableColumn< LogMessage, String > textColumn = new TableColumn< >( "Text" );
//		textColumn.setSortable( false );
		textColumn.setCellValueFactory(( CellDataFeatures< LogMessage, String > p ) -> p.getValue().textProperty() );
		// textColumn.prefWidthProperty().bind( textColumn.textProperty().length() );

		getColumns().addAll( iconColumn, levelColumn, nameColumn, textColumn );

		// setColumnResizePolicy(( ResizeFeatures features ) -> Boolean.TRUE );
	}


}
