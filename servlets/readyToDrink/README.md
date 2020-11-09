#Generate
	mvn archetype:generate -Dfilter="org.apache.maven.archetypes:maven-archetype-webapp" -DgroupId="com.hoffnungland" -DartifactId=readyToDrink -Dpackage="com.hoffnungland" -Dversion="0.0.1-SNAPSHOT"


#Angular Build
	ng build --prod --base-href=/readyToDrink/
