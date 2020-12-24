#checkVatTecheckTinServicestService

#Create a new project

	mvn archetype:generate -Dfilter="org.apache.maven.archetypes:maven-archetype-quickstart" -DgroupId="com.hoffnungland" -DartifactId=checkTinService -Dpackage="com.hoffnungland.checkTinService" -Dversion="0.0.1"

#Build settings
##Add prerequisites

	<prerequisites>
		<maven>3.0.5</maven>
	</prerequisites>

Update to java 1.8<br>
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.source.version>1.8</java.source.version>
		<java.target.version>1.8</java.target.version>
	</properties>

wsimport of [WSDL](https://ec.europa.eu/taxation_customs/tin/checkTinService.wsdl) to check VAT Code

	<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>jaxws-maven-plugin</artifactId>
		<executions>
			<execution>
				<goals>
					<goal>wsimport</goal>
				</goals>
			</execution>
		</executions>
		<configuration>
			<wsdlUrls>
				<wsdlUrl>https://ec.europa.eu/taxation_customs/tin/checkTinService.wsdl</wsdlUrl>
			</wsdlUrls>
		</configuration>
	</plugin>

for versions after JDK 8 you need to import following dependencies to be used for SOAP interactions

	<dependencies>
		<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
		<dependency>
		    <groupId>javax.xml.bind</groupId>
		    <artifactId>jaxb-api</artifactId>
		    <version>2.3.1</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.bind</groupId>
			<artifactId>jaxb-core</artifactId>
			<version>3.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.bind</groupId>
			<artifactId>jaxb-impl</artifactId>
			<version>3.0.0</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.xml.ws/jaxws-api -->
		<dependency>
		    <groupId>javax.xml.ws</groupId>
		    <artifactId>jaxws-api</artifactId>
		    <version>2.3.1</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.jws/javax.jws-api -->
		<dependency>
		    <groupId>javax.jws</groupId>
		    <artifactId>javax.jws-api</artifactId>
		    <version>1.1</version>
		</dependency>	
	</dependencies>