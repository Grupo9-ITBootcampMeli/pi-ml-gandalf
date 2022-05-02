FROM openjdk:8-jdk-alpine
MAINTAINER group9
VOLUME /tmp
EXPOSE 8080
ADD build/libs/gandalf-0.0.1-SNAPSHOT.jar gandalf.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gandalf.jar"]
