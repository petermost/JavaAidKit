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
// along with JavaAidKit.  If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.lang;

import static com.pera_software.aidkit.nullable.NullStrings.*;

//##################################################################################################
/**
 * Wrap the call to System.getProperty to avoid failing calls because of mistyped key names.
 */
public final class SystemProperties {
	
	public static final String OS_ARCHITECTURE_32 = "x86";
	public static final String OS_ARCHITECTURE_64 = "amd64";

	//==============================================================================================

	private SystemProperties() {
	}

	//==============================================================================================
	
	private static String getProperty( String key ) {
		return makeNonNull( System.getProperty( key ));
	}
	
	//==============================================================================================

	public static String getClassPath() {
		return getProperty( "java.class.path" );
	}

	//==============================================================================================
	/**
	 * @return ":" under UNIX and ";" under Windows
	 */
	public static String getPathSeparator() {
		return getProperty( "path.separator" );
	}

	//==============================================================================================
	/**
	 * @return "/" under UNIX and "\" under Windows
	 */
	public static String getFileSeparator() {
		return getProperty( "file.separator" );
	}

	//==============================================================================================
	/**
	 * @return "\n" under UNIX and "\r\n" under Windows
	 */
	public static String getLineSeparator() {
		return getProperty( "line.separator" );
	}

	//==============================================================================================

	public static String getTmpDir() {
		return getProperty( "java.io.tmpdir" );
	}

	//==============================================================================================
	/**
	 * @return The current working directory
	 */
	public static String getUserDirectory() {
		return getProperty( "user.dir" );
	}
	
	//==============================================================================================
	
	/**
	 * @return 'x86' for 32-bit JVM and 'amd64' for an 64-bit JVM:
	 */
	public static String getOsArchitecture() {
		return getProperty( "os.arch" );
	}
}

// This is a list of all system properties (printed with SystemPropertiesTest): If the value is
// missing, then it was user specific.
//
// 'java.runtime.name' = 'Java(TM) SE Runtime Environment'
// 'sun.boot.library.path' = 'C:\Program Files (x86)\Java\jdk8\jre\bin'
// 'java.vm.version' = '25.77-b03'
// 'user.country.format' = 'DE'
// 'java.vm.vendor' = 'Oracle Corporation'
// 'java.vendor.url' = 'http://java.oracle.com/'
// 'path.separator' = ';'
// 'java.vm.name' = 'Java HotSpot(TM) Client VM'
// 'file.encoding.pkg' = 'sun.io'
// 'user.country' = 'US'
// 'user.script' = ''
// 'sun.java.launcher' = 'SUN_STANDARD'
// 'sun.os.patch.level' = 'Service Pack 1'
// 'java.vm.specification.name' = 'Java Virtual Machine Specification'
// 'user.dir' = 'C:\Entwicklung_Java_Tools\JavaAidKit'
// 'java.runtime.version' = '1.8.0_77-b03'
// 'java.awt.graphicsenv' = 'sun.awt.Win32GraphicsEnvironment'
// 'java.endorsed.dirs' = 'C:\Program Files (x86)\Java\jdk8\jre\lib\endorsed'
// 'os.arch' = 'x86'
// 'java.io.tmpdir' = 'C:\temp\'
// 'line.separator'
// 'java.vm.specification.vendor' = 'Oracle Corporation'
// 'user.variant' = ''
// 'os.name' = 'Windows 7'
// 'sun.jnu.encoding' = 'Cp1252'
// 'java.library.path'
// 'java.specification.name' = 'Java Platform API Specification'
// 'java.class.version' = '52.0'
// 'sun.management.compiler' = 'HotSpot Client Compiler'
// 'os.version' = '6.1'
// 'user.home' = 'C:\Users\most'
// 'user.timezone' = ''
// 'java.awt.printerjob' = 'sun.awt.windows.WPrinterJob'
// 'file.encoding' = 'UTF-8'
// 'java.specification.version' = '1.8'
// 'java.class.path' = 'C:\Entwicklung_Java_Tools\JavaAidKit\bin;C:\Entwicklung_Java_Tools\JavaAidKit\lib\junit-4.12.jar;C:\Entwicklung_Java_Tools\JavaAidKit\lib\java-hamcrest-2.0.0.0.jar;C:\Entwicklung_Java_Tools\JavaAidKit\lib\controlsfx-8.20.8.jar;C:\Entwicklung_Java_Tools\JavaAidKit\lib\org.eclipse.jdt.annotation_2.0.100.v20150311-1658.jar;C:\Entwicklung_Java_Tools\JavaAidKit\lib\jna-4.2.1.jar;C:\Entwicklung_Java_Tools\JavaAidKit\lib\jna-platform-4.2.1.jar;/C:/Tools/eclipse-java-mars-1-win32/eclipse/configuration/org.eclipse.osgi/890/0/.cp/;/C:/Tools/eclipse-java-mars-1-win32/eclipse/configuration/org.eclipse.osgi/889/0/.cp/'
// 'user.name' = 'most'
// 'java.vm.specification.version' = '1.8'
// 'sun.java.command' = 'org.eclipse.jdt.internal.junit.runner.RemoteTestRunner -version 3 -port 50745 -testLoaderClass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader -loaderpluginname org.eclipse.jdt.junit4.runtime -test com.pera_software.aidkit.lang.SystemPropertiesTest:printAllProperties'
// 'java.home' = 'C:\Program Files (x86)\Java\jdk8\jre'
// 'sun.arch.data.model' = '32'
// 'user.language' = 'en'
// 'java.specification.vendor' = 'Oracle Corporation'
// 'user.language.format' = 'de'
// 'awt.toolkit' = 'sun.awt.windows.WToolkit'
// 'java.vm.info' = 'mixed mode'
// 'java.version' = '1.8.0_77'
// 'java.ext.dirs' = 'C:\Program Files (x86)\Java\jdk8\jre\lib\ext;C:\Windows\Sun\Java\lib\ext'
// 'sun.boot.class.path' = 'C:\Program Files (x86)\Java\jdk8\jre\lib\resources.jar;C:\Program Files (x86)\Java\jdk8\jre\lib\rt.jar;C:\Program Files (x86)\Java\jdk8\jre\lib\sunrsasign.jar;C:\Program Files (x86)\Java\jdk8\jre\lib\jsse.jar;C:\Program Files (x86)\Java\jdk8\jre\lib\jce.jar;C:\Program Files (x86)\Java\jdk8\jre\lib\charsets.jar;C:\Program Files (x86)\Java\jdk8\jre\lib\jfr.jar;C:\Program Files (x86)\Java\jdk8\jre\classes'
// 'java.vendor' = 'Oracle Corporation'
// 'file.separator' = '\'
// 'java.vendor.url.bug' = 'http://bugreport.sun.com/bugreport/'
// 'sun.io.unicode.encoding' = 'UnicodeLittle'
// 'sun.cpu.endian' = 'little'
// 'sun.desktop' = 'windows'
// 'sun.cpu.isalist' = 'pentium_pro+mmx pentium_pro pentium+mmx pentium i486 i386 i86'

