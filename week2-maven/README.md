List out all available goals-->     
mvn help:describe –Dplugin=eclipse

leverage local maven repository within a project-->
mvn -Dmaven.repo.local=$HOME/.m2 clean install


Extract and save build logs to a file-->
mvn clean build > maven.log

make maven work offline-->
mvn –o clean package
