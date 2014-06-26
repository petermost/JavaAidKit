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
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.visualstudio;

import java.nio.file.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

//##################################################################################################

public class ProjectFileParser
{
	private Document _document;
	private XPath _xpath;

	//==============================================================================================

	public ProjectFileParser( Path projectFilePath )
		throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Doesn't work with namespace awareness!
		// factory.setNamespaceAware( true );

		DocumentBuilder builder = factory.newDocumentBuilder();
		_document = builder.parse( projectFilePath.toString() );

		XPathFactory xpathFactory = XPathFactory.newInstance();
		_xpath = xpathFactory.newXPath();

	}

	//==============================================================================================

	public List< String > findXmlTags( String nodeName )
		throws Exception
	{
		List< String > texts = new ArrayList<>();
		XPathExpression xpathExpression = _xpath.compile( nodeName );
		NodeList nodes = ( NodeList )xpathExpression.evaluate( _document, XPathConstants.NODESET );
		if ( nodes != null ) {
			for ( int i = 0; i < nodes.getLength(); ++i )
				texts.add( nodes.item( i ).getTextContent() );
		}
		return texts;
	}
	//==============================================================================================

	public List< String > findXmlLines( String nodeName )
		throws Exception
	{
		List< String > xmlLines = new ArrayList<>();
		List< String > xmlTags = findXmlTags( nodeName );
		for ( String xmlTag : xmlTags ) {
			xmlLines.addAll( Arrays.asList( xmlTag.split( "\n" )));
		}
		return xmlLines;
	}
}
