#Generate
	mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion="1.4" -DgroupId="com.hoffnungland" -DartifactId=orderEntryWeb -Dpackage="com.hoffnungland" -Dversion="0.0.1-SNAPSHOT"


#Angular Build
	ng build --prod --base-href=/orderEntryWeb/
