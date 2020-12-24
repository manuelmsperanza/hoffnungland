#ibanRechner

#Create a new project

	mvn archetype:generate -Dfilter="org.apache.maven.archetypes:maven-archetype-quickstart" -DgroupId="com.hoffnungland" -DartifactId=ibanRechner -Dpackage="com.hoffnungland.ibanRechner" -Dversion="0.0.1"

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

wsdl2java of [WSDL](https://ssl.ibanrechner.de/soap/index.php?wsdl) to check IBAN<br>
_the use of wsdl2java is necessary because of soap encoding within WSDL_

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
				<wsdlUrl>https://ec.europa.eu/taxation_customs/vies/checkVatTestService.wsdl</wsdlUrl>
			</wsdlUrls>
		</configuration>
	</plugin>