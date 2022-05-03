FROM openjdk:11
MAINTAINER group9
VOLUME /tmp
EXPOSE 8080
ADD target/gandalf-0.0.1-SNAPSHOT.jar gandalf.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gandalf.jar"]
