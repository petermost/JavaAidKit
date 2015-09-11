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
import java.util.Arrays;
import java.util.regex.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import com.pera_software.aidkit.collection.*;

//##################################################################################################

public abstract class ProjectFileParser {
	
	private Path _projectFilePath;
	private Document _document;
	private XPath _xpath;

	//==============================================================================================

	public ProjectFileParser( Path projectFilePath ) throws Exception {
		_projectFilePath = projectFilePath;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Doesn't work with namespace awareness!
		// factory.setNamespaceAware( true );

		DocumentBuilder builder = factory.newDocumentBuilder();
		_document = builder.parse( _projectFilePath.toUri().toURL().openStream() );

		XPathFactory xpathFactory = XPathFactory.newInstance();
		_xpath = xpathFactory.newXPath();
	}

	//==============================================================================================

	public abstract List< String > findSourceFileNames() throws Exception;

	public abstract List< String > findOutputFileNames() throws Exception;

	public abstract List< String > findIntermediateDirectoryNames() throws Exception;

	public abstract List< String > findOutputDirectoryNames() throws Exception;

	public abstract String findTargetName() throws Exception;

	public abstract List< String > findPreBuildCommands() throws Exception;

	public abstract List< String > findPostBuildCommands() throws Exception;

	public abstract List< String > findTreatWarningsAsErrorsValues() throws Exception;
	
	public List< String > findCopyDirectoryNames() throws Exception {
		List< String > prePostBuildCommands = new ArrayList<>();

		prePostBuildCommands.addAll( findPreBuildCommands() );
		prePostBuildCommands.addAll( findPostBuildCommands() );

		return CopyCommandParser.findDestinationDirectoryNames( prePostBuildCommands );
	}

	public List< String > findProjectReferenceNames() throws Exception {
		return findXmlTags( "//ProjectReference/@Include" );
	}
	
	public List< String > findLibraryReferenceNames() throws Exception {
		return findXmlTags( "//Reference/@Include" );
	}
	
	public List< BuildConfiguration > findBuildConfigurations() throws Exception {
		// We don't search for '//ProjectConfiguration/Configuration' because this would only work
		// for C++ projects. This search returns:
		// C++: <'$(Configuration)|$(Platform)'=='Debug|Win32'>
		// C# : < '$(Configuration)|$(Platform)' == 'Debug|x86' >
		// without the <> of course. Watch out for the blanks!

		List< String > conditions = findXmlTags( "//PropertyGroup/@Condition" );
		conditions = Lists.removeDuplicates( conditions );

		// Extract the configuration- and platform name:

		List< BuildConfiguration > configurations = new ArrayList<>();
		Pattern conditionPattern = Pattern.compile( ".*'\\$\\(Configuration\\)\\|\\$\\(Platform\\)'.*==.*'(.*)\\|(.*)'.*" );
		conditions.forEach( condition -> {
			Matcher matcher = conditionPattern.matcher( condition );
			if ( matcher.matches() ) {
				String configurationName = matcher.group( 1 );
				String platformName = matcher.group( 2 );
				configurations.add( new BuildConfiguration( configurationName, platformName ));
			}
		});
		return configurations;
	}

	//==============================================================================================

	private NodeList findNodes( String nodeName ) throws Exception {
		XPathExpression xpathExpression = _xpath.compile( nodeName );
		NodeList nodes = ( NodeList )xpathExpression.evaluate( _document, XPathConstants.NODESET );
		return nodes;
	}

	//==============================================================================================

	protected List< String > findXmlTags( String nodeName ) throws Exception {
		List< String > texts = new ArrayList<>();
		NodeList nodes = findNodes( nodeName );
		if ( nodes != null ) {
			for ( int i = 0; i < nodes.getLength(); ++i ) {
				Node node = nodes.item( i );
				texts.add( node.getTextContent() );
			}
		}
		return texts;
	}

	//==============================================================================================

	protected List< String > findXmlLines( String nodeName ) throws Exception {
		List< String > xmlLines = new ArrayList<>();
		List< String > xmlTags = findXmlTags( nodeName );
		for ( String xmlTag : xmlTags ) {
			xmlLines.addAll( Arrays.asList( xmlTag.split( "\n" ) ) );
		}
		return xmlLines;
	}

	//==============================================================================================

	public Path path() {
		return _projectFilePath;
	}
}
