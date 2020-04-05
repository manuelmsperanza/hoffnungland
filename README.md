#Generate
	mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-j2ee-simple -DarchetypeVersion=1.4
	-Dgroupid=com.hoffnungland
	-DartifactId=hoffnungland
	-Dpackage=com.hoffnungland
	-Dversion=0.0.1-SNAPSHOT

#Add to git
	git init
	git commit -a -m "first commit"
	git remote add origin git@github.com:manuelmsperanza/hoffnungland.git
	git push -u origin master