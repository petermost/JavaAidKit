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

package com.pera_software.aidkit.system;

import java.util.*;

//##################################################################################################
/**
 * Wrap the call to System.getProperty to avoid failing calls because of mistyped key names.
 */
public final class SystemProperties {

	public static final String OS_ARCHITECTURE_32 = "x86";
	public static final String OS_ARCHITECTURE_64 = "amd64";

	//==============================================================================================

	private SystemProperties() 
	{
	}

	//==============================================================================================

	public static Optional<String> getProperty(String key)
	{
		return Optional.ofNullable(System.getProperty(key));
	}

	//==============================================================================================

	public static Optional<String> getClassPath()
	{
		return getProperty("java.class.path");
	}

	//==============================================================================================
	/**
	 * @return ":" under UNIX and ";" under Windows
	 */
	public static Optional<String> getPathSeparator()
	{
		return getProperty("path.separator");
	}

	//==============================================================================================
	/**
	 * @return "/" under UNIX and "\" under Windows
	 */
	public static Optional<String> getFileSeparator()
	{
		return getProperty("file.separator");
	}

	//==============================================================================================
	/**
	 * @return "\n" under UNIX and "\r\n" under Windows
	 */
	public static Optional<String> getLineSeparator()
	{
		return getProperty("line.separator");
	}

	//==============================================================================================

	public static Optional<String> getTmpDir()
	{
		return getProperty("java.io.tmpdir");
	}

	//==============================================================================================
	/**
	 * @return The current working directory
	 */
	public static Optional<String> getUserDirectory()
	{
		return getProperty("user.dir");
	}

	//==============================================================================================

	/**
	 * @return 'x86' for 32-bit JVM and 'amd64' for an 64-bit JVM:
	 */
	public static Optional<String> getOsArchitecture()
	{
		return getProperty("os.arch");
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

// Another output under Linux:

//'java.specification.version' = '15'
//'sun.management.compiler' = 'HotSpot 64-Bit Tiered Compilers'
//'sun.jnu.encoding' = 'UTF-8'
//'java.runtime.version' = '15.0.1+9'
//'java.class.path' = '/home/peter/EclipseWorkspace/JavaAidKit/target/test-classes:/home/peter/EclipseWorkspace/JavaAidKit/target/classes:/home/peter/.m2/repository/net/java/dev/jna/jna-platform/5.6.0/jna-platform-5.6.0.jar:/home/peter/.m2/repository/net/java/dev/jna/jna/5.6.0/jna-5.6.0.jar:/home/peter/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.7.0/junit-jupiter-api-5.7.0.jar:/home/peter/.m2/repository/org/apiguardian/apiguardian-api/1.1.0/apiguardian-api-1.1.0.jar:/home/peter/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/home/peter/.m2/repository/org/junit/platform/junit-platform-commons/1.7.0/junit-platform-commons-1.7.0.jar:/home/peter/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.7.0/junit-jupiter-engine-5.7.0.jar:/home/peter/.m2/repository/org/junit/platform/junit-platform-engine/1.7.0/junit-platform-engine-1.7.0.jar:/home/peter/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.7.0/junit-jupiter-params-5.7.0.jar:/home/peter/.m2/repository/org/mockito/mockito-core/3.6.28/mockito-core-3.6.28.jar:/home/peter/.m2/repository/net/bytebuddy/byte-buddy/1.10.18/byte-buddy-1.10.18.jar:/home/peter/.m2/repository/net/bytebuddy/byte-buddy-agent/1.10.18/byte-buddy-agent-1.10.18.jar:/home/peter/.m2/repository/org/objenesis/objenesis/3.1/objenesis-3.1.jar:/home/peter/.m2/repository/org/hamcrest/java-hamcrest/2.0.0.0/java-hamcrest-2.0.0.0.jar:/home/peter/.m2/repository/org/junit/platform/junit-platform-launcher/1.7.0/junit-platform-launcher-1.7.0.jar:/home/peter/Tools/eclipse/configuration/org.eclipse.osgi/214/0/.cp:/home/peter/Tools/eclipse/configuration/org.eclipse.osgi/212/0/.cp'
//'user.name' = 'peter'
//'java.vm.vendor' = 'AdoptOpenJDK'
//'path.separator' = ':'
//'sun.arch.data.model' = '64'
//'os.version' = '5.8.0-34-generic'
//'java.runtime.name' = 'OpenJDK Runtime Environment'
//'file.encoding' = 'UTF-8'
//'java.vendor.url' = 'https://adoptopenjdk.net/'
//'java.vm.name' = 'OpenJDK 64-Bit Server VM'
//'java.vm.specification.version' = '15'
//'os.name' = 'Linux'
//'java.vendor.version' = 'AdoptOpenJDK'
//'sun.java.launcher' = 'SUN_STANDARD'
//'user.country' = 'US'
//'sun.boot.library.path' = '/usr/lib/jvm/adoptopenjdk-15-hotspot-amd64/lib'
//'sun.java.command' = 'org.eclipse.jdt.internal.junit.runner.RemoteTestRunner -version 3 -port 35933 -testLoaderClass org.eclipse.jdt.internal.junit5.runner.JUnit5TestLoader -loaderpluginname org.eclipse.jdt.junit5.runtime -packageNameFile /tmp/packageNames6897899698269738726.txt'
//'java.vendor.url.bug' = 'https://github.com/AdoptOpenJDK/openjdk-support/issues'
//'java.io.tmpdir' = '/tmp'
//'jdk.debug' = 'release'
//'sun.cpu.endian' = 'little'
//'java.version' = '15.0.1'
//'user.home' = '/home/peter'
//'user.dir' = '/home/peter/EclipseWorkspace/JavaAidKit'
//'os.arch' = 'amd64'
//'user.language' = 'en'
//'java.specification.vendor' = 'Oracle Corporation'
//'java.vm.specification.name' = 'Java Virtual Machine Specification'
//'java.version.date' = '2020-10-20'
//'java.home' = '/usr/lib/jvm/adoptopenjdk-15-hotspot-amd64'
//'file.separator' = '/'
//'java.vm.compressedOopsMode' = '32-bit'
//'java.library.path' = '/usr/java/packages/lib:/usr/lib64:/lib64:/lib:/usr/lib'
//'java.vm.info' = 'mixed mode, sharing'
//'java.vm.specification.vendor' = 'Oracle Corporation'
//'java.specification.name' = 'Java Platform API Specification'
//'java.vendor' = 'AdoptOpenJDK'
//'java.vm.version' = '15.0.1+9'
//'sun.io.unicode.encoding' = 'UnicodeLittle'
//'java.class.version' = '59.0'

